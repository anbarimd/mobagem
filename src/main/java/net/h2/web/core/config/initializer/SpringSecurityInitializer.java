package net.h2.web.core.config.initializer;

import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer
{
	
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		super.beforeSpringSecurityFilterChain(servletContext);
	}
	
}
