package net.h2.web.mob.login;

import net.h2.web.core.base.exception.BaseServerBusinessException;
import net.h2.web.core.base.server.api.BaseApiImpl;

import org.springframework.stereotype.Service;

@Service
public class LoginApiImpl extends BaseApiImpl<LoginEntity, Long, ILoginDAO>
		implements ILoginAPI {

	@Override
	public Long saveLogin(LoginEntity login) throws BaseServerBusinessException {
		return dao.save(login);
	}

}
