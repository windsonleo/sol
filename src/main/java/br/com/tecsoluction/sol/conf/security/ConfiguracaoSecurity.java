package br.com.tecsoluction.sol.conf.security;

import javax.servlet.MultipartConfigElement;
import javax.servlet.Registration.Dynamic;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebSecurity
//@EnableWebMvc
public class ConfiguracaoSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
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
				.dataSource(dataSource);
//				.passwordEncoder(bCryptPasswordEncoder);
				
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.
			authorizeRequests()
				.antMatchers("/resources/**","/static/**","/web/**","/build/**","/vendors/**","/img/**","/templates/**","/webjars/**","/js/**","/css/**").permitAll()
				.antMatchers("/login").permitAll()
//				.antMatchers("/registration").permitAll()
				.antMatchers("/home").hasRole("MEMBRO")

				.antMatchers("/adm/**").hasAuthority("ADM").anyRequest()
				.authenticated().and().csrf().disable().formLogin()
				.loginPage("/login").failureUrl("/erro")
				.defaultSuccessUrl("/home")
				.usernameParameter("username")
				.passwordParameter("senha")
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").and().exceptionHandling()
				.accessDeniedPage("/accessdenied");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**","/webjars/**", "/web/**","/build/**","/vendors/**","/audio/**","/templates/**");
	
//	    web.ignoring()
//	    	.antMatchers("/web/**","/js/**","/css/**","/build/**","/vendors/**");
	}
	

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("MEMBRO")
//                .and()
//                .withUser("admin").password("password").roles("ADM");
//    }
//	
	
	
}
