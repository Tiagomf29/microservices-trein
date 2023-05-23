package com.msstock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	                .allowedOrigins("http://localhost:8081")
	                .allowedMethods("GET", "POST", "PUT", "PATH","DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
	    }
	    
	    @Bean
	    CorsFilter corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
	        config.addAllowedMethod(HttpMethod.PUT);
	        config.addAllowedMethod(HttpMethod.DELETE);
	        config.addAllowedMethod(HttpMethod.PATCH);
	        config.addAllowedMethod(HttpMethod.POST);
	        source.registerCorsConfiguration("/**", config);
	        return new CorsFilter(source);
	    }
	
}
