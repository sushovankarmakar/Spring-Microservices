package com.tavisca.gce.DBAccessAPI.config;

import com.tavisca.gce.DBAccessAPI.interceptor.InputAccessRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InputAccessRequestInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    InputAccessRequestInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(interceptor);
    }
}
