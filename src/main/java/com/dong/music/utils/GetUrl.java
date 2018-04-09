package com.dong.music.utils;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class GetUrl {
    public static String getJson(String url){
        try {
            url= URLEncoder.encode(url,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder json = new StringBuilder();
        String ur="http://songsearch.kugou.com/song_search_v2?callback=jQuery191034642999175022426_1489023388639&keyword="+url+"&page=1&pagesize=30&userid=-1&clientver=&platform=WebFilter&tag=em&filter=2&iscorrection=1&privilege_filter=0&_=1489023388641";
        try {
            URL urlObject=new URL(ur);
            URLConnection uc=urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"utf-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String js=json.toString().replace("jQuery191034642999175022426_1489023388639","").replace("(","").replace(")","");
        return js;
    }
    public static String getUrl(String hash){
        String url="http://www.kugou.com/yy/index.php?r=play/getdata&hash="+hash;
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject=new URL(url);
            URLConnection uc=urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"utf-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
