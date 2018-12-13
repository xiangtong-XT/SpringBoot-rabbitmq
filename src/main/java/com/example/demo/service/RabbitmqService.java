package com.example.demo.service;

import com.example.demo.ToolUtil.RabbitMqConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Log4j2
@Component
public class RabbitmqService implements RabbitTemplate.ConfirmCallback{

    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin admin;
    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Autowired
    public RabbitmqService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }
    public void sendMsg(Object content,String exchange,String routingKey,Map<String , Object> headerProperties) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());

        if(null != headerProperties && headerProperties.size() > 0){
            MessageHeaders mhs = new MessageHeaders(headerProperties);
            Message msg = MessageBuilder.createMessage(content, mhs);
            rabbitTemplate.convertAndSend(exchange , routingKey , msg, correlationId);
            return ;
        }
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(exchange , routingKey , content , correlationId);
    }

    /**
     * 自定义队列并添加队列
     * @param content
     * @param exchange
     * @param routingKey
     * @param queueName
     * @param headerProperties
     */
    public void sendMsg1(Object content,String exchange,String routingKey,String queueName,Map<String , Object> headerProperties){
        RabbitMqConfig rmc = new RabbitMqConfig(queueName,routingKey,exchange);
        rmc.sendMsg(content,exchange,routingKey,headerProperties);
    }

    /**
     * 自定义队列
     * @param exchange
     * @param routingKey
     * @param queueName
     */
    public void addQueue(String exchange,String routingKey,String queueName){
        new RabbitMqConfig(queueName,routingKey,exchange);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        log.debug("confirm回调！" + s);
        if(b){
//            if(){}

            log.debug("成功设置到消息队列{}" + correlationData);
        }else{
            log.debug("设置到消息队列失败{}" + correlationData);
        }
    }


}
