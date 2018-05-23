package net.h2.web.mob.login.controller;

import net.h2.web.core.base.exception.BaseServerBusinessException;
import net.h2.web.mob.login.ILoginService;
import net.h2.web.mob.login.LoginDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	

	@Autowired
	private ILoginService service;

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/save" , method = RequestMethod.POST)
	public Long saveLogin(@RequestBody LoginDTO login) throws BaseServerBusinessException {
		Long id = service.saveLogin(login);
		return id;
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/update/{userId}" , method = RequestMethod.GET)
	public Long updateLogin(@PathVariable("userId") Long id) throws BaseServerBusinessException {
		LoginDTO login = service.findById(id);
		service.update(login);
		return id;
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
	public void deleteById(@PathVariable("userId") Long id) throws BaseServerBusinessException {
		service.delete(id);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/find", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public LoginDTO findById(@RequestParam(value = "userId") Long id) throws BaseServerBusinessException {
		LoginDTO login = service.findById(id);
		return login;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> errorHandler(Exception exc) {
		logger.error(exc.getMessage(), exc);
		if(exc instanceof BaseServerBusinessException)
		{
			String exceptionCode = ((BaseServerBusinessException) exc).getExceptionCode();
			String exceptionMsg = ((BaseServerBusinessException) exc).getExceptionCode();
			return new ResponseEntity<>(exceptionCode + ":" + exceptionMsg, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
