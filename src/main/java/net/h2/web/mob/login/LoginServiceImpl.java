package net.h2.web.mob.login;

import net.h2.web.core.base.exception.BaseServerBusinessException;
import net.h2.web.core.base.shared.service.BaseServiceImpl;

import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl extends BaseServiceImpl<LoginDTO, LoginEntity, Long,ILoginAPI> implements ILoginService{

	
	
	@Override
	public Long saveLogin(LoginDTO login) throws BaseServerBusinessException {
		LoginEntity customerEntity = convertDtoToEntity(login);
		return api.save(customerEntity);
	}

}
