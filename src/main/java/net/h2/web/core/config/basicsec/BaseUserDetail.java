package net.h2.web.core.config.basicsec;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

public interface BaseUserDetail extends UserDetails, Serializable{
	
	List<String> getUserOperations();

}
