package com.chennan.cloud.rabbit.client;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamClient {

    @Input("myInput")
    SubscribableChannel input();

    @Output("myOutput")
    MessageChannel output();
}
