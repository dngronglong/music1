package com.dong.music.controller;

import com.dong.music.beans.LayBean;
import com.dong.music.beans.MusicBean;
import com.dong.music.beans.SongBean;
import com.dong.music.beans.SongListBean;
import com.dong.music.repository.ListRepository;
import com.dong.music.repository.SongRepository;
import com.dong.music.service.SongListService;
import com.dong.music.utils.RedisUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.dong.music.utils.GetUrl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/mic")
@RestController
@SessionAttributes("list")
public class MusicController {
    @Resource
    private ListRepository listRepository;
    @Resource
    private SongRepository songRepository;
    @Resource
    private SongListService songListService;
    @Resource
    private RedisUtils redisUtils;

    private int userId;


    @GetMapping(value = "/search",produces= {"application/json;charset=utf-8"})
    @ResponseBody
    public LayBean search(String words,int page,int limit) {
//        JSONObject jsonObject=JSONObject.fromObject(GetUrl.getJson(words,page,limit).replace("<em>","").replace("<\\/em>",""));
//        JSONObject jsonObject1=JSONObject.fromObject(jsonObject.get("data"));
//        JSONArray jsonObject2=JSONArray.fromObject(jsonObject1.get("lists"));
//        String count=jsonObject1.getString("total");
//        List<MusicBean> musicList=new ArrayList<>();
//        for (int i=0;i<jsonObject2.size();i++) {
//            MusicBean musicBean=new MusicBean();
//            musicBean.setMusicName(jsonObject2.getJSONObject(i).getString("SongName"));
//            musicBean.setSinger(jsonObject2.getJSONObject(i).getString("SingerName"));
//            musicBean.setAlbum(jsonObject2.getJSONObject(i).getString("AlbumName"));
//            musicBean.setFileHash(jsonObject2.getJSONObject(i).getString("FileHash"));
//            musicBean.setHqHash(jsonObject2.getJSONObject(i).getString("HQFileHash"));
//            musicBean.setSqHash(jsonObject2.getJSONObject(i).getString("SQFileHash"));
//            musicList.add(musicBean);
//        }

        LayBean layBean=new LayBean();
        layBean.setCode("0");
        layBean.setData(GetUrl.qqMusicUtil(words));
        layBean.setCount("30");
        //System.out.println(layBean);
        return layBean;
    }
    @RequestMapping("/play")
    public MusicBean play(String hash) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String json=GetUrl.getUrl(hash);
        MusicBean musicBean=new MusicBean();
        JSONObject jsonObject=JSONObject.fromObject(json);
        JSONObject jsonObject1=JSONObject.fromObject(jsonObject.get("data"));
        musicBean.setCover(jsonObject1.getString("img"));
        musicBean.setLrc(jsonObject1.getString("lyrics"));
        musicBean.setUrl(jsonObject1.getString("play_url"));
        musicBean.setAudio_name(jsonObject1.getString("audio_name"));
        return musicBean;
    }
    @RequestMapping("/Qplay")
    public MusicBean qPlay(String mid,String musicName){
        return GetUrl.qqUrl(mid,musicName);
    }
    @RequestMapping(value = "/addList")
    public void addList(String listName,int id){
        SongListBean songListBean=new SongListBean();
        songListBean.setName(listName);
        songListBean.setU_id(id);
        listRepository.saveAndFlush(songListBean);
        redisUtils.remove("user"+userId+"SongList");
        redisUtils.setObjectList("user"+userId+"SongList",songListService.findSongListByUserId(userId));
    }
    @RequestMapping(value = "/findAll")
    public List<SongListBean> findAll(int id){
        userId=id;
        List<SongListBean> list=new ArrayList<>();
        if(redisUtils.exists("user"+id+"SongList")){
            list= redisUtils.getObbjectList("user"+id+"SongList");
            System.out.println(list+"使用了缓存");
        }else{
            list=songListService.findSongListByUserId(id);
            System.out.println("使用了数据库");
        }
        return list;
    }
    @RequestMapping(value = "/addSong")
    public SongBean addSong(String name,String hash,int id){
        SongBean songBean=new SongBean();
        songBean.setHash(hash);
        songBean.setName(name);
        songBean.setU_l_id(id);
        songRepository.saveAndFlush(songBean);
        return songBean;
    }
    @RequestMapping(value = "/s_list")
    public List<SongBean> find(Integer userId,Integer listId){
        return songRepository.findById(listId,userId);
    }

    @RequestMapping("/getUrl")
    public MusicBean getUrl(String mid,String quality,String musicName){
//        String json=GetUrl.getUrl(mid);
        System.out.println(mid+","+quality);
        MusicBean musicBean=GetUrl.qqUrl(mid,musicName);
        if (quality.equals("m4a")){
            musicBean.setUrl(musicBean.getM4a());
            musicBean.setAudio_name(musicBean.getAudio_name()+".m4a");
        }else if(quality.equals("mp3_l")){
            musicBean.setUrl(musicBean.getMp3_l());
            musicBean.setAudio_name(musicBean.getAudio_name()+".mp3");
        }else if(quality.equals("mp3_h")){
            musicBean.setUrl(musicBean.getMp3_h());
            musicBean.setAudio_name(musicBean.getAudio_name()+".mp3");
        }else if(quality.equals("ape")){
            musicBean.setUrl(musicBean.getApe());
            musicBean.setAudio_name(musicBean.getAudio_name()+".ape");
        }else if(quality.equals("flac")){
            musicBean.setUrl(musicBean.getFlac());
            musicBean.setAudio_name(musicBean.getAudio_name()+".flac");
        }
        return musicBean;
    }
}
