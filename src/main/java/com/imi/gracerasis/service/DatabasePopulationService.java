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

    private final TrackImageService trackImageService;
    private final ObjectMappingService objectMappingService;
    private final MusicRepository musicRepository;
    private final ChartRepository chartRepository;

    @Value("${game.data.path}")
    private String gameDataPath;


    @Autowired
    public DatabasePopulationService(TrackImageService trackImageService,
                                     ObjectMappingService objectMappingService,
                                     MusicRepository musicRepository,
                                     ChartRepository chartRepository)
    {
        this.trackImageService = trackImageService;
        this.objectMappingService = objectMappingService;
        this.musicRepository = musicRepository;
        this.chartRepository = chartRepository;
    }


    public List<MusicXml> getMusicDBData() throws JAXBException {

        String musicDBPath = (gameDataPath + "\\contents\\data\\others\\music_db.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(MusicXmlDatabase.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        MusicXmlDatabase mdb = (MusicXmlDatabase) jaxbUnmarshaller.unmarshal(new File(musicDBPath));
        return mdb.getMusic();
    }

    public void populateDatabase() {

        try {

            musicRepository.deleteAll();

            List<MusicXml> musicXmlList = getMusicDBData();
            for (MusicXml m : musicXmlList) {

                Music music = objectMappingService.toMusicObject(m);
                musicRepository.save(music);
                logger.info("{}: {} - Added to the database", music.getLabel(), music.getAscii());


                Chart novice = objectMappingService.toChartObject(m.getCharts().getNovice(), m.getId(), "Novice");
                Chart advanced = objectMappingService.toChartObject(m.getCharts().getAdvanced(), m.getId(), "Advanced");
                Chart exhaust = objectMappingService.toChartObject(m.getCharts().getExhaust(), m.getId(), "Exhaust");
                Chart last = null;

                if(m.getCharts().getInfinite().getLevel() != 0) {
                    last = objectMappingService.toChartObject(m.getCharts().getInfinite(), m.getId(), "Infinite");
                }

                if(m.getCharts().getMaximum() != null)
                {
                    if(m.getCharts().getMaximum().getLevel() != 0) {
                        last = objectMappingService.toChartObject(m.getCharts().getMaximum(), m.getId(), "Maximum");
                    }
                }

                chartRepository.save(novice);
                chartRepository.save(advanced);
                chartRepository.save(exhaust);
                if(last != null) {
                    chartRepository.save(last);
                }


            }

            logger.info("Database populated.");

//            for(Music music : musicList) TODO: Create a way to store the images not as binary objects
//            {
//                trackImageService.processTrack(music);
//
//            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
