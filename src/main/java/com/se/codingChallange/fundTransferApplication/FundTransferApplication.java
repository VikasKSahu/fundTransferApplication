package com.se.codingChallange.fundTransferApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.se.codingChallange"})
public class FundTransferApplication extends SpringBootServletInitializer {
	//private static ApplicationContext applicationContext;
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(FundTransferApplication.class);
	    }
	public static void main(String[] args) {
		//applicationContext = SpringApplication.run(FundTransferApplication.class, args);
       // displayAllBeans();
		SpringApplication.run(FundTransferApplication.class, args);
	}
//	 public static void displayAllBeans() {
//	        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
//	        for(String beanName : allBeanNames) {
//	            System.out.println(beanName);
//	        }
//	    }
}
