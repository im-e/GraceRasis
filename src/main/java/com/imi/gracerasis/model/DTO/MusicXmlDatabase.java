package com.imi.gracerasis.model.DTO;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "mdb")
@XmlAccessorType(XmlAccessType.FIELD)
public class MusicXmlDatabase {

    @XmlElement(name = "music")
    private List<MusicXml> musicXmlList;

    public List<MusicXml> getMusic() {
        return musicXmlList;
    }

    public void setMusic(List<MusicXml> musicXmlList) {
        this.musicXmlList = musicXmlList;
    }
}
