package com.system.neusoftpractice.config;

import com.system.neusoftpractice.common.JacksonObjectHandler;
import com.system.neusoftpractice.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

// webMvc配置
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    // 添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor()); // 添加用户拦截器
    }

    // 扩展消息转换器
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setObjectMapper(new JacksonObjectHandler());
        converters.add(0, messageConverter);
    }
}
