package com.dong.music.service.impl;

import com.dong.music.beans.SongListBean;
import com.dong.music.mapper.SongListMapper;
import com.dong.music.service.SongListService;
import com.dong.music.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 董荣龙
 * @date 2018-04-18 12:07
 */
@Service
public class SongListServiceImpl implements SongListService {
    @Autowired
    private SongListMapper songListMapper;
    @Resource
    private RedisUtils redisUtils;
    @Override
    public List<SongListBean> findSongListByUserId(int id) {


        redisUtils.setObjectList("user"+id+"SongList",songListMapper.findSongListByUserId(id));


        return songListMapper.findSongListByUserId(id);
    }
}
