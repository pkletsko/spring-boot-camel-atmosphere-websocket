package com.greenbird.websocket.camel.processor.common;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.atmosphere.websocket.WebsocketConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static org.apache.camel.component.atmosphere.websocket.WebsocketConstants.ONCLOSE_EVENT_TYPE;
import static org.apache.camel.component.atmosphere.websocket.WebsocketConstants.ONERROR_EVENT_TYPE;
import static org.apache.camel.component.atmosphere.websocket.WebsocketConstants.ONOPEN_EVENT_TYPE;

@Component
public class WebsocketSessionProcessor implements Processor {
    private static final Logger LOG = LoggerFactory.getLogger(WebsocketSessionProcessor.class);

    public void process(Exchange exchange) throws Exception {
        int eventType = (int) exchange.getIn().getHeader(WebsocketConstants.EVENT_TYPE);
        String connectionKey = (String) exchange.getIn().getHeader(WebsocketConstants.CONNECTION_KEY);
        LOG.info("Event notification from websocket client: " + eventType);

        switch (eventType){
            case ONOPEN_EVENT_TYPE :
                LOG.info("Connection has been established successfully for next connection key: " + connectionKey);
                break;
            case ONCLOSE_EVENT_TYPE :
                LOG.info("Connection has been closed successfully for next connection key: " + connectionKey);
                break;
            case ONERROR_EVENT_TYPE :
                LOG.info("An error event has been triggered for next connection key: " + connectionKey);
                break;
            default:
                LOG.info("Event notification from websocket client is Unknown.");
        }


    }
}
