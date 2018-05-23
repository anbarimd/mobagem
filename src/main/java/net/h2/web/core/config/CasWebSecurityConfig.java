//package net.h2.web.core.config;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.servlet.Filter;
//import javax.servlet.http.HttpServletRequest;
//
//import org.jasig.cas.client.session.SingleSignOutFilter;
//import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationDetailsSource;
//import org.springframework.security.cas.ServiceProperties;
//import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
//import org.springframework.security.cas.authentication.CasAuthenticationProvider;
//import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
//import org.springframework.security.cas.web.CasAuthenticationFilter;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
//import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
//import org.springframework.security.web.RedirectStrategy;
//import org.springframework.security.web.access.ExceptionTranslationFilter;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//import org.springframework.security.web.authentication.logout.LogoutFilter;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
//import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
//import org.springframework.security.web.session.ConcurrentSessionFilter;
//
//import net.h2.web.core.config.basicsec.DefaultUrlAuthorizationBinding;
//import net.h2.web.core.config.basicsec.UrlAuthorizationBinding;
//import net.h2.web.core.config.cas.AuthorizationAuthenticationSuccessHandler;
//import net.h2.web.core.config.cas.FrameworkAuthenticationDetailsSource;
//import net.h2.web.core.config.cas.FrameworkRedirectStrategy;
//import net.h2.web.core.config.cas.HomeUrlAfterSuccessfulAuthentication;
//import net.h2.web.core.config.cas.SessionExpiredFilter;
//import net.h2.web.sec.CustomUserDetailsService;
//
//@Configuration
//@EnableWebSecurity
//@ComponentScan(basePackages = "net.h2.web")
//public class CasWebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	// public static String AsnadServerAddress = "https://AsnadSrv:8443/asnad2";
//	// public static String CasServerAddress = "https://EnrollmentSrv:7443/cas";
//	private static final String APP_ADMIN_USER_NAME = "test";
//	private static final String CAS_URL_PREFIX = "https://localhost:8443/cas";
//	private String casUrl = "https://localhost:8443/cas";
//	private String applicationUrl = "http://localhost:8080/mobagem";
//	private String applicationName = "mobagem";
//	private String accessDeniedUrl = "/403.jsp";
//	private String logoutPageUrl = "/logout.jsp";
//	private String sessionExpiredUrl = "/session-expired.jsp";
//	private String casFailedUrl = "/casfailed.jsp";
//	private String statisticsUrl = "/webservice/rest/Statistics/Datasource/getInfo";
//
//	@Bean
//	public Set<String> adminList() {
//		Set<String> admins = new HashSet<String>();
//		String adminUserName = APP_ADMIN_USER_NAME;
//
//		admins.add("admin");
//
//		if (adminUserName != null && !adminUserName.isEmpty()) {
//			admins.add(adminUserName);
//		}
//		return admins;
//	}
//
//	@Bean
//	public ServiceProperties serviceProperties() {
//		ServiceProperties serviceProperties = new ServiceProperties();
//		serviceProperties.setService(applicationUrl + "/j_spring_cas_security_check");
//		serviceProperties.setSendRenew(true);
//		return serviceProperties;
//	}
//
//	@Bean
//	public CasAuthenticationProvider casAuthenticationProvider() {
//		CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
//		casAuthenticationProvider.setAuthenticationUserDetailsService(customUserDetailsService());
//		casAuthenticationProvider.setServiceProperties(serviceProperties());
//		casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
//		casAuthenticationProvider.setKey("an_id_for_this_auth_provider_only");
//		return casAuthenticationProvider;
//	}
//
//	@Bean
//	public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> customUserDetailsService() {
//		return new CustomUserDetailsService(adminList());
//	}
//
//	@Bean
//	public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
//		return new Cas20ServiceTicketValidator(casUrl);
//	}
//
//	@Bean
//	public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
//		CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
//		casAuthenticationFilter.setAuthenticationManager(authenticationManager());
//		casAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
//		casAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
//		casAuthenticationFilter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy());
//		casAuthenticationFilter.setAuthenticationDetailsSource(authenticationDetailsSource());
//		return casAuthenticationFilter;
//	}
//
//	@Bean
//	public AuthenticationSuccessHandler authenticationSuccessHandler() {
//		AuthorizationAuthenticationSuccessHandler successHandler = new AuthorizationAuthenticationSuccessHandler();
//		successHandler.setTargetUrlResolver(targetUrlResolver);
//		successHandler.setSendRedirect(true);
//		return successHandler;
//	}
//
//	@Bean
//	public AuthenticationFailureHandler authenticationFailureHandler() {
//		return new SimpleUrlAuthenticationFailureHandler(casFailedUrl);
//	}
//
//	@Bean
//	public AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource() {
//		return new FrameworkAuthenticationDetailsSource();
//	}
//
//	@Bean
//	public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//		ConcurrentSessionControlAuthenticationStrategy strategy = new ConcurrentSessionControlAuthenticationStrategy(
//				sessionRegistry());
//		strategy.setMaximumSessions(1);
//		strategy.setExceptionIfMaximumExceeded(false);
//		return strategy;
//	}
//
//	@Bean
//	public SessionRegistry sessionRegistry() {
//		return new SessionRegistryImpl();
//	}
//
//	@Bean
//	public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
//		CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
//		casAuthenticationEntryPoint.setLoginUrl(casUrl + "/login");
//		casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
//		return casAuthenticationEntryPoint;
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		ExceptionHandlingConfigurer<HttpSecurity> accessDeniedPart = http.exceptionHandling()
//				.accessDeniedPage(accessDeniedUrl);
//		HttpSecurity httpSecurityAfterAccessDenied = accessDeniedPart.and();
//		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = httpSecurityAfterAccessDenied
//				.authorizeRequests();
//
//		authorizeRequests.antMatchers(sessionExpiredUrl).permitAll();
//		authorizeRequests.antMatchers(accessDeniedUrl).permitAll();
//		authorizeRequests.antMatchers(logoutPageUrl).permitAll();
//		authorizeRequests.antMatchers(casFailedUrl).permitAll();
//		authorizeRequests.antMatchers(statisticsUrl).permitAll();
//
//		Filter concurrentSessionFilter = concurrentSessionFilter();
//		Filter requestSingleLogoutFilter = requestSingleLogoutFilter();
//		Filter singleLogoutFilter = singleLogoutFilter();
//		Filter casAuthenticationFilter = casAuthenticationFilter();
//		Filter sessionExpiredFilter = sessionExpiredFilter();
//
//		authorizeRequests.and().addFilter(concurrentSessionFilter)
//				.addFilterBefore(requestSingleLogoutFilter, LogoutFilter.class)
//				.addFilterBefore(casAuthenticationFilter, CasAuthenticationFilter.class)
//				.addFilterBefore(singleLogoutFilter, casAuthenticationFilter.getClass())
//				.addFilterAfter(sessionExpiredFilter, ExceptionTranslationFilter.class).logout()
//				.invalidateHttpSession(true)
//				// .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//				.logoutSuccessUrl(logoutPageUrl + "?appname=" + applicationName + "&appurl=" + applicationUrl)
//				.permitAll()
//				// .deleteCookies("JSESSIONID")
//				.and().sessionManagement().sessionAuthenticationStrategy(sessionAuthenticationStrategy()).and().csrf()
//				.disable().authenticationProvider(casAuthenticationProvider()).exceptionHandling()
//				.authenticationEntryPoint(casAuthenticationEntryPoint());
//		http.authorizeRequests()
//				.antMatchers("/form/login.html", "/login.do", "/login.success", "/favicon.ico", "/form/403.html",
//						"/rest", "/rest/services/**", "/casfailed.jsp", "/logout.jsp", "/session-expired.jsp",
//						"/403.jsp", "/shared.css", "/index.html", "/j_spring_cas_security_check")
//				.permitAll().antMatchers("/**").authenticated().and().csrf().disable()
//
//				.addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class).authorizeRequests()
//				.antMatchers("/**").authenticated().and().csrf().disable();
//	}
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/fonts/**").antMatchers("/images/**").antMatchers("/scripts/**")
//				.antMatchers("/styles/**").antMatchers("/views/**").antMatchers("/i18n/**");
//	}
//
//	@Bean
//	public SingleSignOutFilter singleSignOutFilter() {
//		SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
//		singleSignOutFilter.setCasServerUrlPrefix(CAS_URL_PREFIX);
//		return singleSignOutFilter;
//	}
//
//	@Bean
//	public Filter sessionExpiredFilter() {
//		SessionExpiredFilter expiredFilter = new SessionExpiredFilter();
//		expiredFilter.setExpiredUrl(sessionExpiredUrl + "?appurl=" + applicationUrl);
//		return expiredFilter;
//	}
//
//	@Bean
//	public Filter singleLogoutFilter() {
//		return new SingleSignOutFilter();
//	}
//
//	@Bean
//	public Filter requestSingleLogoutFilter() {
//		LogoutFilter filter = new LogoutFilter(casUrl + "/logout?url=" + applicationUrl,
//				new SecurityContextLogoutHandler());
//		filter.setFilterProcessesUrl("/j_spring_cas_security_logout");
//		return filter;
//	}
//
//	@Bean
//	public Filter concurrentSessionFilter() {
//		ConcurrentSessionFilter filter = new ConcurrentSessionFilter(sessionRegistry(),
//				sessionExpiredUrl + "?appurl=" + applicationUrl);
//		filter.setRedirectStrategy(redirectStrategy());
//		return filter;
//	}
//
//	@Bean
//	public RedirectStrategy redirectStrategy() {
//		FrameworkRedirectStrategy strategy = new FrameworkRedirectStrategy();
//		return strategy;
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(casAuthenticationProvider());
//	}
//
//	private UrlAuthorizationBinding urlAuthorizationBinding = new DefaultUrlAuthorizationBinding();
//	private HomeUrlAfterSuccessfulAuthentication targetUrlResolver = new HomeUrlAfterSuccessfulAuthentication() {
//
//		@Override
//		public String decideHomeUrlAfterSuccessfulAuthentication(Authentication authentication) {
//			return "/";
//		}
//	};
//
//}