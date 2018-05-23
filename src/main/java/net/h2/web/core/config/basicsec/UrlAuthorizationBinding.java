package net.h2.web.core.config.basicsec;

import java.util.List;

public interface UrlAuthorizationBinding {
	
	List<RoleUrlAccessBinding> roleUrlAccessBindings();
	String[] permitAllUrls();
	String[] authenticatedUrls();

}
