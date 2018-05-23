package net.h2.web.mob.profile;

import net.h2.web.core.base.server.dao.BaseDaoImpl;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileDaoImpl extends BaseDaoImpl<ProfileEntity, Long>
		implements IProfileDAO {

	@Override
	public ProfileEntity findByMobile(Long mobile) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
			
		detachedCriteria.add(Restrictions.eq("mobilePhone", mobile));
		Criteria criteria = getCriteriaFromDetachedCriteria(detachedCriteria);
		ProfileEntity profile = (ProfileEntity) criteria.uniqueResult();
		if (profile != null) {
			return profile;
		}	
		return null;
	}
	
	
	@Override
	public ProfileEntity findByMobileAndConfimCode(Long mobilePhone,Integer confirmCode) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
			
		detachedCriteria.add(Restrictions.and(Restrictions.eq("mobilePhone", mobilePhone),Restrictions.eq("confirmCode", confirmCode)));
		Criteria criteria = getCriteriaFromDetachedCriteria(detachedCriteria);
		ProfileEntity profile = (ProfileEntity) criteria.uniqueResult();
		if (profile != null) {
			return profile;
		}	
		return null;
	}

}
