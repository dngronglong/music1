package com.dong.music.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SessionFilter implements Filter {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SessionFilter.class);

    @Value("$(serverurl)")
    private String serverurl;
    /**
     * 封装，不需要过滤的list列表
     */
    protected static List<Pattern> patterns = new ArrayList<Pattern>();


    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        patterns.add(Pattern.compile("login/index.html"));
        patterns.add(Pattern.compile("login/login"));
        patterns.add(Pattern.compile("login"));
        patterns.add(Pattern.compile("registe"));
        patterns.add(Pattern.compile("register"));
        patterns.add(Pattern.compile("main/autoFillty_rj_situation.*"));
        patterns.add(Pattern.compile("main/post.*"));
        patterns.add(Pattern.compile(".*[(\\.js)||(\\.css)||(\\.png)]"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (url.startsWith("/") && url.length() > 1) {
            url = url.substring(1);
        }
        if (isInclude(url)){
            chain.doFilter(httpRequest, httpResponse);
            return;
        } else {
            HttpSession session = httpRequest.getSession();
            if (session.getAttribute("user") != null){
                // session存在
                chain.doFilter(httpRequest, httpResponse);
                return;
            } else {
                // session不存在 准备跳转失败
                httpResponse.sendRedirect("login");
            }
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * 是否需要过滤
     * @param url
     * @return
     */
    private boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

}