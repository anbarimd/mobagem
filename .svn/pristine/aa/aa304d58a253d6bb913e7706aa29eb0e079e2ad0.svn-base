package net.h2.web.mob.file.profile;

import net.h2.web.core.base.exception.BaseServerBusinessException;
import net.h2.web.core.base.server.api.BaseApiImpl;

import org.springframework.stereotype.Service;

@Service
public class ProfileUploadApiImpl extends BaseApiImpl<ProfileUploadEntity, Long, IProfileUploadDAO>
		implements IProfileUploadAPI {

	@Override
	public Long saveLogin(ProfileUploadEntity login) throws BaseServerBusinessException {
		return dao.save(login);
	}

}
