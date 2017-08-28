package com.greenbird.websocket.camel.route;


import com.greenbird.websocket.camel.processor.common.WebsocketMessageProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainRouteBuilder extends RouteBuilder {

    @Autowired
    private WebsocketMessageProcessor websocketMessageProcessor;

    public void configure() {
        from("atmosphere-websocket:///chat")
            .process(websocketMessageProcessor);
    }
}