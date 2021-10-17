package com.woods.tcc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile("prod")
public class CorsConfig {
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer(){
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        .allowedMethods("GET","POST", "PUT", "DELETE")
        .allowedHeaders("*")
        .allowedOrigins("*");
      }
    };
  }
}
