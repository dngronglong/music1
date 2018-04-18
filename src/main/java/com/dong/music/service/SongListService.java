package com.dong.music.service;

import com.dong.music.beans.SongListBean;

import java.util.List;

public interface SongListService {
    List<SongListBean> findSongListByUserId(int id);
}
