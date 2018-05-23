package net.h2.web.core.base.shared.datatable.resolver;

import java.util.Collections;
import java.util.List;

import net.h2.web.core.base.shared.paging.SortField;

public class DataTablePagingCriteria {

	private final Integer displayStart;
	private final Integer displayLength;
	private final List<SortField> sortFields;
	private final Integer echo;
	

	public DataTablePagingCriteria(Integer displayStart, Integer displayLength,
			Integer echo, List<SortField> sortFields) {
		this.displayStart = displayStart;
		this.displayLength = displayLength;
		this.echo = echo;
		this.sortFields = sortFields;
	}

	public Integer getEcho() {
		return echo;
	}

	public Integer getDisplayStart() {
		return displayStart;
	}

	public Integer getDisplaySize() {
		return displayLength;
	}

	public List<SortField> getSortFields() {
		return Collections.unmodifiableList(sortFields);
	}

	

}
