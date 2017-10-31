package br.com.tecsoluction.sol.conf;


import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConf {
		
	
	    @Bean
	    @Primary
	    @ConfigurationProperties(prefix = "spring.datasource")
	    public static DataSource dataSource() {
	        return DataSourceBuilder.create().build();
	    }


}
