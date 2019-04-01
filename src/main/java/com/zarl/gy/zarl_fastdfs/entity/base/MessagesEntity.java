package com.zarl.gy.zarl_fastdfs.entity.base;

/**
 * 
 *   
 * @ClassName:  com.zarl.gy.zarl_fastdfs.entity.base.MessagesEntity 
 * @Description:TODO 返回消息公共类
 * @author: 高炎
 * @Email 59498838@qq.com OR yan.gao@zarltech.com
 * @date:   2019年4月1日 上午11:49:06   
 *     
 * @Copyright: 2019 www.zarltech.com Inc. All rights reserved.
 */
public class MessagesEntity extends BaseEntity {
    //
    private transient static final long serialVersionUID = 1L;
    // 响应是否成功
    private boolean success = true;
    // 响应的操作信息
    private String message;
    //状态吗
    private int status;
    //错误逻辑吗
    private String code;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
    
    
    
    
}
