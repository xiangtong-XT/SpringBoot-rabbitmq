package com.example.demo.ToolUtil;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log4j2
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "86400");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with, Content-Type, reqSource, token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        final String token = request.getHeader("token");
        final String uri = request.getRequestURI();
        final String contextPath = request.getContextPath();
        final String resourcePath = uri.substring(contextPath.length(), uri.length());

        try{
            if(1==1){
                return true;
            }
            //文档
            log.info("{} {}",request.getMethod(),resourcePath);
            if(resourcePath.contains("swagger") || resourcePath.contains("login.do")){
                return true;
            }
          if (!StringUtils.hasText(token)) {
                log.error("unable to get the token from the current request header");
                output(response, FormatableErrorFactory.ERROR_LOGIN, "获取用户请求会话信息失败，请重新进行登录");
                return false;
            }
            final String tokenValue = redisTemplate.opsForValue().get("token") == null ? "" : redisTemplate.opsForValue().get("token").toString();
            if(StringUtils.hasText(tokenValue)){

            }else{
                output(response, FormatableErrorFactory.ERROR_LOGIN, "获取用户请求会话信息失败，请重新进行登录");
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            output(response, FormatableErrorFactory.ERROR_LOGIN, "获取用户请求会话信息失败，请重新进行登录");
            return false;
        }


        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

    protected void output(HttpServletResponse response, int code, String msg) {
        String content = "{\"code\":" + code + ", \"msg\": \"" + msg + "\"}";
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            PrintWriter writer = response.getWriter();
            writer.write(content);
            log.info("response ：{}",content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            log.error("write data into response failed: ", e);
            e.printStackTrace();
        }
    }

}
