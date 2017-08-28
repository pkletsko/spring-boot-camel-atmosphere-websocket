package com.greenbird.websocket.camel.processor.common;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.atmosphere.websocket.WebsocketConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WebsocketNotDeliveredMessageProcessor implements Processor {
    private static final Logger LOG = LoggerFactory.getLogger(WebsocketNotDeliveredMessageProcessor.class);

    public void process(Exchange exchange) throws Exception {
        String clientMessage = exchange.getIn().getBody(String.class);
        String connectionKey = (String) exchange.getIn().getHeader(WebsocketConstants.CONNECTION_KEY);
        LOG.info("Message to connection key:" + connectionKey + ", has not been delivered. Message body: " + clientMessage);
    }
}
