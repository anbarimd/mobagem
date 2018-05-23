package net.h2.web.core.base.shared.datatable.resultset;

import java.util.Collections;
import java.util.List;

import net.h2.web.core.base.shared.datatable.resolver.DataTablePagingCriteria;

public class DataTableResultSet<D> {

	private final Integer sEcho;
	private final Integer iTotalRecords;
	private Integer iTotalDisplayRecords;
	private final List<D> aaData;

	public DataTableResultSet(DataTablePagingCriteria pagingCriteria, List<D> resultSet) {
		this.sEcho = pagingCriteria.getEcho();
		this.aaData = resultSet;
		this.iTotalRecords = pagingCriteria.getDisplaySize();
		//this.iTotalDisplayRecords = totalRecords;
	}

	public Integer getsEcho() {
		return sEcho;
	}

	public Integer getiTotalRecords() {
		return iTotalRecords;
	}

	public Integer getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public List<D> getAaData() {
		return Collections.unmodifiableList(aaData);
	}

	public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

}
