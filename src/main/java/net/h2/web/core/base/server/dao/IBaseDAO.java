package net.h2.web.core.base.server.dao;

import java.io.Serializable;
import java.util.List;

import net.h2.web.core.base.server.entity.BaseEntity;
import net.h2.web.core.base.shared.datatable.resolver.DataTablePagingCriteria;

public interface IBaseDAO <E extends BaseEntity<Id>,Id extends Serializable>{
	
	Id save(E entity);
	void delete (E entity);
	void delete (Id id);
	void update(E entity);
	E findById(Id id);
	Long countAll() ;
	List<E> searchByExample(E example);	
	List<E> getDataTableSortedPage(DataTablePagingCriteria dataTablePagingCriteria);
	
	

}
