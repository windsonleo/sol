package br.com.tecsoluction.sol;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Import({ThymeleafeConf.class})
public class WebConfig extends WebMvcConfigurerAdapter {
	
	  private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
	            "classpath:/resources/", "classpath:/resources/",
	            "classpath:/static/", "classpath:/templates/", "classpath:/web/"
	    };
	 

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/web/**").addResourceLocations("classpath:/static/web/");
        registry.addResourceHandler("**/favicon.ico").addResourceLocations("classpath:/img/favicon.ico");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/build/**").addResourceLocations("classpath:/static/web/build/");
        registry.addResourceHandler("/vendors/**").addResourceLocations("classpath:/static/web/vendors/");      
        registry.addResourceHandler("/audio/**").addResourceLocations("classpath:/static/audio/");
        registry.addResourceHandler("/error/**").addResourceLocations("classpath:/templates/error/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");

    	
    }
    
    

    

}
