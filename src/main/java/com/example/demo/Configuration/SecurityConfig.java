package com.example.demo.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/*@Configuration
@EnableWebSecurity
@Import(Encoder.class)*/
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/{

	/*@Autowired
	private UserDetailsService userDetailService;
	
	@Bean
	public BCryptPasswordEncoder bCriyptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
			.authorizeRequests()
				.antMatchers("/resources/**", "/registration").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailService).passwordEncoder(bCriyptPasswordEncoder());
	}*/
}
