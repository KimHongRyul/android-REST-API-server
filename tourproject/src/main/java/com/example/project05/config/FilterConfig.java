package com.example.project05.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.project05.config.filter.MyFilter1;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<MyFilter1> filter1() {
		FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
		bean.addUrlPatterns("/*"); // 모든 요청에서 다 필터가 걸려라
		bean.setOrder(0); // 낮은 번호가 필터중 가장 먼저
		return bean;
	}
	
	
	
}
