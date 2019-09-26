package com.chennan.cloud.rabbit.web;

import com.chennan.cloud.rabbit.client.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/msc/stream/")
@RestController
public class StreamController {

    @Autowired private StreamClient streamClient;

    /**
     * 生产者生产消息到 rabbitMQ 中
     * @param msg 消息内容
     */
    @GetMapping("send")
    public void send(String msg){
        streamClient.output().send(MessageBuilder.withPayload(msg).build());
    }

}
