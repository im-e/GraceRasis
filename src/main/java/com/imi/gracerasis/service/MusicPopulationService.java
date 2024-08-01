package com.imi.gracerasis.service;

import com.imi.gracerasis.model.DTO.Music;
import com.imi.gracerasis.model.DTO.MusicDatabase;
import com.imi.gracerasis.model.entity.MusicEntity;
import com.imi.gracerasis.model.repository.MusicEntityRepository;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class MusicPopulationService {

    private final MusicEntityRepository musicEntityRepository;
    private final TrackImageService trackImageService;
    private final MusicMapperService musicMapperService;

    @Value("${game.data.path}")
    private String gameDataPath;


    @Autowired
    public MusicPopulationService(MusicEntityRepository musicEntityRepository,
                                  TrackImageService trackImageService, MusicMapperService musicMapperService)
    {
        this.musicEntityRepository = musicEntityRepository;
        this.trackImageService = trackImageService;
        this.musicMapperService = musicMapperService;
    }


    public List<Music> getMusicDBData() throws JAXBException {

        String musicDBPath = (gameDataPath + "\\contents\\data\\others\\music_db.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(MusicDatabase.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        MusicDatabase mdb = (MusicDatabase) jaxbUnmarshaller.unmarshal(new File(musicDBPath));
        return mdb.getMusic();
    }

    @Transactional
    public void populateDatabase() {

        try {
            List<Music> musicList = getMusicDBData();

            for (Music m : musicList) {
                MusicEntity mE = musicMapperService.toEntity(m);
                musicEntityRepository.save(mE);
            }


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
