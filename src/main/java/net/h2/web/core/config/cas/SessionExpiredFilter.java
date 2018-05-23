package net.h2.web.core.config.cas;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.security.web.util.ThrowableCauseExtractor;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

public class SessionExpiredFilter extends GenericFilterBean {

	private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();
	private AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();

	private String expiredUrl;

	public void setExpiredUrl(String expiredUrl) {
		this.expiredUrl = expiredUrl;
	}

	@Override
	public void afterPropertiesSet() {
		Assert.isTrue(
				expiredUrl == null || UrlUtils.isValidRedirectUrl(expiredUrl),
				expiredUrl + " isn't a valid redirect URL");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch (IOException ex) {
			throw ex;
		} catch (Exception ex) {
			Throwable[] causeChain = throwableAnalyzer.determineCauseChain(ex);
			RuntimeException ase = (AuthenticationException) throwableAnalyzer
					.getFirstThrowableOfType(AuthenticationException.class,
							causeChain);

			if (ase == null) {
				ase = (AccessDeniedException) throwableAnalyzer
						.getFirstThrowableOfType(AccessDeniedException.class,
								causeChain);
			}

			if (ase != null) {
				if (ase instanceof AuthenticationException) {
					throw ase;
				} else if (ase instanceof AccessDeniedException) {

					if (authenticationTrustResolver
							.isAnonymous(SecurityContextHolder.getContext()
									.getAuthentication())) {

						String ajaxHeader = ((HttpServletRequest) request)
								.getContentType();
						if ((ajaxHeader != null && ajaxHeader
								.contains("gwt-rpc"))) {
							throw new SessionExpiredException(expiredUrl);
						}
						/*
						 * String uri = ((HttpServletRequest) request)
						 * .getRequestURI(); if (uri.contains("/services")) {
						 * throw new WebSessionExpiredException(); }
						 */
						else {
							throw ase;
						}

						/*
						 * HttpServletRequest req = ((HttpServletRequest)
						 * request); HttpServletResponse res =
						 * (HttpServletResponse) response; String contentType =
						 * req.getContentType(); String servletPath =
						 * req.getServletPath(); HttpSession session =
						 * req.getSession(false); if (session != null) { String
						 * sessionId = session.getId(); SessionInformation
						 * sessionInformation = sessionRegistry
						 * .getSessionInformation(sessionId); if
						 * (sessionInformation != null) { if
						 * (sessionInformation.isExpired()) { if (contentType !=
						 * null && contentType.contains("gwt-rpc")) { throw new
						 * SessionExpiredException( expiredUrl); } else {
						 * res.sendRedirect(expiredUrl); return; } } } } throw
						 * ase;
						 */
					} else {
						throw ase;
					}
				}
			}

		}
	}

	private static final class DefaultThrowableAnalyzer extends
			ThrowableAnalyzer {
		/**
		 * @see org.springframework.security.web.util.ThrowableAnalyzer#initExtractorMap()
		 */
		@Override
		protected void initExtractorMap() {
			super.initExtractorMap();

			registerExtractor(ServletException.class,
					new ThrowableCauseExtractor() {
						@Override
						public Throwable extractCause(Throwable throwable) {
							ThrowableAnalyzer.verifyThrowableHierarchy(
									throwable, ServletException.class);
							return ((ServletException) throwable)
									.getRootCause();
						}
					});
		}

	}

}
