package com.imi.gracerasis.service;

import com.imi.gracerasis.model.DTO.MusicXml;
import com.imi.gracerasis.model.DTO.MusicXmlDatabase;
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
    private final EntityMappingService entityMappingService;
    private final MusicRepository musicRepository;

    @Value("${game.data.path}")
    private String gameDataPath;


    @Autowired
    public DatabasePopulationService(TrackImageService trackImageService,
                                     EntityMappingService entityMappingService,
                                     MusicRepository musicRepository)
    {
        this.trackImageService = trackImageService;
        this.entityMappingService = entityMappingService;
        this.musicRepository = musicRepository;
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

                Music music = entityMappingService.toMusicObject(m);
                musicRepository.save(music);
                logger.info("{}: {} - Added to the database", music.getLabel(), music.getAscii());

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
