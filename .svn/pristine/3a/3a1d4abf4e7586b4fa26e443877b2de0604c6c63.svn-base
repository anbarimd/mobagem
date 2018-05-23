package net.h2.web.mob.profile;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import net.h2.web.core.base.shared.dto.BaseDTO;
import net.h2.web.mob.credit.CreditEntity;

@JsonInclude(Include.ALWAYS)
public class ProfileDTO extends BaseDTO<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long mobilePhone;
	private String fullName;
	private Boolean isEnable = true;
	private Date profileCreatedDate;
	private Date profileLastModifiedDate;
	private Integer confirmCode;
	private String imagePath;
	private String ticketStr;
	private String ipAddress;
	private Integer smsRequestedCount = 0;
	


	public Long getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(Long mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Date getProfileCreatedDate() {
		return profileCreatedDate;
	}

	public void setProfileCreatedDate(Date profileCreatedDate) {
		this.profileCreatedDate = profileCreatedDate;
	}

	public Date getProfileLastModifiedDate() {
		return profileLastModifiedDate;
	}

	public void setProfileLastModifiedDate(Date profileLastModifiedDate) {
		this.profileLastModifiedDate = profileLastModifiedDate;
	}

	public Integer getConfirmCode() {
		return confirmCode;
	}

	public void setConfirmCode(Integer confirmCode) {
		this.confirmCode = confirmCode;
	}

	public String getTicketStr() {
		return ticketStr;
	}

	public void setTicketStr(String ticketStr) {
		this.ticketStr = ticketStr;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Integer getSmsRequestedCount() {
		return smsRequestedCount;
	}

	public void setSmsRequestedCount(Integer smsRequestedCount) {
		this.smsRequestedCount = smsRequestedCount;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	// public List<CreditEntity> getCredit() {
	// return credit;
	// }
	//
	// public void setCredit(List<CreditEntity> credit) {
	// this.credit = credit;
	// }

}
