package com.radida.pacs.core.common;

import java.io.Serializable;

public class Entity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String insGid;
    private String insName;
    

    public String getInsGid() {
		return insGid;
	}

	public void setInsGid(String insGid) {
		this.insGid = insGid;
	}



	public String getInsName() {
		return insName;
	}



	public void setInsName(String insName) {
		this.insName = insName;
	}



	@Override
    public String toString() {
        return "Entity [insGid=" + insGid + ", insName=" + insName + "]";
    }

}