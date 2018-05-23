package net.h2.web.core.config.basicsec;

import java.util.List;

public class DefaultUrlAuthorizationBinding implements UrlAuthorizationBinding {

	@Override
	public List<RoleUrlAccessBinding> roleUrlAccessBindings() {
		return null;
	}

	@Override
	public String[] permitAllUrls() {
		return null;
	}

	@Override
	public String[] authenticatedUrls() {
		String[] authenticated = new String[] { "ALL" };
		return authenticated;
	}

}
