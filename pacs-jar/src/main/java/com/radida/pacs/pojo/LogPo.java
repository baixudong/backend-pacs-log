package com.radida.pacs.pojo;

import java.io.Serializable;
import java.util.Date;

import com.radida.pacs.core.common.IDomainObject;

public class LogPo implements IDomainObject{
	
	private static final long serialVersionUID = 1L;
	
	private String gid;
	private String msg;
	private Date createTime;
	
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public Serializable getUid() {
		return this.getGid();
	}
	@Override
	public void setUid(Serializable id) {
		this.setGid((String)id);
	}

}
