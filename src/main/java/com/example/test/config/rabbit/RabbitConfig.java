//package com.example.test.config.rabbit;
//
//import org.springframework.amqp.core.*;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Description
// */
//@Configuration
//public class RabbitConfig {
//
//    public static final String QUEUE_EMAIL = "queue_email";//email队列
//    public static final String QUEUE_SMS = "queue_sms";//sms队列
//    public static final String EXCHANGE_NAME="topic.exchange";//topics类型交换机
//    public static final String ROUTINGKEY_EMAIL="topic.#.email.#";
//    public static final String ROUTINGKEY_SMS="topic.#.sms.#";
//
//
//    @Bean(EXCHANGE_NAME)
//    public Exchange exchange(){
//        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
//    }
//
//
//    @Bean(QUEUE_EMAIL)
//    public Queue emailQueue(){
//        return new Queue(QUEUE_EMAIL);
//    }
//
//    @Bean(QUEUE_SMS)
//    public Queue emailSms(){
//        return new Queue(QUEUE_SMS);
//    }
//
//
//    @Bean
//    public Binding bindingEmail(@Qualifier(QUEUE_EMAIL) Queue queue, @Qualifier(EXCHANGE_NAME) Exchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_EMAIL).noargs();
//    }
//
//    @Bean
//    public Binding bindingSms(@Qualifier(QUEUE_SMS) Queue queue, @Qualifier(EXCHANGE_NAME) Exchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_SMS).noargs();
//    }
//}
