package com.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com")
public class WebAppConfig extends WebMvcConfigurerAdapter {
 public WebAppConfig(){
	 System.out.println("webappconfig is instantiated");
 }
}
