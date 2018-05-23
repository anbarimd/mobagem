package net.h2.web.core.config.basicsec;

public interface BaseAuthenticationConfigurer {

	String accessDeniedPageUrl();

	String logoutUrl();

	String sessionExpiredUrl();

	HomeUrlAfterSuccessfulAuthentication homeURL();
	
	ErrorUrlAfterFailedAuthentication errorUrl();

	UrlAuthorizationBinding urlAuthorizationBinding();
}
