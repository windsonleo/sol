package br.com.tecsoluction.sol;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@ComponentScan("br.com.tecsoluction.sol")
public class ConfiguracaoSecurity extends WebSecurityConfigurerAdapter {

	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;	
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(DataSourceConf.dataSource());
//				.passwordEncoder(bCryptPasswordEncoder);
				
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
			authorizeRequests()
				.antMatchers("/resources/**","/static/**","*/web/**","*/build/**","*/vendors/**","*/img/**","/templates/**","/webjars/**","*/js/**","*/css/**","*/audio/**","**/favicon.ico").permitAll()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("*/static/**").permitAll()
				.antMatchers("*/templates/**").permitAll()
				.antMatchers("*/web/**").permitAll()
				.antMatchers("*/build/**").permitAll()
				.antMatchers("*/vendors/**").permitAll()
				.antMatchers("*/img/**").permitAll()
				.antMatchers("*/css/**").permitAll()
				.antMatchers("*/js/**").permitAll()

				.antMatchers("/login").permitAll()
//				.antMatchers("/registration").permitAll()
				.antMatchers("/home").hasRole("MEMBRO")

				.antMatchers("/usuario/**").hasAuthority("MEMBRO").anyRequest()
				.authenticated().and().csrf().disable().formLogin()
				.loginPage("/login").failureUrl("/error/erro")
				.defaultSuccessUrl("/usuario/home")
				.usernameParameter("username")
				.passwordParameter("senha")
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").and().exceptionHandling()
				.accessDeniedPage("/error/accessdenied");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**");
       web
	       .ignoring()
	       .antMatchers("*/static/**");
       
       web
       .ignoring()
       .antMatchers("*/css/**");
       
       web
       .ignoring()
       .antMatchers("*/js/**");
       
       web
       .ignoring()
       .antMatchers("*/img/**");
       
       web
       .ignoring()
       .antMatchers("*/web/**");
       
       web
       .ignoring()
       .antMatchers("*/build/**");
       
       web
       .ignoring()
       .antMatchers("*/vendors/**");
       
       web
       .ignoring()
       .antMatchers("*/audio/**");
       
       web
       .ignoring()
       .antMatchers("*/templates/**");
       
       web
       .ignoring()
       .antMatchers("*/audio/**");

	}
	

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("MEMBRO")
                .and()
                .withUser("admin").password("password").roles("ADM");
    }
	
	
}
