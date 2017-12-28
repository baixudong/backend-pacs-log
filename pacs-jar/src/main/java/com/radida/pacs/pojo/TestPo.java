package com.radida.pacs.pojo;

import java.io.Serializable;
import java.util.Date;

import com.radida.pacs.core.common.IDomainObject;

public class TestPo implements IDomainObject{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	/** 状态 -1删除 0无效 1有效 */
	private Integer status;
	private Date createTime;
	private Date updateTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public Serializable getUid() {
		return this.getId();
	}
	@Override
	public void setUid(Serializable id) {
		this.setId((String)id);
	}

}
