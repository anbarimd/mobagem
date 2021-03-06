package net.h2.web.mob.profile.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.h2.web.core.base.exception.BaseServerBusinessException;
import net.h2.web.core.utils.CoreUtil;
import net.h2.web.mob.profile.IProfileService;
import net.h2.web.mob.profile.ProfileDTO;
import net.h2.web.mob.profile.RegisterParams;
import net.h2.web.mob.profile.enums.ProfileStatus;
import net.h2.web.mob.systemkeys.ISystemKeysAPI;
import net.h2.web.mob.systemkeys.SystemKeys;
import net.h2.web.mob.util.ProfileUtil;
import net.h2.web.mob.util.SmsUtil;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	Logger logger = LoggerFactory.getLogger(ProfileController.class);

	@Autowired
	private IProfileService service;	

	@Autowired
	private SmsUtil smsUtil;
	
	@Autowired
	private ISystemKeysAPI systemKeys;

	private static final String APP_JSON = "application/json;charset=UTF-8";
	private static final String DEFAULT_SMS_ALLOW_REQ_COUNT = "5";
	

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/getConfirm/{mobile}", method = RequestMethod.GET, produces = APP_JSON)
	public Integer getConfirm(@PathVariable("mobile") Long mobile ,HttpServletRequest request)
			throws BaseServerBusinessException, ClientProtocolException, IOException, InterruptedException {
		
		Integer profileStatus = null;
		String remoteAddress = request.getRemoteAddr();
		Integer SMS_ALLOW_REQ_COUNT = Integer.valueOf(systemKeys.getSystemValue(SystemKeys.KEY_SMS_ALLOW_REQ_COUNT, DEFAULT_SMS_ALLOW_REQ_COUNT));
		
		ProfileDTO profile = service.findProfileByMobile(mobile);
		if(profile == null)
		{
			profileStatus =  ProfileStatus.NOT_EXIST.getValue();
			Integer confirmCode = ProfileUtil.generateConfirmationCode();	
			smsUtil.sendSMS(mobile, confirmCode);
			saveProfile(mobile, confirmCode, remoteAddress);
		}
		else if(!profile.getIsEnable())
		{
			profileStatus =  ProfileStatus.DISABLED.getValue();
			updateProfile(profile,null,remoteAddress);
		}
		else if(remoteAddress.equals(profile.getIpAddress()) && profile.getSmsRequestedCount() >= SMS_ALLOW_REQ_COUNT)
		{
			profileStatus =  ProfileStatus.POTENTIAL_RISK.getValue();
			updateProfile(profile,null,remoteAddress);
		}
		else
		{
			profileStatus =  ProfileStatus.EXIST.getValue();
			Integer confirmCode = ProfileUtil.generateConfirmationCode();	
			smsUtil.sendSMS(mobile, confirmCode);
			updateProfile(profile,confirmCode,remoteAddress);
		}		

		return profileStatus;
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/getTicket", method = RequestMethod.POST,produces = APP_JSON)
	public String getTicket(@RequestBody RegisterParams registerParams) throws BaseServerBusinessException {
		String confirmCode = registerParams.getConfirmCode();
		String mobilePhone = registerParams.getMobile();
		if(CoreUtil.checkStr(confirmCode) && CoreUtil.checkStr(mobilePhone))
		{
			ProfileDTO profile = service.findByMobileAndConfimCode(Long.valueOf(mobilePhone),Integer.valueOf(confirmCode));
			if(profile != null)
			{
				String generateTicket = ProfileUtil.generateTicket(mobilePhone, confirmCode);
				profile.setTicketStr(generateTicket);
				service.update(profile);				
				return generateTicket;
			}
		}
		return null;
	}

	private void saveProfile(Long mobile, Integer confirmCode, String remoteAddress) throws BaseServerBusinessException {
		ProfileDTO profile = new ProfileDTO();
		profile.setConfirmCode(confirmCode);
		profile.setMobilePhone(mobile);
		profile.setIpAddress(remoteAddress);
		profile.setProfileCreatedDate(new Date());
		service.save(profile);

	}

	private void updateProfile(ProfileDTO profile,Integer confirmCode, String remoteAddress) throws BaseServerBusinessException {
		if(confirmCode != null)
			profile.setConfirmCode(confirmCode);
		profile.setIpAddress(remoteAddress);
		profile.setSmsRequestedCount(remoteAddress.equals(profile.getIpAddress()) ? profile.getSmsRequestedCount() + 1 : 0);
		profile.setProfileLastModifiedDate(new Date());
		service.update(profile);

	}

	
	@ExceptionHandler({Exception.class,ClientProtocolException.class,InterruptedException.class})
	public ResponseEntity<String> errorHandler(Exception exc) {
		logger.error(exc.getMessage(), exc);
		if (exc instanceof BaseServerBusinessException) {
			String exceptionCode = ((BaseServerBusinessException) exc).getExceptionCode();
			String exceptionMsg = ((BaseServerBusinessException) exc).getExceptionCode();
			return new ResponseEntity<>(exceptionCode + ":" + exceptionMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else if (exc instanceof ClientProtocolException) {
			return new ResponseEntity<>("SMS", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		else if (exc instanceof IOException) {
			return new ResponseEntity<>("SMS", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		else if (exc instanceof IOException) {
			return new ResponseEntity<>("SMS", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
