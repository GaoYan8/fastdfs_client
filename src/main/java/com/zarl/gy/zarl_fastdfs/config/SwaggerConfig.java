package com.zarl.gy.zarl_fastdfs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.groupName("文件模块")
				.select()
				// 当前包路径
				.apis(RequestHandlerSelectors.basePackage("com.zarl.gy.zarl_fastdfs.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	//构建api文档的详细信息函数  
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 页面标题
				.title("springBoot Fastdfs 文件上传API RESTful API")
				// 创建人
				.contact(new Contact("高炎", "https://github.com/GaoYan8/fastdfs_client", "yan.gao@zarltech.com"))
				// 版本号
				.version("1.0.0")
				// 描述
				.description("Fastdfs 文件上传API").build();
	}
}