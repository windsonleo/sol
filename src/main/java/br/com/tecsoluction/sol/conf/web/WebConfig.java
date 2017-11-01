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
        registry.addResourceHandler("/resources/**").addResourceLocations("/src/main/resources/");
        registry.addResourceHandler("/static/**").addResourceLocations("/resources/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("/resources/templates/");
        registry.addResourceHandler("/web/**").addResourceLocations("/resources/static/web/");
        registry.addResourceHandler("**/favicon.ico").addResourceLocations("/resources/static/img/favicon.ico");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/build/**").addResourceLocations("/resources/static/web/build");
        registry.addResourceHandler("/vendors/**").addResourceLocations("/resources/static/web/vendors/");
        registry.addResourceHandler("/audio/**").addResourceLocations("/resources/static/audio/");
        registry.addResourceHandler("/error/**").addResourceLocations("/resources/templates/error/");
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/static/js/");

   
    }
    
    
    

}
