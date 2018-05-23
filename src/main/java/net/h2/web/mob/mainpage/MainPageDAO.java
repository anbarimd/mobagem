package net.h2.web.mob.mainpage;

import java.util.ArrayList;
import java.util.List;

import net.h2.web.core.base.server.dao.BaseDaoImpl;
import net.h2.web.mob.file.page.MainPageFileEntity;
import net.h2.web.mob.mainpage.enums.MainPageType;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class MainPageDAO extends BaseDaoImpl<MainPageFileEntity, Long> implements IMainPageDAO {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Cacheable("mainPageFileCache")
	public List<MainPageFileEntity> getMainPageListByType(MainPageType type) {

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);

		detachedCriteria.add(Restrictions.eq("type", type));

		detachedCriteria.addOrder(Order.asc("colIndex"));

		Criteria criteria = getCriteriaFromDetachedCriteria(detachedCriteria);

		List list = criteria.list();

		return list == null ? new ArrayList<MainPageFileEntity>() : list;
	}

}
