package net.h2.web.mob.systemkeys;

import net.h2.web.core.base.exception.BaseServerBusinessException;
import net.h2.web.core.base.server.api.BaseApiImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SystemKeysApiImpl extends BaseApiImpl<SystemKeysEntity, Long, ISystemKeysDAO>
		implements ISystemKeysAPI {

	@Override
	@Transactional(readOnly = true)
	public String getSystemValue(String key, String defaultKey)
			throws BaseServerBusinessException {
		return dao.getSystemValue(key,defaultKey);
	}


}
