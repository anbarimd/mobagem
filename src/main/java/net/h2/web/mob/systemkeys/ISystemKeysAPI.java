package net.h2.web.mob.systemkeys;

import net.h2.web.core.base.exception.BaseServerBusinessException;
import net.h2.web.core.base.server.api.IBaseAPI;

public interface ISystemKeysAPI extends IBaseAPI<SystemKeysEntity, Long> {
	
	String getSystemValue(String key, String defaultKey) throws BaseServerBusinessException;
	
	

}
