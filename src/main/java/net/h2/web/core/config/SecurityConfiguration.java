package net.h2.web.core.config;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import net.h2.web.sec.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "net.h2.web")
@PropertySources(@PropertySource("classpath:application.properties"))
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//	private static final String CAS_URL_LOGIN = "https://localhost:8443/cas/login";
//	private static final String CAS_URL_LOGOUT = "https://localhost:8443/cas/logout";
//	private static final String CAS_URL_PREFIX = "https://localhost:8443/cas";
//	private static final String CAS_SERVICE_URL = "http://localhost:8080/mobagem/j_spring_cas_security_check";
//	private static final String APP_SERVICE_HOME = "http://localhost:8080/mobagem";

	// apache(httpd) server on port 8083
//	private static final String CAS_URL_LOGIN = "http://localhost:8083/cas/login";
//	private static final String CAS_URL_LOGOUT = "http://localhost:8083/cas/logout";
//	private static final String CAS_URL_PREFIX = "http://localhost:8083/cas";
//	private static final String CAS_SERVICE_URL = "http://localhost:8084/mobagem/j_spring_cas_security_check";
//	private static final String APP_SERVICE_HOME = "http://localhost:8084/mobagem";

	// apache(httpd) server on port 443(https)
	private static final String CAS_URL_LOGIN = "https://localhost:443/cas/login";
	private static final String CAS_URL_LOGOUT = "https://localhost:443/cas/logout";
	private static final String CAS_URL_PREFIX = "https://localhost:443/cas";
	private static final String CAS_SERVICE_URL = "https://localhost:8446/mobagem/j_spring_cas_security_check";
	private static final String APP_SERVICE_HOME = "https://localhost:8446/mobagem";
	
	private static final String APP_ADMIN_USER_NAME = "admin";

//	@Inject
//	private Environment env;

	/*
	 * @Inject private AjaxAuthenticationSuccessHandler
	 * ajaxAuthenticationSuccessHandler;
	 * 
	 * @Inject private AjaxAuthenticationFailureHandler
	 * ajaxAuthenticationFailureHandler;
	 * 
	 * @Inject private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;
	 */

	// @Inject
	// private CasAuthenticationProvider authProvider;

	@Bean
	public Set<String> adminList() {
		Set<String> admins = new HashSet<String>();
		String adminUserName = APP_ADMIN_USER_NAME;

		admins.add("admin");

		if (adminUserName != null && !adminUserName.isEmpty()) {
			admins.add(adminUserName);
		}
		return admins;
	}

	@Bean
	public ServiceProperties serviceProperties() {
		ServiceProperties sp = new ServiceProperties();
		sp.setService(CAS_SERVICE_URL);
		sp.setSendRenew(false);
		return sp;
	}

	@Bean
	public CasAuthenticationProvider casAuthenticationProvider() {
		CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
		casAuthenticationProvider.setAuthenticationUserDetailsService(customUserDetailsService());
		casAuthenticationProvider.setServiceProperties(serviceProperties());
		casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
		casAuthenticationProvider.setKey("an_id_for_this_auth_provider_only");
		return casAuthenticationProvider;
	}

	@Bean
	public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> customUserDetailsService() {
		return new CustomUserDetailsService(adminList());
	}

	@Bean
	public SessionAuthenticationStrategy sessionStrategy() {
		SessionAuthenticationStrategy sessionStrategy = new SessionFixationProtectionStrategy();
		return sessionStrategy;
	}

	@Bean
	public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
		return new Cas20ServiceTicketValidator(CAS_URL_PREFIX);
	}

	@Bean
	public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
		CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
		casAuthenticationFilter.setAuthenticationManager(authenticationManager());
		casAuthenticationFilter.setSessionAuthenticationStrategy(sessionStrategy());
		casAuthenticationFilter.setFilterProcessesUrl("/j_spring_cas_security_check");
		return casAuthenticationFilter;
	}

	@Bean
	public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
		CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
		casAuthenticationEntryPoint.setLoginUrl(CAS_URL_LOGIN);
		casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
		return casAuthenticationEntryPoint;
	}

	@Bean
	public SingleSignOutFilter singleSignOutFilter() {
		SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
		singleSignOutFilter.setCasServerUrlPrefix(CAS_URL_PREFIX);
		return singleSignOutFilter;
	}

	@Bean
	public LogoutFilter requestCasGlobalLogoutFilter() {
		LogoutFilter logoutFilter = new LogoutFilter(
				CAS_URL_LOGOUT + "?service=" + APP_SERVICE_HOME,
				new SecurityContextLogoutHandler());
		// logoutFilter.setFilterProcessesUrl("/logout");
		// logoutFilter.setFilterProcessesUrl("/j_spring_cas_security_logout");
		logoutFilter.setLogoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"));
		return logoutFilter;
	}

	@Inject
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(casAuthenticationProvider());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/admin/images/**").antMatchers("/resources/admin/js/**").antMatchers("/scripts/**")
				.antMatchers("/styles/**").antMatchers("/views/**").antMatchers("/i18n/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class)
				.addFilterBefore(requestCasGlobalLogoutFilter(), LogoutFilter.class).exceptionHandling()
				.authenticationEntryPoint(casAuthenticationEntryPoint()).and().addFilter(casAuthenticationFilter());
		

		http.authorizeRequests()
				.antMatchers("/form/login.html", "/login.do", "/login.success", "/favicon.ico", "/form/403.html",
						"/rest", "/rest/services/**", "/casfailed.jsp", "/logout.jsp", "/session-expired.jsp",
						"/403.jsp", "/shared.css", "/index.html")
				.permitAll().antMatchers("/**").authenticated().and().csrf().disable();

		/**
		 * <logout invalidate-session="true" delete-cookies="JSESSIONID" />
		 */
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true)
				.deleteCookies("JSESSIONID");

		// http.csrf();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(casAuthenticationProvider());
	}
}
