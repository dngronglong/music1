package com.dong.music.utils;


import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.dong.music.beans.Music;
import com.dong.music.beans.MusicBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class GetUrl {
    public static String getJson(String url, int page, int limit) {
        try {
            url = URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder json = new StringBuilder();
        String ur = "http://songsearch.kugou.com/song_search_v2?callback=jQuery191034642999175022426_1489023388639&keyword=" + url + "&page=" + page + "&pagesize=" + limit + "&userid=-1&clientver=&platform=WebFilter&tag=em&filter=2&iscorrection=1&privilege_filter=0&_=1489023388641";
        try {
            URL urlObject = new URL(ur);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String js = json.toString().replace("jQuery191034642999175022426_1489023388639", "").replace("(", "").replace(")", "");
        return js;
    }

    public static String getUrl(String hash) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String url = "http://www.kugou.com/yy/index.php?r=play/getdata&hash=" + hash;
//        String mD = GetMD5(hash + "kgcloud");
//        System.out.println(mD);
//        String url="http://trackercdn.kugou.com/i/?cmd=4&hash="+ hash+ "&key="+mD+ "&pid=1&forceDown=0&vip=1";
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public static String GetMD5(String hash) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(hash.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String md5 = new BigInteger(1, md.digest()).toString(16);
            //BigInteger会把0省略掉，需补全至32位
            return fillMD5(md5);
        } catch (Exception e) {
            throw new RuntimeException("MD5加密错误:" + e.getMessage(), e);
        }
    }

    public static String fillMD5(String md5) {
        return md5.length() == 32 ? md5 : fillMD5("0" + md5);
    }

    public static JSONObject getMid(String word) {
        try {
            word = URLEncoder.encode(word, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://s.music.qq.com/fcgi-bin/music_search_new_platform?t=0&n=30&aggr=1&cr=1&loginUin=0&format=json&inCharset=GB2312&outCharset=utf-8&notice=0&platform=jqminiframe.json&needNewCode=0&p=1&catZhida=0&remoteplace=sizer.newclient.next_song&w=";
        url += word;
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(json);
        return JSONObject.fromObject(json.toString());
    }

    public static List<Music.Song> getMusicList(String word) {
        String url = "http://s.music.qq.com/fcgi-bin/music_search_new_platform?t=0&n=30&aggr=1&cr=1&loginUin=0&format=json&inCharset=GB2312&outCharset=utf-8&notice=0&platform=jqminiframe.json&needNewCode=0&p=1&catZhida=0&remoteplace=sizer.newclient.next_song&w=" + word;
        HttpConfig httpConfig = HttpConfig.custom().url(url);
        try {
            String response = HttpClientUtil.send(httpConfig);
            Gson gson = new Gson();
            Music music = gson.fromJson(response, Music.class);//new TypeToken<Music>(){}.getType()
            List<Music.Song> musicList = music.getData().getSong().getList();
            musicList.forEach((song) -> {
                String f = song.getF();
                String[] j = f.split("\\|");
                String lyricSrc = "http://music.qq.com/miniportal/static/lyric/"+String.valueOf(Integer.valueOf(j[0]) % 100) + "/" +String.valueOf(j[0])+".xml";
                song.setLyricSrc(lyricSrc);
                if (j.length == 1) {
                    song.setAlbum("");
                } else {
                    song.setMid(j[20]);
                    song.setAlbum(song.getSinger());
                }
            });
            return musicList;
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static String getLyric(String lyricSrc) {
        lyricSrc = "http://music.qq.com/miniportal/static/lyric/68/101803868.xml";
        HttpConfig httpConfig = HttpConfig.custom().url(lyricSrc).encoding("GB2312");

        try {
            String response = HttpClientUtil.send(httpConfig);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new URL(lyricSrc));
            Element rootElm = document.getRootElement();
            return rootElm.getText();
        } catch (HttpProcessException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static List<MusicBean> qqMusicUtil(String word) {
        JSONObject jsonObject = GetUrl.getMid(word);
        JSONObject jsonObject1 = JSONObject.fromObject(jsonObject.getString("data"));
        JSONObject jsonObject2 = JSONObject.fromObject(jsonObject1.getString("song"));
        JSONArray jsonObject3 = JSONArray.fromObject(jsonObject2.getString("list"));
        List<MusicBean> list = new ArrayList<>();
        for (int i = 0; i < jsonObject3.size(); i++) {
            MusicBean musicBean = new MusicBean();
            String s = jsonObject3.getJSONObject(i).getString("f");
            String[] j = s.split("\\|");
            if (j.length == 1) {
                j = j[0].split("@@");
                musicBean.setMusicName(jsonObject3.getJSONObject(i).getString("fsong"));
                musicBean.setAlbum("");
                musicBean.setSinger(jsonObject3.getJSONObject(i).getString("fsinger"));
            } else {
                musicBean.setMusicName(jsonObject3.getJSONObject(i).getString("fsong"));
                musicBean.setAlbum(jsonObject3.getJSONObject(i).getString("fsinger"));
                musicBean.setSinger(j[3]);
                musicBean.setMid(j[20]);
            }

            list.add(musicBean);
        }
        //System.out.println(list);
        return list;
    }

    public static MusicBean qqUrl(String mid, String musicName) {
        try {
            mid = URLEncoder.encode(mid, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://www.douqq.com/qqmusic/qqapi.php?mid=" + mid;
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //String s=json.toString().replace("\\","");
        JSONObject jsonObject = JSONObject.fromObject(json.substring(1, json.length() - 1).replace("\\\"", "\""));
        System.out.println(jsonObject);
        MusicBean musicBean = new MusicBean();
        musicBean.setAudio_name(musicName);
        jsonObject = JSONObject.fromObject(json.substring(1, json.length() - 1).replace("\\", ""));
        musicBean.setM4a(jsonObject.getString("m4a"));
        musicBean.setMp3_l(jsonObject.getString("mp3_l"));
        musicBean.setMp3_h(jsonObject.getString("mp3_h"));
        musicBean.setApe(jsonObject.getString("ape"));
        musicBean.setFlac(jsonObject.getString("flac"));
        musicBean.setCover(jsonObject.getString("pic"));
        System.out.println(musicBean);
        return musicBean;
    }
}
