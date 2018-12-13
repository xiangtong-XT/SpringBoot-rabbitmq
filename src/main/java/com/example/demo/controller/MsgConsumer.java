package com.example.demo.controller;

import com.example.demo.ToolUtil.RabbitMqConfig;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.spring.web.json.Json;

import java.net.URLDecoder;
import java.util.Map;

@Log4j2
@Component
public class MsgConsumer {

    @RabbitListener(queues = RabbitMqConfig.QUEUE_B)
    @RabbitHandler
    public void process(Message content)throws Exception {
        log.info("处理消息 " + content);
        log.info("处理内容 " + content.getPayload());
    }

}
