package net.h2.web.core.config.cas;

import javax.servlet.http.HttpServletRequest;

import net.h2.web.core.config.cas.patch.DefaultServiceAuthenticationDetails;

public class FrameworkAuthenticationDetails extends
		DefaultServiceAuthenticationDetails {

	private static final long serialVersionUID = 1L;

	private String browser;

	public FrameworkAuthenticationDetails(HttpServletRequest request) {
		super(request);
		String b = request.getHeader("user-agent");
		setBrowser(b);
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

}
