package net.h2.web.core.base.shared.service;

import java.io.Serializable;

import net.h2.web.core.base.exception.BaseServerBusinessException;
import net.h2.web.core.base.shared.datatable.resolver.DataTablePagingCriteria;
import net.h2.web.core.base.shared.datatable.resultset.DataTableResultSet;
import net.h2.web.core.base.shared.dto.BaseDTO;

public interface IBaseService<D extends BaseDTO<Id>, Id extends Serializable>{

	Id save(D dto) throws BaseServerBusinessException;

	void delete(D dto) throws BaseServerBusinessException;
	
	void delete(Id id) throws BaseServerBusinessException;

	void update(D dto) throws BaseServerBusinessException;

	D findById(Id id) throws BaseServerBusinessException;
	
	Long countAll() throws BaseServerBusinessException;
	
	DataTableResultSet<D> getDataTableSortedPage(DataTablePagingCriteria dataTablePagingCriteria) throws BaseServerBusinessException;
	
	
	

}
