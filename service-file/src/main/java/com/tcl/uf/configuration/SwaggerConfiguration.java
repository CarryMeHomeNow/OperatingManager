package com.tcl.uf.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket swaggerSpringMvcPlugin() {
		List<Parameter> aParameters = new ArrayList<Parameter>();
		//构造请求头参数
		ParameterBuilder aParameterBuilder = new ParameterBuilder();
		aParameters.add(aParameterBuilder.build());
		
		return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tcl.uf.controller"))
                .paths(PathSelectors.any())
                .build();
	}

	private ApiInfo apiInfo() {
		ApiInfoBuilder apiInfoBuilder=new ApiInfoBuilder();
		apiInfoBuilder.title("文件上传管理微服务");
		apiInfoBuilder.termsOfServiceUrl("http://localhost:8001");
		apiInfoBuilder.description("TCL Plus");
		apiInfoBuilder.version("1.0.0");
		return apiInfoBuilder.build();
	}
}
