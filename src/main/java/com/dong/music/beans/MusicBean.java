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
    private String mid;//qq音乐mid
    private String m4a;
    private String mp3_l;//
    private String mp3_h;
    private String ape;
    private String flac;

    public MusicBean() {
    }

    public String getMid() {
        return mid;
    }

    public String getM4a() {
        return m4a;
    }

    public void setM4a(String m4a) {
        this.m4a = m4a;
    }

    public String getMp3_l() {
        return mp3_l;
    }

    public void setMp3_l(String mp3_l) {
        this.mp3_l = mp3_l;
    }

    public String getMp3_h() {
        return mp3_h;
    }

    public void setMp3_h(String mp3_h) {
        this.mp3_h = mp3_h;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public String getFlac() {
        return flac;
    }

    public void setFlac(String flac) {
        this.flac = flac;
    }

    public void setMid(String mid) {
        this.mid = mid;
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
                ", mid='" + mid + '\'' +
                ", m4a='" + m4a + '\'' +
                ", mp3_l='" + mp3_l + '\'' +
                ", mp3_h='" + mp3_h + '\'' +
                ", ape='" + ape + '\'' +
                ", flac='" + flac + '\'' +
                '}';
    }
}
