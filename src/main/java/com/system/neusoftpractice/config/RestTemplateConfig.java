package com.system.neusoftpractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

//
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        // 创建一个RestTemplate实例，使用指定的ClientHttpRequestFactory
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        // 创建一个SimpleClientHttpRequestFactory实例
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        // 超时设置
        // 设置读取超时时间为6000毫秒
        factory.setReadTimeout(6000);
        // 设置连接超时时间为10000毫秒
        factory.setConnectTimeout(10000);

        return factory;
    }
}
