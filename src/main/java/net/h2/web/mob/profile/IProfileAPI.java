package net.h2.web.mob.profile;

import net.h2.web.core.base.exception.BaseServerBusinessException;
import net.h2.web.core.base.server.api.IBaseAPI;

public interface IProfileAPI extends IBaseAPI<ProfileEntity, Long> {

	ProfileEntity findByMobile(Long mobile) throws BaseServerBusinessException;

	ProfileEntity findByMobileAndConfimCode(Long mobilePhone, Integer confirmCode) throws BaseServerBusinessException;

}
