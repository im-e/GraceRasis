package com.imi.gracerasis.service;

import com.imi.gracerasis.model.DTO.MusicXml;
import com.imi.gracerasis.model.DTO.MusicXmlDatabase;
import com.imi.gracerasis.model.repository.*;
import jakarta.transaction.Transactional;
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
import java.util.Objects;

@Service
public class DatabasePopulationService {

    private static final Logger logger = LoggerFactory.getLogger(DatabasePopulationService.class);

    private final TrackImageService trackImageService;
    private final EntityMappingService entityMappingService;

    @Value("${game.data.path}")
    private String gameDataPath;


    @Autowired
    public DatabasePopulationService(TrackImageService trackImageService,
                                     EntityMappingService entityMappingService)
    {
        this.trackImageService = trackImageService;
        this.entityMappingService = entityMappingService;
    }


    public List<MusicXml> getMusicDBData() throws JAXBException {

        String musicDBPath = (gameDataPath + "\\contents\\data\\others\\music_db.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(MusicXmlDatabase.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        MusicXmlDatabase mdb = (MusicXmlDatabase) jaxbUnmarshaller.unmarshal(new File(musicDBPath));
        return mdb.getMusic();
    }

    @Transactional
    public void populateDatabase() {

        try {
            List<MusicXml> musicXmlList = getMusicDBData();

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
