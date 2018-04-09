package com.dong.music.controller;

import com.dong.music.utils.CodeUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

@Controller
public class CodeController {
    @RequestMapping(value = "/code")
    public String code(HttpServletResponse response, HttpServletRequest request){
        InputStream fis = null;
        OutputStream os = null;
        // 调用工具类生成的验证码和验证码图片
        Map<String, Object> codeMap = CodeUtils.generateCodeAndPic();
        // 将四位数字的验证码保存到Session中。
        HttpSession session = request.getSession();
        session.setAttribute("code", codeMap.get("code").toString());

        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", -1);

        response.setContentType("image/jpeg");
        //System.out.println(session.getAttribute("code"));
        try {
            os=response.getOutputStream();
            ImageIO.write((RenderedImage) codeMap.get("codePic"),"jpeg",os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
