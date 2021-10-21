package com.test.webtest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry){
        /* Configuration for path when download secure QR Image */
        registry.addResourceHandler("/qrImg/**")
                .addResourceLocations("file:///c:/TestQR/qrImg/");
    }
}
