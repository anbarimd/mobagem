package net.h2.web.mob.profile;

import org.springframework.stereotype.Service;

import net.h2.web.core.base.exception.BaseServerBusinessException;
import net.h2.web.core.base.shared.service.BaseServiceImpl;

@Service
public class ProfileServiceImpl extends BaseServiceImpl<ProfileDTO, ProfileEntity, Long,IProfileAPI> implements IProfileService{

	@Override
	public ProfileDTO findProfileByMobile(Long mobile)
			throws BaseServerBusinessException {
		
		return convertEntityToDTO(api.findByMobile(mobile));
	}

	@Override
	public ProfileDTO findByMobileAndConfimCode(Long mobilePhone, Integer confirmCode) throws BaseServerBusinessException {
		return convertEntityToDTO(api.findByMobileAndConfimCode(mobilePhone,confirmCode));		
	}
	
	

}
