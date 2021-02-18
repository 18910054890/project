package com.lyzd.om.shared.model.common;

import java.io.Serializable;

import com.lyzd.om.shared.model.BaseEntity;

/**
 * @author Thinker
 *
 * @param <ID>
 */
public abstract class IDBasedEntity<ID extends Serializable> extends BaseEntity {


	/****/
	private static final long serialVersionUID = 2899502637434511740L;
	private ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

}
