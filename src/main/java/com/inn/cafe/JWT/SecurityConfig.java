package com.inn.cafe.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {  //this class is depricated
	
	
	@Autowired 
	CustomerUsersDetailsService customerUsersDetailsService;
	
	@Autowired
	JwtFilter jwtFilter;
	
	/*
	 * // @Override // protected void configure (AuthenticationManagerBuilder auth)
	 * throws Exception{ // auth.userDetailsService(customerUserDetailsService); //
	 * } instead
	 * 
	 */
	
	
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.customerUsersDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	
	  @Bean public PasswordEncoder passwordEncoder()
	  { return NoOpPasswordEncoder.getInstance(); } 
	 
	
	/*
	 * @Bean BCryptPasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */
	
	/*
	 * @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	 * 
	 * @Override public AuthenticationManager authenticationManagerBean() throws
	 * Exception{ return super.authenticationManagerBean(); }
	 */
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception{
		return configuration.getAuthenticationManager();
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.cors()   //.configurationSource(request-> new CorsConfiguration().applyPermitDefaultValues())
		.and()
        .csrf().disable()
        .authorizeRequests()
        .requestMatchers("/user/login", "/user/forgotPassword") //, "/user/signup"
        .permitAll()
        .anyRequest()
        .authenticated()
        .and().exceptionHandling()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.authenticationProvider(daoAuthenticationProvider());
		
		DefaultSecurityFilterChain defaultSecurityFilterChain =http.build();
		
		return defaultSecurityFilterChain;        
	}

}
























