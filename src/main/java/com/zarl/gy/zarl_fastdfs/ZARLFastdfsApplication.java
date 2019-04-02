package com.zarl.gy.zarl_fastdfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 
 *   
 * @ClassName:  com.zarl.gy.zarl_fastdfs.ZARLFastdfsApplication 
 * @Description:TODO 启动类
 * @author: 高炎
 * @Email 59498838@qq.com OR yan.gao@zarltech.com
 * @date:   2019年4月1日 上午11:50:03   
 *     
 * @Copyright: 2019 www.zarltech.com Inc. All rights reserved.
 */
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@SpringBootApplication
@PropertySources({ 
	@PropertySource("classpath:zarlFastdfs.properties"),
	@PropertySource("classpath:configFastdfs.properties") 
	})
public class ZARLFastdfsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZARLFastdfsApplication.class, args);
	}
	

	@Bean(name = "multipartResolver")  	
    public MultipartResolver  multipartResolver() { 	  	
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();  
        resolver.setDefaultEncoding("UTF-8");  
        resolver.setResolveLazily(true);//D属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常  
        resolver.setMaxInMemorySize(40960);  
        resolver.setMaxUploadSize(1000 * 1024 * 1024);//上传文件大小 20M 20*1024*1024  
        return resolver; 
    }

}
