package com.dong.music.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Music implements Serializable {

    private static final long serialVersionUID = 1439471589260104744L;

    private SongData data = new SongData();

    public SongData getData() {
        return data;
    }

    public void setData(SongData data) {
        this.data = data;
    }

    public class SongData {

        private SongList song = new SongList();

        public SongList getSong() {
            return song;
        }

        public void setSong(SongList song) {
            this.song = song;
        }

    }

    public class SongList {

        private List<Song> list = new ArrayList<Song>();

        public List<Song> getList() {
            return list;
        }

        public void setList(List<Song> list) {
            this.list = list;
        }

    }

    public class Song {

        private String f;
        @SerializedName("fsong")
        private String musicName;
        @SerializedName("fsinger")
        private String singer;
        private String album;
        private String mid;
        private String lyricSrc;

        public String getF() {
            return f;
        }

        public void setF(String f) {
            this.f = f;
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

        public String getAlbum() {
            return album;
        }

        public void setAlbum(String album) {
            this.album = album;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getLyricSrc() {
            return lyricSrc;
        }

        public void setLyricSrc(String lyricSrc) {
            this.lyricSrc = lyricSrc;
        }
    }
}
