package com.ll.jwt.config;

import com.ll.jwt.filter.MyFilter1;
import com.ll.jwt.filter.MyFilter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    //필터 체인에 거는 것이 아니라 새로운 필터를 만드는 방법

    @Bean
    public FilterRegistrationBean<MyFilter1> filter1() {
        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
        bean.addUrlPatterns("/*"); //모든 url에서 다해라
        bean.setOrder(0); // 낮은 번호가 필터중에서 가장 먼저 실행됨
        return bean;
    }

    @Bean
    public FilterRegistrationBean<MyFilter2> filter2() {
        FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>(new MyFilter2());
        bean.addUrlPatterns("/*"); //모든 url에서 다해라
        bean.setOrder(1); // 낮은 번호가 필터중에서 가장 먼저 실행됨
        return bean;
    }
}
