package br.com.tecsoluction.sol;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import br.com.tecsoluction.sol.servico.imp.RoleServicoImpl;
import br.com.tecsoluction.sol.servico.imp.UsuarioServicoImpl;


@SpringBootApplication(scanBasePackages = "br.com.tecsoluction.sol")
@EntityScan(basePackages = { "br.com.tecsoluction.sol.entidade" })
@EnableJpaRepositories(basePackages = { "br.com.tecsoluction.sol.dao" })
@ComponentScan(basePackages = {"br.com.tecsoluction.sol.controller"})
@Import({ConfiguracaoSecurity.class,WebConfig.class,DataSourceConf.class})
public class SolApplication  extends SpringBootServletInitializer {

	
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SolApplication.class);
    }
    
  
    
	@Bean
	 public MultipartConfigElement multipartConfigElement() {
	    
		 MultipartConfigFactory factory = new MultipartConfigFactory();
	        factory.setMaxFileSize("5120MB");
	        factory.setMaxRequestSize("5120MB");
	        return factory.createMultipartConfig();
		
		
	 }

	 
	 @Bean
	 public StandardServletMultipartResolver multipartResolverServelet() {
	     return new StandardServletMultipartResolver();
	 }
	
	 @Bean
	 public CommonsMultipartResolver multipartResolver() {
		 CommonsMultipartResolver multipart = new CommonsMultipartResolver();
		 multipart.setMaxUploadSize(5 * 1024 * 1024);
		 return multipart;
		 }
	 
	 @Bean 
	 @Order(0) 
	 public MultipartFilter multipartFilter() {
		 MultipartFilter multipartFilter = new MultipartFilter();
		 multipartFilter.setMultipartResolverBeanName("multipartReso‌‌​​lver");
		 return multipartFilter;
		 
	 }
	
//	@Bean(name = "servicoNotificao")
//    public ServicoNotificacaoImpl getservico() {
//       return new ServicoNotificacaoImpl();
//    }
	
//	@Bean(name = "usuarioServico")
//    public UsuarioServicoImpl getservicoUsuario() {
//       return new UsuarioServicoImpl();
//    }
//	
//	@Bean(name = "roleServico")
//    public RoleServicoImpl getservicoRole() {
//       return new RoleServicoImpl();
//    }
	
	

	public static void main(String[] args) {
		SpringApplication.run(SolApplication.class, args);
	

	
	}
	
	
	
	
}
