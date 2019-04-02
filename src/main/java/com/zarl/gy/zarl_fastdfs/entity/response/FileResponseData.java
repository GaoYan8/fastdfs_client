package com.zarl.gy.zarl_fastdfs.entity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zarl.gy.zarl_fastdfs.entity.base.MessagesEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 *   
 * @ClassName:  com.zarl.gy.zarl_fastdfs.entity.response.FileResponseData 
 * @Description:TODO 上传文件后的数据返回对象，便于前台获取数据.
 * @author: 高炎
 * @Email 59498838@qq.com OR yan.gao@zarltech.com
 * @date:   2019年4月1日 上午11:49:17   
 *     
 * @Copyright: 2019 www.zarltech.com Inc. All rights reserved.
 */
@ApiModel(description = "上传文件后的数据返回对象，便于前台获取数据")
public class FileResponseData extends MessagesEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public FileResponseData() {}
	
	public FileResponseData(boolean success) {
		setSuccess(success);
	}

	/**
     * 文件路径
     */
	@ApiModelProperty(value = "文件路径")
    @JsonInclude(Include.NON_NULL)
    private String filePath;

    /**
     * 文件名称
     */
	@ApiModelProperty(value = "文件名称")
    @JsonInclude(Include.NON_NULL)
    private String fileName;

    /**
     * 文件类型
     */
	@ApiModelProperty(value = "文件类型")
    @JsonInclude(Include.NON_NULL)
    private String fileType;

    /**
     * Http URL
     */
	@ApiModelProperty(value = "Http URL")
    @JsonInclude(Include.NON_NULL)
    private String httpUrl;

    /**
     * Http Token
     */
	@ApiModelProperty(value = "Http Token")
    @JsonInclude(Include.NON_NULL)
    private String token;
   

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
