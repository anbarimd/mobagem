package net.h2.web.core.config.cas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.cas.web.CasAuthenticationEntryPoint;

public class FrameworkAuthenticationEntryPoint extends
		CasAuthenticationEntryPoint {

	@Override
	protected void preCommence(HttpServletRequest request,
			HttpServletResponse response) {
		// System.err.println("preCommence called. the request was for " +
		// request.getServletPath());
		super.preCommence(request, response);
	}

	@Override
	protected String createServiceUrl(HttpServletRequest request,
			HttpServletResponse response) {
		return super.createServiceUrl(request, response);
	}

}
