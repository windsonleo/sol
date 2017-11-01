package br.com.tecsoluction.sol.conf.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/src/main/resources/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/src/main/resources/templates/");
        registry.addResourceHandler("/web/**").addResourceLocations("classpath:/src/main/resources/static/web/");
        registry.addResourceHandler("**/favicon.ico").addResourceLocations("classpath:/resources/static/img/favicon.ico");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/build/**").addResourceLocations("classpath:/src/main/resources/static/web/build");
        registry.addResourceHandler("/vendors/**").addResourceLocations("classpath:/src/main/resources/static/web/vendors/");
        registry.addResourceHandler("/audio/**").addResourceLocations("classpath:/src/main/resources/static/audio/");
        registry.addResourceHandler("/error/**").addResourceLocations("classpath:/src/main/resources/templates/error/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/src/main/resources/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/src/main/resources/static/js/");

   
    }
    
    
    

}
