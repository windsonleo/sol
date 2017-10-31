package br.com.tecsoluction.sol;

import java.util.Properties;

import javax.servlet.MultipartConfigElement;
import javax.servlet.Registration.Dynamic;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import br.com.tecsoluction.sol.conf.DataSourceConf;
import br.com.tecsoluction.sol.conf.security.ConfiguracaoSecurity;
import br.com.tecsoluction.sol.conf.web.MultPartResolver;
import br.com.tecsoluction.sol.conf.web.ThymeleafeConf;
import br.com.tecsoluction.sol.conf.web.WebConfig;
import br.com.tecsoluction.sol.servico.imp.RoleServicoImpl;
import br.com.tecsoluction.sol.servico.imp.ServicoNotificacaoImpl;
import br.com.tecsoluction.sol.servico.imp.UsuarioServicoImpl;

//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "br.com.tecsoluction.sol")
@EntityScan(basePackages = { "br.com.tecsoluction.sol.entidade" })
@EnableJpaRepositories(basePackages = { "br.com.tecsoluction.sol.dao" })
@ComponentScan(basePackages = {"br.com.tecsoluction.sol"})
//@Import({WebConfig.class,ConfiguracaoSecurity.class, ThymeleafeConf.class})
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

//	 @Bean
//	 public MultipartResolver multipartResolverr() {
//	     org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
//	     multipartResolver.setMaxUploadSize(1000000);
//	     return multipartResolver;
//	 }
	 
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
	
	@Bean(name = "servicoNotificao")
    public ServicoNotificacaoImpl getservico() {
       return new ServicoNotificacaoImpl();
    }
	
	@Bean(name = "usuarioServico")
    public UsuarioServicoImpl getservicoUsuario() {
       return new UsuarioServicoImpl();
    }
	
	@Bean(name = "roleServico")
    public RoleServicoImpl getservicoRole() {
       return new RoleServicoImpl();
    }
	
	
//	@Bean(name="dataSource")
//    public DataSource dataSource(Environment environment) {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/sol?useSSL=false");
//        dataSource.setUsername("root");
//        dataSource.setPassword("123456");
//        return dataSource;
//    }
	
	
//	@Bean(name="dataSource")
//    public DataSource dataSource(Environment environment) {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://sbiymdvltaunnx:a8cfe732a24937cda48d29b651645a84a3efc82966051a0f1b7a29e19a9af16c@ec2-184-73-159-137.compute-1.amazonaws.com:5432/d2imdq1tlu3415");
//        dataSource.setUsername("sbiymdvltaunnx");
//        dataSource.setPassword("a8cfe732a24937cda48d29b651645a84a3efc82966051a0f1b7a29e19a9af16c");
//        return dataSource;
//    }

	public static void main(String[] args) {
		SpringApplication.run(SolApplication.class, args);
	

	
	}
	
	
//	@Bean
//    public MultipartResolver multipartResolver() {
//        return new StandardServletMultipartResolver();
//    }
	
	
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){


        LocalContainerEntityManagerFactoryBean lcemfb
                = new LocalContainerEntityManagerFactoryBean();

        lcemfb.setDataSource(DataSourceConf.dataSource());
        lcemfb.setPackagesToScan(new String[] {"br.com.tecsoluction.sol.entidade"});

        lcemfb.setPersistenceUnitName("PU-SOL");

        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        lcemfb.setJpaVendorAdapter(va);
//        va.setDatabase(Database.MYSQL);
        va.setDatabase(Database.POSTGRESQL);

        va.setGenerateDdl(true);
        va.setShowSql(true);
//        va.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        va.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        Properties ps = new Properties();
//        ps.put("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        ps.put("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        ps.put("spring.jpa.hibernate.ddl-auto", "create");
		ps.put("useSSL","false");
//		ps.put("security.basic.enabled","false");

		
		ps.put("spring.jpa.properties.hibernate.format_sql","true");
		ps.put("spring.datasource.validationQuery","SELECT 1");
		ps.put("spring.thymeleaf.cache","false");
		ps.put("security.basic.enabled","false");

		
		
		
		
        lcemfb.setJpaProperties(ps);

        lcemfb.afterPropertiesSet();

        return lcemfb;

    }



    @Bean
    public PlatformTransactionManager transactionManager(){

        JpaTransactionManager tm = new JpaTransactionManager();

        tm.setEntityManagerFactory(
                this.entityManagerFactory().getObject() );

        return tm;

    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
	
	
}
