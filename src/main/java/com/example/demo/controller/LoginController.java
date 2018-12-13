package com.example.demo.controller;


import com.example.demo.ToolUtil.MD5;
import com.example.demo.ToolUtil.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/manager/login")
@Api(tags = "后台登录")
public class LoginController extends BaseController{

    @GetMapping("/login.do")
    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "登录账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pwd", value = "登录密码", required = true, dataType = "String")})
    public ServerResponse Login(@RequestParam(name="name" ,required = true)String name,@RequestParam(name="pwd",required = true) String pwd){
        Map<String, Object> rd = new HashMap<>();
           /* Object userCacheInfo = redisTemplate.opsForValue().get(userToken);
            if(userCacheInfo != null) {
               *//* rd.put("userInfo", JSONObject.parseObject(userCacheInfo, AdminVo.class));
                rd.put("token", userToken);
                Integer timeOut = RymPropertiesConfig.getInt("login_time_out");*//**//*
                if(timeOut==null||timeOut.equals(0)) {
                    timeOut = 30*60;
                }*//*
                redisTemplate.opsForValue().set(userToken,userCacheInfo,1800l);

            }else {
               return null;
            }*/

           return null;
    }


    private String getValidToken() throws Exception {
        /*String cacheP = CachePrefix.管理员登陆校验TOKEN.getValue();*/
        String ukey = MD5.md5("");
        long t = Calendar.getInstance().getTimeInMillis();
        /*String token = cacheP+ukey+t;
        Integer timeOut = RymPropertiesConfig.getInt("login_time_out");
        if(timeOut==null) {
            timeOut = 30*60;
        }
        JedisUtil.setString(token, timeOut, JSONObject.toJSONString(admin));*/
        return null;
    }
}
