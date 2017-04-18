package com.sonio.musicplayer.entity;

/**
 * Created by 812 on 2017/4/17.
 */

public class Mp3Info {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    private long  id;
    private String title;
    private String artist;

    public Mp3Info(long id,String title,String artist){
        this.id = id;
        this.title = title;
        this.artist = artist;

    }
}
