package com.system.neusoftpractice.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration // 标明是配置类
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public OpenAPI swaggerOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("NEUSoft Practice 2024")
                        .description("NEUSoft Practice 2024")
                        .version("v1.0"));
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}
