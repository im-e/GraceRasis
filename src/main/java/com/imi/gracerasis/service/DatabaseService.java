package com.imi.gracerasis.service;

import com.imi.gracerasis.model.DTO.MusicXml;
import com.imi.gracerasis.model.DTO.MusicXmlDatabase;
import com.imi.gracerasis.model.entity.Chart;
import com.imi.gracerasis.model.entity.Music;
import com.imi.gracerasis.model.repository.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class DatabaseService {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);

    private final JacketService jacketService;
    private final ObjectMappingService objectMappingService;
    private final MusicRepository musicRepository;
    private final ChartRepository chartRepository;
    private final GoogleCloudService googleCloudService;

    @Value("${game.data.path}")
    private String gameDataPath;


    @Autowired
    public DatabaseService(JacketService jacketService,
                           ObjectMappingService objectMappingService,
                           MusicRepository musicRepository,
                           ChartRepository chartRepository, GoogleCloudService googleCloudService)
    {
        this.jacketService = jacketService;
        this.objectMappingService = objectMappingService;
        this.musicRepository = musicRepository;
        this.chartRepository = chartRepository;
        this.googleCloudService = googleCloudService;
    }


    public List<MusicXml> getMusicDBData() throws JAXBException {

        String musicDBPath = (gameDataPath + "\\contents\\data\\others\\music_db.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(MusicXmlDatabase.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        MusicXmlDatabase mdb = (MusicXmlDatabase) jaxbUnmarshaller.unmarshal(new File(musicDBPath));
        return mdb.getMusic();
    }

    private void assignJacket(Chart chart, String jacketURL) {
        chart.setJacketLink(jacketURL);
    }

    private void clearExistingData() {
        musicRepository.deleteAll();
        chartRepository.deleteAll();
    }

    public void populateDatabase() {

        try {

            clearExistingData();

            //get music from dbxml
            List<MusicXml> musicXmlList = getMusicDBData();

            //for each music
            for (MusicXml musicXml : musicXmlList) {
                //separate into music and chart objects
                //music mapping
                Music music = objectMappingService.toMusicObject(musicXml);
                //chart mapping
                Chart novice = objectMappingService.toChartObject(musicXml.getCharts().getNovice(), musicXml, "Novice");
                Chart advanced = objectMappingService.toChartObject(musicXml.getCharts().getAdvanced(), musicXml, "Advanced");
                Chart exhaust = objectMappingService.toChartObject(musicXml.getCharts().getExhaust(), musicXml, "Exhaust");
                Chart last = null;

                //set final difficulty
                if(musicXml.getCharts().getInfinite().getLevel() != 0) {
                    last = objectMappingService.toChartObject(musicXml.getCharts().getInfinite(), musicXml, "Infinite");
                    music.setFinalLevel(last.getLevel());
                    music.setFinalDifficulty(last.getDifficulty());
                }

                if(musicXml.getCharts().getMaximum() != null)
                {
                    if(musicXml.getCharts().getMaximum().getLevel() != 0) {
                        last = objectMappingService.toChartObject(musicXml.getCharts().getMaximum(), musicXml, "Maximum");
                        music.setFinalLevel(last.getLevel());
                        music.setFinalDifficulty(last.getDifficulty());
                    }
                }

                //get the jacket filepaths for current song
                List<String> jacketFilepaths = jacketService.getJacketFilepaths(musicXml);
                //upload and return the uploaded links for the images
                List<String> jacketURLs = googleCloudService.uploadJackets(jacketFilepaths);

                // Assign jackets based on the number of available jackets
                switch (jacketURLs.size()) {
                    case 1:
                        assignJacket(novice, jacketURLs.get(0));
                        assignJacket(advanced, jacketURLs.get(0));
                        assignJacket(exhaust, jacketURLs.get(0));
                        if (last != null) assignJacket(last, jacketURLs.get(0));
                        break;
                    case 2:
                        assignJacket(novice, jacketURLs.get(0));
                        assignJacket(advanced, jacketURLs.get(0));
                        assignJacket(exhaust, jacketURLs.get(0));
                        if (last != null) assignJacket(last, jacketURLs.get(1));
                        break;
                    case 3:
                        assignJacket(novice, jacketURLs.get(0));
                        assignJacket(advanced, jacketURLs.get(1));
                        assignJacket(exhaust, jacketURLs.get(2));
                        if (last != null) assignJacket(last, jacketURLs.get(2));
                        break;
                    case 4:
                        assignJacket(novice, jacketURLs.get(0));
                        assignJacket(advanced, jacketURLs.get(1));
                        assignJacket(exhaust, jacketURLs.get(2));
                        if (last != null) assignJacket(last, jacketURLs.get(3));
                        break;
                    default:
                        // Handle unexpected number of jackets
                        System.out.println("Unexpected number of jackets: " + jacketURLs.size());
                }

                //set the final (most recognised) jacket to the music
                music.setJacketLink(jacketURLs.getLast());
                //save and add to db
                musicRepository.save(music);
                logger.info("{}: {} - Added to the database", music.getLabel(), music.getAscii());

                //save the charts to db
                chartRepository.save(novice);
                chartRepository.save(advanced);
                chartRepository.save(exhaust);
                if(last != null) {
                    chartRepository.save(last);
                }
            }

            logger.info("Database populated.");

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void updateDatabase() {
        List<Music> musicList = musicRepository.findAll();

        for (Music music : musicList) {
            List<Chart> charts = chartRepository.findChartByMusicId(music.getId());
            boolean musicUpdated = false;

            if (!charts.isEmpty()) {
                charts.get(0).setTitle(music.getTitle());
                charts.get(0).setArtist(music.getArtist());
                chartRepository.save(charts.get(0));
                musicUpdated = true;
            }
            if (charts.size() >= 2) {
                music.setAdvancedLevel(charts.get(1).getLevel());
                charts.get(1).setTitle(music.getTitle());
                charts.get(1).setArtist(music.getArtist());
                chartRepository.save(charts.get(1));
            }
            if (charts.size() >= 3) {
                music.setExhaustLevel(charts.get(2).getLevel());
                charts.get(2).setTitle(music.getTitle());
                charts.get(2).setArtist(music.getArtist());
                chartRepository.save(charts.get(2));
            }
            if (charts.size() >= 4) {
                Chart finalChart = charts.get(3);
                music.setFinalLevel(finalChart.getLevel());
                music.setFinalDifficulty(finalChart.getDifficulty());
                finalChart.setTitle(music.getTitle());
                finalChart.setArtist(music.getArtist());

                if ("Infinite".equals(finalChart.getDifficulty())) {
                    switch (music.getInfVer()) {
                        case 3:
                            finalChart.setDifficulty("Gravity");
                            break;
                        case 4:
                            finalChart.setDifficulty("Heavenly");
                            break;
                        case 5:
                            finalChart.setDifficulty("Vivid");
                            break;
                        case 6:
                            finalChart.setDifficulty("Exceed");
                            break;
                        default:
                            finalChart.setDifficulty("Infinite");
                            break;
                    }
                }
                chartRepository.save(finalChart);
            }

            if (musicUpdated) {
                musicRepository.save(music);
            }

            logger.info("updated music: {}, with {} charts updated", music.getLabel(), charts.size());
        }
    }

}
