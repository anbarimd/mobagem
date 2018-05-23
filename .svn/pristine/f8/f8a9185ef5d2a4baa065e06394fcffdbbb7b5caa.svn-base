package net.h2.web.mob.adminpannel.controller;

import net.h2.web.core.base.exception.BaseServerBusinessException;
import net.h2.web.core.base.shared.datatable.resolver.DataTablePagingCriteria;
import net.h2.web.core.base.shared.datatable.resolver.DataTableParam;
import net.h2.web.core.base.shared.datatable.resultset.DataTableResultSet;
import net.h2.web.mob.profile.IProfileService;
import net.h2.web.mob.profile.ProfileDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminPannelController {

	Logger logger = LoggerFactory.getLogger(AdminPannelController.class);

	@Autowired
	private IProfileService service;	

	private static final String APP_JSON = "application/json;charset=UTF-8";
	

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/profile/getList", method = RequestMethod.GET, produces = APP_JSON)
	public DataTableResultSet<ProfileDTO> getList(@DataTableParam  DataTablePagingCriteria dataTablePagingCriteria)	throws BaseServerBusinessException {
		
		Long totalRecords = service.countAll();
		DataTableResultSet<ProfileDTO> dataTableSortedPage = service.getDataTableSortedPage(dataTablePagingCriteria);
		dataTableSortedPage.setiTotalDisplayRecords(totalRecords.intValue());
		return dataTableSortedPage;
	}

	
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<String> errorHandler(Exception exc) {
		logger.error(exc.getMessage(), exc);
		if (exc instanceof BaseServerBusinessException) {
			String exceptionCode = ((BaseServerBusinessException) exc).getExceptionCode();
			String exceptionMsg = ((BaseServerBusinessException) exc).getExceptionCode();
			return new ResponseEntity<>(exceptionCode + ":" + exceptionMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		else			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
