package net.h2.web.core.base.shared.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import net.h2.web.core.base.exception.BaseServerBusinessException;
import net.h2.web.core.base.server.api.IBaseAPI;
import net.h2.web.core.base.server.entity.BaseEntity;
import net.h2.web.core.base.shared.datatable.resolver.DataTablePagingCriteria;
import net.h2.web.core.base.shared.datatable.resultset.DataTableResultSet;
import net.h2.web.core.base.shared.dto.BaseDTO;

public class BaseServiceImpl<D extends BaseDTO<Id>, E extends BaseEntity<Id>, Id extends Serializable, API extends IBaseAPI<E, Id>>
		implements IBaseService<D, Id> {

	protected API api;
	protected Mapper mapper;
	protected Class<E> entityClass;
	protected Class<D> dtoClass;

	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
			ParameterizedType genericSuperclass = (ParameterizedType) getClass()
					.getGenericSuperclass();
			if (genericSuperclass != null
					&& genericSuperclass.getActualTypeArguments() != null
					&& genericSuperclass.getActualTypeArguments().length > 0) {
				if (genericSuperclass.getActualTypeArguments()[0] instanceof Class) {
					entityClass = (Class<E>) genericSuperclass
							.getActualTypeArguments()[1];
					dtoClass = (Class<D>) genericSuperclass
							.getActualTypeArguments()[0];
				}
			}
		}

	}

	@Override
	public Id save(D dto) throws BaseServerBusinessException {
		E entity = convertDtoToEntity(dto);
		Id id = api.save(entity);
		return id;
	}

	@Override
	public void delete(D dto) throws BaseServerBusinessException {
		E entity = convertDtoToEntity(dto);
		api.delete(entity);

	}

	@Override
	public void delete(Id id) throws BaseServerBusinessException {
		api.delete(id);
	}

	@Override
	public void update(D dto) throws BaseServerBusinessException {
		E entity = convertDtoToEntity(dto);
		api.update(entity);
	}

	@Override
	public D findById(Id id) throws BaseServerBusinessException {
		E entity = api.findById(id);
		D dto = convertEntityToDTO(entity);
		return dto;
	}

	@Override
	public Long countAll() throws BaseServerBusinessException {
		return api.countAll();
	}
	
	
	@Override
	public DataTableResultSet<D> getDataTableSortedPage(
			DataTablePagingCriteria dataTablePagingCriteria)
			throws BaseServerBusinessException {
		List<E> entityList = api.getDataTableSortedPage(dataTablePagingCriteria);
		List<D> dtoList = convertEntityToDTO(entityList);
		DataTableResultSet<D> dataTableResultSet = new DataTableResultSet<>(dataTablePagingCriteria, dtoList);
		return dataTableResultSet;
	}


	protected D convertEntityToDTO(E entity) {
		return convert(entity, dtoClass);
	}

	protected E convertDtoToEntity(D dto) {
		return convert(dto, entityClass);
	}

	protected List<E> convertDtoToEntity(Collection<D> list) {
		return convertList(list, entityClass);
	}

	protected List<D> convertEntityToDTO(Collection<E> list) {
		return convertList(list, dtoClass);
	}

	protected <S, D> D convert(S source, Class<D> destination) {
		return source == null ? null : mapper.map(source, destination);
	}

	protected <S, D> List<D> convertList(Collection<S> source,
			Class<D> destinationClass) {
		if (source == null) {
			return null;
		}
		List<D> destList = new ArrayList<D>();
		for (S s : source) {
			D convert = convert(s, destinationClass);
			destList.add(convert);
		}
		return destList;
	}

	@Autowired
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	@Autowired
	public void setApi(API api) {
		this.api = api;
	}

	
}
