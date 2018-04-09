package com.dong.music.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

@Controller
public class DownLoadController {
    @RequestMapping("/download")
    public String download(String name, String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //System.out.println(url);
        URL ul = new URL(url);
        String[] arr = url.split("\\.");
        String type = "." + arr[4];
        String fileName = name;
        String agent = (String) request.getHeader("USER-AGENT");
//        解决火狐浏览器下载文件名乱码
        if (agent != null && agent.toLowerCase().indexOf("firefox") > 0) {
            fileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
        } else {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName + type);
        response.setHeader("Content-Disposition", "attachment;fileName="
                + fileName + type);
        InputStream fis = null;
        OutputStream os = null;
        try {
            URLConnection conn = ul.openConnection();
            fis = conn.getInputStream();
            os = response.getOutputStream();
            IOUtils.copy(fis, os);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

}
