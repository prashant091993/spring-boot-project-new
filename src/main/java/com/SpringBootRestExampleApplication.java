package com;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@SpringBootApplication(scanBasePackages={"com.*"})
@EnableEurekaClient
@EnableDiscoveryClient
public class SpringBootRestExampleApplication extends SpringBootServletInitializer
{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestExampleApplication.class, args);
		System.out.println("---SpringBootRestExampleApplication is up and running---");
		
				
    }
}
	
	