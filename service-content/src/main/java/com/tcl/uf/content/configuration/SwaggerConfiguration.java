package com.tcl.uf.content.configuration;

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
		//构造参数[设置请求头信息]
		ParameterBuilder aParameterBuilder = new ParameterBuilder();
		aParameters.add(aParameterBuilder.build());
		
		return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tcl.uf.content.controller"))
                .paths(PathSelectors.any())
                .build();
	}

	private ApiInfo apiInfo() {
		ApiInfoBuilder apiInfoBuilder=new ApiInfoBuilder();
		apiInfoBuilder.title("TCL+ 内容管理模块");
		apiInfoBuilder.termsOfServiceUrl("http://localhost:8002");
		apiInfoBuilder.description("TCL+ 内容管理模块");
		apiInfoBuilder.version("1.0.0");
		return apiInfoBuilder.build();
	}
}
