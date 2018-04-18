package com.dong.music.mapper;

import com.dong.music.beans.SongListBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "songListMapper")
public interface SongListMapper {
    List<SongListBean> findSongListByUserId(int id);
}
