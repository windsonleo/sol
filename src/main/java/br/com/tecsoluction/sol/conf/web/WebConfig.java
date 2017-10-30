package br.com.tecsoluction.sol.conf.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import br.com.tecsoluction.sol.conf.security.ConfiguracaoSecurity;

@Configuration
//@EnableWebMvc
//@EnableWebSecurity
//@ComponentScan(basePackages = "br.com.tecsoluction.sol")
//@Import({ConfiguracaoSecurity.class,ThymeleafeConf.class})
public class WebConfig extends WebMvcConfigurerAdapter {
	
	
//	@Bean(name = "multipartResolver")
//	    public StandardServletMultipartResolver resolver() {
//	        return new StandardServletMultipartResolver();
//	    }
	 
	 
//	    @Bean
//	    public MessageSource messageSource() {
//	        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//	        messageSource.setBasename("messages");
//	        return messageSource;
//	    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/src/main/resources/");
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/resources/static/");
//        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/resources/templates/");
//        registry.addResourceHandler("/web/**").addResourceLocations("classpath:/resources/static/web/");
        registry.addResourceHandler("**/favicon.ico").addResourceLocations("classpath:/src/main/resources/static/img/favicon.ico");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/build/**").addResourceLocations("classpath:/resources/static/web/build");
//        registry.addResourceHandler("/vendors/**").addResourceLocations("classpath:/resources/static/web/vendors/");
//        registry.addResourceHandler("/audio/**").addResourceLocations("classpath:/resources/static/audio/");
//        registry.addResourceHandler("/error/**").addResourceLocations("classpath:/resources/templates/error/");
//        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/resources/static/css/");
//        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/resources/static/js/");

   
    }
    
    
    

}
