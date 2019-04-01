package com.zarl.gy.zarl_fastdfs.exception;

/**
 * 
 *   
 * @ClassName:  com.zarl.gy.zarl_fastdfs.exception.FastDFSException 
 * @Description:TODO FastDFS 上传下载时可能出现的一些异常信息
 * @author: 高炎
 * @Email 59498838@qq.com OR yan.gao@zarltech.com
 * @date:   2019年4月1日 上午11:49:39   
 *     
 * @Copyright: 2019 www.zarltech.com Inc. All rights reserved.
 */
public class FastDFSException extends Exception {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误消息
     */
    private String message;

    public FastDFSException(){}

    public FastDFSException(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
