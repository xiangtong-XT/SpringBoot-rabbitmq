package com.example.demo.controller;

import com.example.demo.ToolUtil.ToolUtil;
import com.example.demo.entity.BookEntity;
import com.example.demo.service.RabbitmqService;
import com.example.demo.service.TestService;
import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("TestController")
@Api(tags = "测试")
public class TestController {

    @Autowired
    private TestService testService ;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitmqService rabbitmqService;

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/findAll")
    @ApiOperation("查询信息")
    public String findAll() throws Exception{
        int i = 0;
       System.out.println(i ++ );
      return "";
    }

//    @GetMapping("/insertBooks")
//    @ApiOperation("添加书")
//    public void insertBooks(@RequestParam(name="name" ,required = false)String name ) throws Exception{
//        CloseableHttpClient httpclient = null ;
//        CloseableHttpResponse response = null;
//        try {
//           System.out.println(redisTemplate.opsForValue().get("xiangtong"));
//            httpclient = HttpClients.createDefault();
//            // 创建参数队列
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//           /* params.add(new BasicNameValuePair("username", "admin"));
//            params.add(new BasicNameValuePair("password", "123456"));*/
//
//
//            String path = "http://api.zhuishushenqi.com/book/by-categories?gender=male&type=over&major=玄幻&minor=&start=0&limit=20";
//            //参数转换为字符串
//            String paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(params, "UTF-8"));
//            String url = path;
//            // 创建httpget.
//            HttpGet httpget = new HttpGet(path);
//            /*System.out.println("executing request " + httpget.getURI());*/
//            // 执行get请求.
//            response = httpclient.execute(httpget);
//
//                // 获取响应实体
//                HttpEntity entity = response.getEntity();
//                /*// 打印响应状态
//                System.out.println(response.getStatusLine());*/
//                if (entity != null) {
//                    Gson g = new Gson();
//                    Map k =  ToolUtil.parseJsonWithGson(EntityUtils.toString(entity), Map.class);
//                   /* System.out.println(k.get("books").toString());
//                    System.out.println( g.toJson(k.get("books"))+"//////////");*/
//                    List<BookEntity> list = ToolUtil.parseJsonArrayWithGson(g.toJson(k.get("books")), BookEntity.class);
//
//                  /*  System.out.println(list.size());*/
//                }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭连接,释放资源
//            response.close();
//            httpclient.close();
//
//        }
//    }
    @GetMapping("/rabbitMqSend")
    public void testRabbitMqSend(@RequestParam String param){
        int i = 1;
        Map  map = new HashMap<>();
        map.put("token",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        rabbitmqService.sendMsg1(param,"EXCHANGE_W","ROUTINGKEY_W","QUEUENAME_W",map);
//        rabbitmqService.sendMsg(param ,"my-mq-exchange_B" ,"spring-boot-routingKey_B" , map );
    }
}
