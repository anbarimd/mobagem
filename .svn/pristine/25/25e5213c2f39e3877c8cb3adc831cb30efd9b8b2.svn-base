package net.h2.web.mob.profile;

import net.h2.web.core.base.server.dao.IBaseDAO;

public interface IProfileDAO extends IBaseDAO<ProfileEntity, Long> {

	ProfileEntity findByMobile(Long mobile);

	ProfileEntity findByMobileAndConfimCode(Long mobilePhone, Integer confirmCode);

}
