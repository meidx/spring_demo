package com.xkcoding.mq.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @author: mdx
 * @date: Created in 2020/1/15 0015 14:51
 * @version: V1.0
 */
@Slf4j
@Configurable
public class MyRabbitMqConfig {
    public static String D_Q = "dq";
    public static String F_Q = "fq";
    public static String T_Q = "tq";

    public static String D_E = "de";
    public static String F_E = "fe";
    public static String T_E = "te";

    public static String R_K_D = "rkd";
    public static String R_K_F = "rkf";
    public static String R_K_T = "rkt";

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;
    }

    @Bean
    public Queue DQ() {
        return new Queue(D_Q);
    }

    @Bean
    public Queue FQ() {
        return new Queue(F_Q);
    }

    @Bean
    public Queue TQ() {
        return new Queue(T_Q);
    }

    @Bean
    public Exchange DE() {
        return new DirectExchange(D_E);
    }

    @Bean
    public Exchange getFE() {
        return new FanoutExchange(F_E);
    }

    @Bean
    public Exchange getTE() {
        return new TopicExchange(T_E);
    }

    @Bean
    public Binding db222(Queue DQ, DirectExchange DE) {
        return BindingBuilder.bind(DQ).to(DE).with("");
    }
}
