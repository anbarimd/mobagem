package net.h2.web.core.base.server.entity;

import java.io.Serializable;

public class BaseEntity<Id extends Serializable> {

	private Id id;

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

}
