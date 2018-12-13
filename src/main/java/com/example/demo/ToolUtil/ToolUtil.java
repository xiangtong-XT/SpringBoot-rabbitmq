package com.example.demo.ToolUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ToolUtil {
    final static LinkedBlockingQueue linkQueue = new LinkedBlockingQueue(100);
    final static ExecutorService es = Executors.newFixedThreadPool(4);

    static boolean b = false;

    /**
     * json对象
     *
     * @param jsonData json串
     * @param type     转换类型
     * @param <T>
     * @return
     */
    public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        T result = gson.fromJson(jsonData, type);
        return result;
    }

    /**
     * 将Json数组解析成相应的映射对象列表
     *
     * @param jsonData json串
     * @param type     转换类型
     * @param <T>
     * @return
     */
    public static <T> List<T> parseJsonArrayWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>() {
        }.getType());
        return result;
    }

    public static void main(String[] args) {
         /*ConcurrentHashMap ConcurrentHashMap = new ConcurrentHashMap();
         ConcurrentHashMap.put("q",1);
         ConcurrentHashMap.put("q1",1);
         Collection k = ConcurrentHashMap.values();
         Enumeration l = ConcurrentHashMap.keys();
         Iterator f = k.iterator();
         System.out.println(l);
         while(f.hasNext()){

            System.out.println( f.next());
         }
        while(l.hasMoreElements()){
            System.out.println(l.nextElement());
        };*/
        String s = "(Body:'测试账号' MessageProperties [headers={}, contentType=text/plain, contentEncoding=UTF-8, contentLength=0, receivedDeliveryMode=PERSISTENT, priority=0, redelivered=false, receivedExchange=my-mq-exchange_B, receivedRoutingKey=spring-boot-routingKey_B, deliveryTag=134, consumerTag=amq.ctag-J8hM_WfzUddOot_VNeU6Rg, consumerQueue=QUEUE_B])";

        System.out.println(parseJsonWithGson(s, Map.class));

    }
}
