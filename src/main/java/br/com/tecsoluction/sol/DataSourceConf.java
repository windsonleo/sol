package br.com.tecsoluction.sol;


import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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


}
