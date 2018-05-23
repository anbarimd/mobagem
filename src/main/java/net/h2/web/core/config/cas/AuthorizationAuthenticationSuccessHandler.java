package net.h2.web.core.config.cas;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationAuthenticationSuccessHandler extends
		AbstractAuthenticationTargetUrlRequestHandler implements
		AuthenticationSuccessHandler {

	public AuthorizationAuthenticationSuccessHandler(String defaultTargetURL) {
		setDefaultTargetUrl(defaultTargetURL);
	}

	public AuthorizationAuthenticationSuccessHandler() {
	}

	// @Autowired
	// private ICredentialsIntoSession credentialsIntoSessionHandler;

	@Override
	// @Transactional
	public void onAuthenticationSuccess(final HttpServletRequest request,
			final HttpServletResponse response,
			final Authentication authentication) throws IOException,
			ServletException {
		if (authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication
					.getPrincipal();
			String username = userDetails.getUsername();

//			request.getSession().setAttribute(
//					IBaseUserAPI.USERNAME_SESSION_PROPERTY_NAME, username);

			// credentialsIntoSessionHandler.putCredentialsIntoSession();

			if (getSendRedirect() != null && getSendRedirect()) {
				handle(request, response, authentication);
			}
			clearAuthenticationAttributes(request);
		}
	}

	/**
	 * Removes temporary authentication-related data which may have been stored
	 * in the session during the authentication process.
	 */
	protected final void clearAuthenticationAttributes(
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if (session == null) {
			return;
		}

		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	private Boolean sendRedirect;

	public Boolean getSendRedirect() {
		return sendRedirect;
	}

	public void setSendRedirect(Boolean sendRedirect) {
		this.sendRedirect = sendRedirect;
	}

	public HomeUrlAfterSuccessfulAuthentication targetUrlResolver;

	public HomeUrlAfterSuccessfulAuthentication getTargetUrlResolver() {
		return targetUrlResolver;
	}

	public void setTargetUrlResolver(HomeUrlAfterSuccessfulAuthentication targetUrlResolver) {
		this.targetUrlResolver = targetUrlResolver;
	}

	@Override
	protected void handle(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String targetUrl = "";
		if (targetUrlResolver != null) {
			targetUrl = targetUrlResolver
					.decideHomeUrlAfterSuccessfulAuthentication(authentication);
		} else {
			targetUrl = determineTargetUrl(request, response);
		}

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to "
					+ targetUrl);
			return;
		}

		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}

}
