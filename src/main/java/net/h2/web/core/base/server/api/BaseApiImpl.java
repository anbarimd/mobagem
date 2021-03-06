package net.h2.web.core.base.server.api;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import net.h2.web.core.base.exception.BaseServerBusinessException;
import net.h2.web.core.base.server.dao.IBaseDAO;
import net.h2.web.core.base.server.entity.BaseEntity;
import net.h2.web.core.base.shared.datatable.resolver.DataTablePagingCriteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class BaseApiImpl<E extends BaseEntity<Id>, Id extends Serializable, DAO extends IBaseDAO<E, Id>>
		implements IBaseAPI<E, Id> {

	protected DAO dao;
	protected Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public BaseApiImpl() {
		if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
			ParameterizedType genericSuperclass = (ParameterizedType) getClass()
					.getGenericSuperclass();
			if (genericSuperclass != null
					&& genericSuperclass.getActualTypeArguments() != null
					&& genericSuperclass.getActualTypeArguments().length > 0) {
				if (genericSuperclass.getActualTypeArguments()[0] instanceof Class) {
					entityClass = (Class<E>) genericSuperclass
							.getActualTypeArguments()[0];
				}
			}
		}
	}

	@Override
	@Transactional
	public Id save(E entity) throws BaseServerBusinessException {
		return dao.save(entity);
	}

	@Override
	@Transactional
	public void delete(E entity) throws BaseServerBusinessException {
		dao.delete(entity);

	}

	@Override
	public void delete(Id id) throws BaseServerBusinessException {
		dao.delete(id);
	}

	@Override
	@Transactional
	public void update(E entity) throws BaseServerBusinessException {
		dao.update(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public E findById(Id id) throws BaseServerBusinessException {
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Long countAll() throws BaseServerBusinessException {
		return dao.countAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<E> getDataTableSortedPage(
			DataTablePagingCriteria dataTablePagingCriteria)
			throws BaseServerBusinessException {
		return dao.getDataTableSortedPage(dataTablePagingCriteria);
	}

	@Override
	public List<E> searchByExample(E example)
			throws BaseServerBusinessException {
		return dao.searchByExample(example);
	}

	@Autowired
	public void setDao(DAO dao) {
		this.dao = dao;
	}

	

}