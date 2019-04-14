package org.opensoft.projecte.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private RestAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private RestAuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private RestAuthenticationSuccessHandler authenticationSuccessHandler;

	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource);
		return jdbcUserDetailsManager;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
		    .exceptionHandling()
		    .authenticationEntryPoint(authenticationEntryPoint)
		.and()
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/admin").access("hasRole('ADMIN')")
			.antMatchers("/sudo").access("hasRole('GOD')")
			.anyRequest().authenticated()
		.and()
			.formLogin()
			.permitAll()
			.successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailureHandler)
		.and()
			.logout()
			.permitAll();
	}
}
