package br.com.tecsoluction.sol;


import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataSourceConf {
		
	
	    @Bean
	    @Primary
//	    @ConfigurationProperties(prefix = "spring.datasource")
	    public static DataSource dataSource() {
	   
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("org.postgresql.Driver");
	        dataSource.setUrl("jdbc:postgresql://ec2-184-73-159-137.compute-1.amazonaws.com:5432/d2imdq1tlu3415");
	        dataSource.setUsername("sbiymdvltaunnx");
	        dataSource.setPassword("a8cfe732a24937cda48d29b651645a84a3efc82966051a0f1b7a29e19a9af16c");

	    	return dataSource;
	    }
	    
	    @Bean
	    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){


	        LocalContainerEntityManagerFactoryBean lcemfb
	                = new LocalContainerEntityManagerFactoryBean();

	        lcemfb.setDataSource(dataSource());
	        lcemfb.setPackagesToScan(new String[] {"br.com.tecsoluction.sol.entidade"});

	        lcemfb.setPersistenceUnitName("PU-SOL");

	        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
	        lcemfb.setJpaVendorAdapter(va);
//	        va.setDatabase(Database.MYSQL);
	        va.setDatabase(Database.POSTGRESQL);

	        va.setGenerateDdl(true);
	        va.setShowSql(true);
//	        va.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
	        va.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
	        Properties ps = new Properties();
//	        ps.put("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	        ps.put("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
	        ps.put("spring.jpa.hibernate.ddl-auto", "update");
			ps.put("useSSL","false");
//			ps.put("security.basic.enabled","false");

			
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
