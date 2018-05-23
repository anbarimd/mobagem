package net.h2.web.core.config.basicsec;

import java.util.Map;

public interface ErrorUrlAfterFailedAuthentication {

	Map<String, String> decideErrorUrlAfterFailedAuthentication(String baseUrl);

}
