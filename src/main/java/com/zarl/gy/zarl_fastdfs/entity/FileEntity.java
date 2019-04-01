package com.zarl.gy.zarl_fastdfs.entity;


import com.zarl.gy.zarl_fastdfs.entity.base.BaseEntity;


/**
 * 
 *   
 * @ClassName:  com.zarl.gy.zarl_fastdfs.entity.FileEntity 
 * @Description:TODO 文件实体类
 * @author: 高炎
 * @Email 59498838@qq.com OR yan.gao@zarltech.com
 * @date:   2019年4月1日 上午11:49:27   
 *     
 * @Copyright: 2019 www.zarltech.com Inc. All rights reserved.
 */
public class FileEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String name;
	
	private int a;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

}
