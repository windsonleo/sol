package br.com.tecsoluction.sol;

import javax.servlet.MultipartConfigElement;
import javax.servlet.Registration.Dynamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication
public class SolApplication {
	
	
	@Bean(name = "multipartResolver")
    public StandardServletMultipartResolver resolver() {
       return new StandardServletMultipartResolver();
    }
	


	public static void main(String[] args) {
		SpringApplication.run(SolApplication.class, args);
	

	
	}
	
	
//	@Bean
//    public MultipartResolver multipartResolver() {
//        return new StandardServletMultipartResolver();
//    }
	
	
	
}
