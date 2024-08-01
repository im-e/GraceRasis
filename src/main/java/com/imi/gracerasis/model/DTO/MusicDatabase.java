package com.imi.gracerasis.model.DTO;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "mdb")
@XmlAccessorType(XmlAccessType.FIELD)
public class MusicDatabase {

    @XmlElement(name = "music")
    private List<Music> music;

    public List<Music> getMusic() {
        return music;
    }

    public void setMusic(List<Music> music) {
        this.music = music;
    }
}
