package net.h2.web.core.config.cas;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;

public class FrameworkAuthenticationDetailsSource
		implements
		AuthenticationDetailsSource<HttpServletRequest, FrameworkAuthenticationDetails> {

	@Override
	public FrameworkAuthenticationDetails buildDetails(
			HttpServletRequest request) {
		return new FrameworkAuthenticationDetails(request);
	}

}
