package net.h2.web.mob.systemkeys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import net.h2.web.core.base.server.entity.BaseEntity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "MOB_SYSTEM_KEYS")
//@Cache(region = "systemKeyCache", usage = CacheConcurrencyStrategy.READ_ONLY)
public class SystemKeysEntity extends BaseEntity<Long> {

	private String key;
	private String value;

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

	@Lob
	@Column(name = "SYS_KEY", unique = true)
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "SYS_VALUE")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
