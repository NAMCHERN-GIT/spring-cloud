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

    @GetMapping("send")
    public void send(){
        streamClient.output().send(MessageBuilder.withPayload("Hello World...").build());
    }

}
