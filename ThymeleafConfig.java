package com.firstPro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Configuration
public class ThymeleafConfig implements WebMvcConfigurer {

    @Bean
    public org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver templateResolver() {
        org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver resolver = new org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/");// Set the correct prefix for your templates directory
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");
        resolver.setCacheable(false); // Disable template caching for development
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/static/");
    }
}
