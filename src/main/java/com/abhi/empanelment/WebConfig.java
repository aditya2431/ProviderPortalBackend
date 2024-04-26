package com.abhi.empanelment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.InetSocketAddress;

@Configuration
public class WebConfig {

//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
    
   @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("185.46.212.88", 80));
        requestFactory.setProxy(proxy);

        return new RestTemplate(requestFactory);
    } 
}