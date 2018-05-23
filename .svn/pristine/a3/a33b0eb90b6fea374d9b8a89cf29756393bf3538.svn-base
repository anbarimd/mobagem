package net.h2.web.mob.profile;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.h2.web.core.base.server.entity.BaseEntity;
import net.h2.web.mob.credit.CreditEntity;

@Entity
@Table(name = "MOB_PROFILE")
//@Cache(region = "profileCache",usage=CacheConcurrencyStrategy.READ_ONLY)
public class ProfileEntity extends BaseEntity<Long> {

	private Long mobilePhone;
	private String fullName;
	private Boolean isEnable;
	private Date profileCreatedDate;
	private Date profileLastModifiedDate;
	private Integer confirmCode;
	private String ticketStr;
	private String imagePath;
	private List<CreditEntity> credit;
	private String ipAddress;
	private Integer smsRequestedCount;
	

	@Override
	@Id
	@GeneratedValue
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	@Column(name = "PROF_MOBILE",unique = true)
	public Long getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(Long mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "PROF_IS_ENABLE")
	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_PROFILE_ID")
	public List<CreditEntity> getCredit() {
		return credit;
	}

	public void setCredit(List<CreditEntity> credit) {
		this.credit = credit;
	}

	@Column(name = "PROF_CONFIRM_CODE")
	public Integer getConfirmCode() {
		return confirmCode;
	}

	public void setConfirmCode(Integer confirmCode) {
		this.confirmCode = confirmCode;
	}

	@Column(name = "PROF_TICKET")
	public String getTicketStr() {
		return ticketStr;
	}

	public void setTicketStr(String ticketStr) {
		this.ticketStr = ticketStr;
	}
	

	@Column(name = "PROF_IP_ADDRESS")
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	@Column(name = "PROF_FULLNAME")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "PROF_CREATE_DATE")
	public Date getProfileCreatedDate() {
		return profileCreatedDate;
	}

	public void setProfileCreatedDate(Date profileCreatedDate) {
		this.profileCreatedDate = profileCreatedDate;
	}

	@Column(name = "PROF_MODIFIED_DATE")
	public Date getProfileLastModifiedDate() {
		return profileLastModifiedDate;
	}

	public void setProfileLastModifiedDate(Date profileLastModifiedDate) {
		this.profileLastModifiedDate = profileLastModifiedDate;
	}

	@Column(name = "PROF_IMAGE_PATH")
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Column(name = "PROF_SMS_COUNTER")
	public Integer getSmsRequestedCount() {
		return smsRequestedCount;
	}

	public void setSmsRequestedCount(Integer smsRequestedCount) {
		this.smsRequestedCount = smsRequestedCount;
	}

}
