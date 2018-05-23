package net.h2.web.core.config.cas;

import org.springframework.security.core.Authentication;

public interface HomeUrlAfterSuccessfulAuthentication {

	String decideHomeUrlAfterSuccessfulAuthentication(Authentication authentication);

}
