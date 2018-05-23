package net.h2.web.core.config.basicsec;

public interface CasAuthenticationConfigurer extends
		BaseAuthenticationConfigurer {

	String casUrl();

	String applicationUrl();

	String applicationName();
	
	String casFailedUrl();

}
