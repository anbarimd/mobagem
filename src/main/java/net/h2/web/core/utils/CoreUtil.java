package net.h2.web.core.utils;

public class CoreUtil {
	
	public static Boolean checkStr(String str)
	{
		return  ((str == null) || (str.isEmpty())) ? false : true ; 
	}

}
