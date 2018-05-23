package net.h2.web.mob.systemkeys;

import net.h2.web.core.base.server.dao.BaseDaoImpl;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class SystemKeysDaoImpl extends BaseDaoImpl<SystemKeysEntity, Long>
		implements ISystemKeysDAO {

	@Override
	@Cacheable("systemKeyCache")
	public String getSystemValue(String key, String defaultKey) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
		detachedCriteria.add(Restrictions.eq("key", key));
		Criteria criteriaFromDetachedCriteria = getCriteriaFromDetachedCriteria(detachedCriteria);
		SystemKeysEntity systemKeysEntity = (SystemKeysEntity) criteriaFromDetachedCriteria.uniqueResult();
		if(systemKeysEntity != null)
			return systemKeysEntity.getValue();		
		return defaultKey;
	}

}
