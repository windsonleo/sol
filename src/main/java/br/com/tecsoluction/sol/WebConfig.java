package br.com.tecsoluction.sol;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Import({ThymeleafeConf.class})
public class WebConfig extends WebMvcConfigurerAdapter {
	
	  private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
	            "classpath:/META-INF/resources/", "classpath:/resources/",
	            "classpath:/resources/static/", "classpath:/resources/templates/", "classpath:/resources/static/web/"
	    };
	 

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
        registry.addResourceHandler("/static/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
        registry.addResourceHandler("/templates/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
        registry.addResourceHandler("/web/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
        registry.addResourceHandler("**/favicon.ico").addResourceLocations("classpath:/resources/static/img/favicon.ico");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/build/**").addResourceLocations("classpath:/resources/static/web/build/");
        registry.addResourceHandler("/vendors/**").addResourceLocations("classpath:/resources/static/web/vendors/");      
        registry.addResourceHandler("/audio/**").addResourceLocations("classpath:/resources/static/audio/");
        registry.addResourceHandler("/error/**").addResourceLocations("classpath:/resources/templates/error/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/resources/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/resources/static/js/");

    	
    }

    

}
