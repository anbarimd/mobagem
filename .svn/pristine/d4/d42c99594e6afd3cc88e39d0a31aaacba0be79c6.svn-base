package net.h2.web.mob.credit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import net.h2.web.core.base.server.entity.BaseEntity;

@Entity
@Table(name = "MOB_CREDIT")
public class CreditEntity extends BaseEntity<Long> {

	private String ticketStr;

	private Date createdDate;

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

	@Column(name = "CONF_CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Lob
	@Column(name = "CONF_CODE", length = Integer.MAX_VALUE)
	public String getTicketStr() {
		return ticketStr;
	}

	public void setTicketStr(String ticketStr) {
		this.ticketStr = ticketStr;
	}

}
