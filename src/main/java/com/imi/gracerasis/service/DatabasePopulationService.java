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
public class DatabasePopulationService {

    private static final Logger logger = LoggerFactory.getLogger(DatabasePopulationService.class);

    private final ChartJacketService chartJacketService;
    private final ObjectMappingService objectMappingService;
    private final MusicRepository musicRepository;
    private final ChartRepository chartRepository;
    private final GoogleCloudStorageService googleCloudStorageService;

    @Value("${game.data.path}")
    private String gameDataPath;


    @Autowired
    public DatabasePopulationService(ChartJacketService chartJacketService,
                                     ObjectMappingService objectMappingService,
                                     MusicRepository musicRepository,
                                     ChartRepository chartRepository, GoogleCloudStorageService googleCloudStorageService)
    {
        this.chartJacketService = chartJacketService;
        this.objectMappingService = objectMappingService;
        this.musicRepository = musicRepository;
        this.chartRepository = chartRepository;
        this.googleCloudStorageService = googleCloudStorageService;
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

    public void populateDatabase() {

        try {

            musicRepository.deleteAll();
            chartRepository.deleteAll();

            //get music from dbxml
            List<MusicXml> musicXmlList = getMusicDBData();

            //for each music
            for (MusicXml m : musicXmlList) {
                //separate into music and chart objects
                //music mapping
                Music music = objectMappingService.toMusicObject(m);
                //chart mapping
                Chart novice = objectMappingService.toChartObject(m.getCharts().getNovice(), m.getId(), "Novice");
                Chart advanced = objectMappingService.toChartObject(m.getCharts().getAdvanced(), m.getId(), "Advanced");
                Chart exhaust = objectMappingService.toChartObject(m.getCharts().getExhaust(), m.getId(), "Exhaust");
                Chart last = null;

                //set final difficulty
                //TODO: set gravity, heavenly, vivid, exceed difficulties correctly
                if(m.getCharts().getInfinite().getLevel() != 0) {
                    last = objectMappingService.toChartObject(m.getCharts().getInfinite(), m.getId(), "Infinite");
                }

                if(m.getCharts().getMaximum() != null)
                {
                    if(m.getCharts().getMaximum().getLevel() != 0) {
                        last = objectMappingService.toChartObject(m.getCharts().getMaximum(), m.getId(), "Maximum");
                    }
                }

                //get the jacket filepaths for current song
                List<String> jacketFilepaths = chartJacketService.getJacketFilepaths(m);
                //upload and return the uploaded links for the images
                List<String> jacketURLs = googleCloudStorageService.uploadJackets(jacketFilepaths);

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

}
