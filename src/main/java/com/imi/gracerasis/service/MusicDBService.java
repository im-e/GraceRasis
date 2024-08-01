package com.imi.gracerasis.service;

import com.imi.gracerasis.model.DTO.Music;
import com.imi.gracerasis.model.DTO.MusicDatabase;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class MusicDBService {

    @Value("${game.data.path}")
    private String gameDataPath;

    public List<Music> getMusicDBData() throws JAXBException {
        String musicDBPath = (gameDataPath + "\\contents\\data\\others\\music_db.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(MusicDatabase.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        MusicDatabase mdb = (MusicDatabase) jaxbUnmarshaller.unmarshal(new File(musicDBPath));
        return mdb.getMusic();
    }

}
