package com.chennan.cloud.rabbit.receiver;

import com.chennan.cloud.rabbit.client.StreamClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 创建用于接收来自 RabbitMQ 消息的消费者 StreamReceiver 类
 * <p>
 *     {@link @EnableBinding} 注解用来指定一个或多个定义了 @Input 或 @Output 注解的接口，以此实现对消息通道（Channel）的绑定。
 *     通过 @EnableBinding(value = {StreamClient.class}) 绑定了 StreamClient 接口，该接口是我们自己实现的对输入输出消息通道绑定的定义
 * </p>
 * <p>
 *     {@link @StreamListener} 主要定义在方法上，作用是将被修饰的方法注册为消息中间件上数据流的事件监听器，注解中的属性值对应了监听的消息通道名。
 *     将 receive 方法注册为 myInput 消息通道的监听处理器，当我们往这个消息通道发送信息的时候，receiver 方法会执行。
 * </p>
 *
 * @author chen.nan
 */
@Slf4j
@Component
@EnableBinding(value = {StreamClient.class})
public class StreamReceiver {

    @StreamListener(StreamClient.INPUT)
    public void receive(String message){
        log.info("StreamReceiver: {}", message);
    }

}
