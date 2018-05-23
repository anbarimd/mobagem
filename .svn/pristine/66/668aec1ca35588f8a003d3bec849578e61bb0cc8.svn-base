package net.h2.web.core.base.server.api;

import java.io.Serializable;
import java.util.List;

import net.h2.web.core.base.exception.BaseServerBusinessException;
import net.h2.web.core.base.server.entity.BaseEntity;
import net.h2.web.core.base.shared.datatable.resolver.DataTablePagingCriteria;
import net.h2.web.core.base.shared.datatable.resultset.DataTableResultSet;

public interface IBaseAPI<E extends BaseEntity<Id>, Id extends Serializable> {

	Id save(E entity) throws BaseServerBusinessException;

	void delete(E entity) throws BaseServerBusinessException;

	void delete(Id id) throws BaseServerBusinessException;

	void update(E entity) throws BaseServerBusinessException;

	E findById(Id id) throws BaseServerBusinessException;

	Long countAll() throws BaseServerBusinessException;

	List<E> searchByExample(E example) throws BaseServerBusinessException;
	
	List<E> getDataTableSortedPage(DataTablePagingCriteria dataTablePagingCriteria) throws BaseServerBusinessException;

}