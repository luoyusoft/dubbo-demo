package com.jinhaoxun.dubbo.org.rocketmq;

import com.jinhaoxun.dubbo.module.rocketmq.model.request.AddConsumerReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author jinhaoxun
 * @date 2019-08-09
 * @description Rocketmq消费者工厂类
 */
@Slf4j
@Component
public class RocketmqConsumerFactory {

    @Value("${rocketmq.consumer.groupname}")
    private String consumerGroupName;
    @Value("${rocketmq.namesrvaddr}")
    private String nameServerAddr;
    @Value("${rocketmq.consumer.consumethreadmin}")
    private int consumeThreadMin;
    @Value("${rocketmq.consumer.consumethreadmax}")
    private int consumeThreadMax;
    @Value("${rocketmq.consumer.topicname}")
    private String topicName;
    @Autowired
    private DefaultMQPushConsumer defaultMQPushConsumer;

    /**
     * @author  jinhaoxun
     * @description 配置DefaultMQPushConsumer，启动项目时默认消费者服务
     * @return DefaultMQPushConsumer
     */
    @Bean
    public DefaultMQPushConsumer getRocketmqConsumer() throws Exception {
        log.info("开始启动Rocketmq消费者服务...");
        //订阅指定 Topic 下的所有消息
        setRocketmqConsumer();
        defaultMQPushConsumer.subscribe(topicName, "*");
        // 消费者对象在使用之前必须要调用 start 初始化
        defaultMQPushConsumer.start();
        log.info("Rocketmq消费者服务启动成功！");
        return defaultMQPushConsumer;
    }

    /**
     * @author  jinhaoxun
     * @description 配置DefaultMQPushConsumer，手动启动消费者服务
     * @param addConsumerReq 消费者的配置
     * @return DefaultMQPushConsumer
     */
    public DefaultMQPushConsumer startRocketmqConsumer(AddConsumerReq addConsumerReq) throws Exception {
        log.info("开始启动Rocketmq消费者服务...");
        consumerGroupName = addConsumerReq.getConsumerGroup();
        setRocketmqConsumer();
        //订阅指定 Topic 下的所有消息
        defaultMQPushConsumer.subscribe(addConsumerReq.getTopic(), "*");
        //设置SQL过滤
        //defaultMQPushConsumer.subscribe(addConsumerReq.getTopic(), MessageSelector.bySql("i>5"));
        // 消费者对象在使用之前必须要调用 start 初始化
        defaultMQPushConsumer.start();
        log.info("Rocketmq消费者服务启动成功！");
        return defaultMQPushConsumer;
    }

    /**
     * @author  jinhaoxun
     * @description 设置消费者的信息
     */
    private void setRocketmqConsumer() {
        //创建一个消息消费者，并设置一个消息消费者组
        defaultMQPushConsumer = new DefaultMQPushConsumer(consumerGroupName);
        //指定 NameServer 地址
        defaultMQPushConsumer.setNamesrvAddr(nameServerAddr);
        //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        defaultMQPushConsumer.setConsumeThreadMin(consumeThreadMin);
        defaultMQPushConsumer.setConsumeThreadMax(consumeThreadMax);
        defaultMQPushConsumer.setVipChannelEnabled(false);
        //设置消费模式，广播或负载均衡，默认为负载均衡
        //defaultMQPushConsumer.setMessageModel(MessageModel.BROADCASTING);
        RocketmqMessageListener rocketmqMessageListener = new RocketmqMessageListener();
        //注册消息监听器
        defaultMQPushConsumer.registerMessageListener(rocketmqMessageListener);
    }

    /**
     * @author  jinhaoxun
     * @description 关闭消费者服务
     */
    public void destroyRocketmqConsumer(){
        log.info("开始关闭Rocketmq消费者服务...");
        defaultMQPushConsumer.shutdown();
        log.info("Rocketmq消费者服务已关闭！");
    }
}
