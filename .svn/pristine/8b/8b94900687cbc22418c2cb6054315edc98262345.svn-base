package net.h2.web.mob.util;

import java.util.Date;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

import net.h2.web.mob.profile.ProfileDTO;
import net.h2.web.mob.profile.enums.ProfileStatus;


public class ProfileUtil {
	
	
	private static final Integer LOW = 5555;
	private static final Integer HIGH = 6666;
	
	
	public static Integer generateConfirmationCode() {
		Random rand = new Random();
		int confirmCode = rand.nextInt(HIGH-LOW) + LOW;
		return confirmCode;
	}
	
	public static String generateTicket(String mobilePhone,String confirmCode) {
		
		Date timestamp = new Date();
		String ticketStr = DigestUtils.md5Hex(mobilePhone + confirmCode + timestamp.getTime());		
		return ticketStr;
	}

	
	
	
}
