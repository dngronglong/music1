package com.dong.music.beans;

import java.io.Serializable;

public class MusicBean implements Serializable {
    //音乐名字
    private String musicName;
    //歌手
    private String singer;
    //普通hash
    private String fileHash;
    //hq hash
    private String hqHash;
    //无损
    private String sqHash;
    //专辑
    private String album;
    //封面
    private String cover;
    //播放地址
    private String url;
    //歌词
    private String lrc;
    //歌名
    private String audio_name;

    public MusicBean() {
    }

    public String getAudio_name() {
        return audio_name;
    }

    public void setAudio_name(String audio_name) {
        this.audio_name = audio_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    public String getHqHash() {
        return hqHash;
    }

    public void setHqHash(String hqHash) {
        this.hqHash = hqHash;
    }

    public String getSqHash() {
        return sqHash;
    }

    public void setSqHash(String sqHash) {
        this.sqHash = sqHash;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "MusicBean{" +
                "musicName='" + musicName + '\'' +
                ", singer='" + singer + '\'' +
                ", fileHash='" + fileHash + '\'' +
                ", hqHash='" + hqHash + '\'' +
                ", sqHash='" + sqHash + '\'' +
                ", album='" + album + '\'' +
                ", cover='" + cover + '\'' +
                ", url='" + url + '\'' +
                ", lrc='" + lrc + '\'' +
                ", audio_name='" + audio_name + '\'' +
                '}';
    }
}
