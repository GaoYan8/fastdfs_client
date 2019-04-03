package com.zarl.gy.zarl_fastdfs;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

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
@SpringBootApplication
@PropertySources({ 
	@PropertySource("classpath:zarlFastdfs.properties"),
	@PropertySource("classpath:configFastdfs.properties") 
	})
public class ZARLFastdfsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZARLFastdfsApplication.class, args);
	}
	
	/*@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory config = new MultipartConfigFactory();
        config.setMaxFileSize("9000MB");
        config.setMaxRequestSize("9000MB");
        return config.createMultipartConfig();
    }*/

}
