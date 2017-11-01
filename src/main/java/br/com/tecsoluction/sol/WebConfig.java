package br.com.tecsoluction.sol;

import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
//@EnableWebSecurity
@ComponentScan(basePackages = "br.com.tecsoluction.sol")
@Import({ConfiguracaoSecurity.class,ThymeleafeConf.class,DataSourceConf.class})
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
	
	
//	 @Override
//	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//	        configurer.enable();
//	    }
	 

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/src/main/resources/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/src/main/resources/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/src/main/resources/templates/");
        registry.addResourceHandler("/web/**").addResourceLocations("classpath:/src/main/resources/static/web/");
        registry.addResourceHandler("**/favicon.ico").addResourceLocations("classpath:/resources/static/img/favicon.ico");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/build/**").addResourceLocations("classpath:/src/main/resources/static/web/build");
        registry.addResourceHandler("/vendors/**").addResourceLocations("classpath:/src/main/resources/static/web/vendors/");
        registry.addResourceHandler("/audio/**").addResourceLocations("classpath:/src/main/resources/static/audio/");
        registry.addResourceHandler("/error/**").addResourceLocations("classpath:/src/main/resources/templates/error/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/src/main/resources/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/src/main/resources/static/js/");

   
    }
    
    @Bean
 	public DispatcherServlet dispatcherServlet() {
 		return new DispatcherServlet();
 	}

 	@Bean
 	public ServletRegistrationBean dispatchServletRegistration() {

 		ServletRegistrationBean registration = new ServletRegistrationBean(
 				dispatcherServlet(), "/*");

 		registration
 				.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME);

 		return registration;

 	}
    

}
