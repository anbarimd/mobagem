package net.h2.web.core.config.cas;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.DefaultRedirectStrategy;

public class FrameworkRedirectStrategy extends DefaultRedirectStrategy {

	@Override
	public void sendRedirect(HttpServletRequest request,
			HttpServletResponse response, String url) throws IOException {

		if (url.contains("session-expired.jsp")) {
			String contentType = request.getContentType();
			if (contentType != null && contentType.contains("gwt-rpc")) {
				throw new SessionExpiredException(url);
			}
			String uri = request.getRequestURI();
			if (uri.contains("/services")) {
				throw new WebSessionExpiredException();
			}
		}

		super.sendRedirect(request, response, url);
	}

}
