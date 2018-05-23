package net.h2.web.mob.profile;

import net.h2.web.core.base.exception.BaseServerBusinessException;
import net.h2.web.core.base.server.api.BaseApiImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileApiImpl extends BaseApiImpl<ProfileEntity, Long, IProfileDAO>
		implements IProfileAPI {

	
	@Override
	@Transactional(readOnly = true)
	public ProfileEntity findByMobile(Long mobile)
			throws BaseServerBusinessException {
		return dao.findByMobile(mobile);
	}

	@Override
	@Transactional(readOnly = true)
	public ProfileEntity findByMobileAndConfimCode(Long mobilePhone, Integer confirmCode) throws BaseServerBusinessException {
		return dao.findByMobileAndConfimCode(mobilePhone,confirmCode);
	}

}
