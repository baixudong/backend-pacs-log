package com.radida.pacs.core.common;

import java.io.Serializable;

public interface IDomainObject extends Serializable {
    /**
     * 返回实体对象的id 
     * id可以为任何实现了序列化接口的对象
     * @return id
     */
    public  Serializable getUid();
    /**
     * 设置实体对象的id
     * @param id 可以为任何实现了序列化接口的对象
     */
    public void setUid(Serializable id); 
}
