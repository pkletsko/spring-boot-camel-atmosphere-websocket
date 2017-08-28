package com.greenbird.websocket.camel.processor.common;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WebsocketMessageProcessor implements Processor {
    private static final Logger LOG = LoggerFactory.getLogger(WebsocketMessageProcessor.class);

    public void process(Exchange exchange) throws Exception {
        String clientMessage = exchange.getIn().getBody(String.class);
        LOG.info("Message from websocket client: " + clientMessage);
    }
}
