package com.infodev.pscrms.security;

import com.infodev.pscrms.utilities.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private Environment env;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;



	private static final String[] PUBLIC_MATCHERS = {
			"/",
			"/lib/**",
			"/css/**",
			"/js/**",
			"/img/**",
			"/images/**",
			"**/",
			"/newUser",
			"/forgetPassword",
			"/login",
			"/getUserByUserId",
			"**/uploads/**",
			"/assets/**"


	};


	@Autowired
	private SecurityHandler securityHandler;


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.authorizeRequests()

		/*	antMatchers("/**").*/
			.antMatchers(PUBLIC_MATCHERS).
			permitAll().anyRequest().authenticated();

		http
			.authorizeRequests()
			.antMatchers("/admin").hasAnyRole("ROLE_ADMIN")
				.and()
			.formLogin().loginPage("/login").permitAll().failureUrl("/login?error")
			.successHandler(securityHandler)
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
			.and()
			.rememberMe();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(SecurityUtils.passwordEncoder());
	}
	@Override
	public void configure(WebSecurity web) throws  Exception{
		web.ignoring()
				.antMatchers("/api/updateCardStatus","/api/login","*/uploads/***","/api/getUsersDetail","/api/getStudentDetails","/api/getAccountLoad","/api/issueDirectives","/api/changePassword","/api/cardActivation","/api/CustomerAccountCardDetails","/api/accountLoad","/api/updateConsumersProfile","/api/verifyCvv"
						,"/api/updatePrepaidCardStatus","/api/getStatementData");
	}

}
