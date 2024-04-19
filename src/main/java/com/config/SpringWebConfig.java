package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@EnableTransactionManagement
@ComponentScan("com.config.*")
public class SpringWebConfig extends WebMvcConfigurerAdapter 
{

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		System.out.println("-------------------C----------------");
	    //registry.addResourceHandler("/files/**").addResourceLocations("file:/opt/files/");
	}
	
		
	@Bean
	public InternalResourceViewResolver viewResolver() 
	{
		System.out.println("-------------------B----------------");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	
  

}