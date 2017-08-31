package com.greenbird.websocket.camel.route;


import com.greenbird.websocket.camel.processor.common.WebsocketMessageProcessor;
import com.greenbird.websocket.camel.processor.common.WebsocketNotDeliveredMessageProcessor;
import com.greenbird.websocket.camel.processor.common.WebsocketSessionProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.atmosphere.websocket.WebsocketConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainRouteBuilder extends RouteBuilder {

    @Autowired
    private WebsocketSessionProcessor websocketSessionProcessor;

    @Autowired
    private WebsocketMessageProcessor websocketMessageProcessor;

    @Autowired
    private WebsocketNotDeliveredMessageProcessor websocketNotDeliveredMessageProcessor;

    public void configure() {
        from("atmosphere-websocket:///chat")
                .choice()
                    .when(header(WebsocketConstants.EVENT_TYPE).isEqualTo(WebsocketConstants.ONOPEN_EVENT_TYPE))
                        .process(websocketSessionProcessor)
                        .to("atmosphere-websocket:///chat")
                    .when(header(WebsocketConstants.EVENT_TYPE).isEqualTo(WebsocketConstants.ONCLOSE_EVENT_TYPE))
                        .process(websocketSessionProcessor)
                    .when(header(WebsocketConstants.EVENT_TYPE).isEqualTo(WebsocketConstants.ONERROR_EVENT_TYPE))
                        .process(websocketSessionProcessor)
                    .when(header(WebsocketConstants.ERROR_TYPE).isEqualTo(WebsocketConstants.MESSAGE_NOT_SENT_ERROR_TYPE))
                        .process(websocketNotDeliveredMessageProcessor)
                    .otherwise()
                        .process(websocketMessageProcessor).to("seda:messages");
    }
}