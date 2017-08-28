package com.greenbird.config;

import org.apache.camel.component.atmosphere.websocket.CamelWebSocketServlet;
import org.atmosphere.cpr.ContainerInitializer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class SpringRegistrationBeanServlet {

    @Bean
    public EmbeddedAtmosphereInitializer atmosphereInitializer() {
        return new EmbeddedAtmosphereInitializer();
    }

    @Bean
    public ServletRegistrationBean camelWebSocketServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new CamelWebSocketServlet(), "/camel/*");

        //!!! Remember always set name "CamelWsServlet" - nothing else should be used.
        // If you use another name your consumer will not be registered and nothing will work.
        bean.setName("CamelWsServlet");
        bean.setLoadOnStartup(0);
        // Need to occur before the EmbeddedAtmosphereInitializer
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    private static class EmbeddedAtmosphereInitializer extends ContainerInitializer
            implements ServletContextInitializer {

        @Override
        public void onStartup(ServletContext servletContext) throws ServletException {
            onStartup(Collections.<Class<?>> emptySet(), servletContext);
        }
    }
}
