package com.example.demo.ToolUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Map;
import java.util.UUID;


@Configuration
@Log4j2
@Getter
@Setter
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${spring.rabbitmq.addresses}")
    private String addresses;

    @Autowired
    private PropertiesConfig propertiesConfig;

    public static final String EXCHANGE_A = "my-mq-exchange_A";
    public static final String EXCHANGE_B = "my-mq-exchange_B";

    public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";

    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";

    private String queueName;
    private String routingKey;
    private RabbitTemplate rabbitTemplate;
    private String exchange;


    public RabbitMqConfig(){

    }

    /**
     *  自定义队列
     * @param queueName
     * @param routingKey
     * @param exchange
     */
    public RabbitMqConfig(String queueName , String routingKey , String exchange){
        this.queueName = queueName;
        this.routingKey = routingKey;
        this.exchange = exchange;
        this.rabbitTemplate = rabbitTemplate1();
        RabbitAdmin admin = new RabbitAdmin(this.rabbitTemplate.getConnectionFactory());
        admin.declareQueue(new Queue(this.queueName));
        admin.declareExchange(new DirectExchange(this.exchange));
        admin.declareBinding(BindingBuilder.bind(new Queue(this.queueName,true)).to(new DirectExchange(this.exchange)).with(this.routingKey));


    }
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

    public ConnectionFactory connectionFactory1() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(propertiesConfig.getProperty("spring.rabbitmq.host"),Integer.parseInt(propertiesConfig.getProperty("spring.rabbitmq.port")));
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername( propertiesConfig.getProperty("spring.rabbitmq.username"));
        connectionFactory.setPassword( propertiesConfig.getProperty("spring.rabbitmq.password"));
        connectionFactory.setVirtualHost( propertiesConfig.getProperty("spring.rabbitmq.virtual-host"));
        connectionFactory.setAddresses(propertiesConfig.getProperty("spring.rabbitmq.addresses"));
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

    public RabbitTemplate rabbitTemplate1() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory1());
        template.setQueue(this.queueName);
        template.setRoutingKey(this.routingKey);
        template.setExchange(this.exchange);
        return template;
    }

    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE_A);
    }

    @Bean
    public DirectExchange defaultExchangeB() {
        return new DirectExchange(EXCHANGE_B);
    }

    @Bean
    public Queue queueA() {
        return new Queue(QUEUE_A, true); //队列持久
    }

    @Bean
    public Queue queueB() {
        return new Queue(QUEUE_B, true); //队列持久
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(this.ROUTINGKEY_A);
    }
    @Bean
    public Binding bindingB(){
        return BindingBuilder.bind(queueB()).to(defaultExchangeB()).with(this.ROUTINGKEY_B);
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
       this.rabbitTemplate.convertAndSend(exchange , routingKey , content , correlationId);
    }
}
