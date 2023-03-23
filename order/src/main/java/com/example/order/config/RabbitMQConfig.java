package com.example.order.config;

@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMQConfig {

    private String host;
    private String username;
    private String password;
    private int port;
    private String exchangeName;
    private String notificationQueueName;
    private String notificationRoutingKey;
    private String padQueueName;
    private String padRoutingKey;
    private String productQueueName;
    private String productRoutingKey;

    // getters and setters

}