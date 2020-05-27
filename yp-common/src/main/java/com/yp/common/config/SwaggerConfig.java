//package com.yp.common.config;
//
//import com.fasterxml.classmate.TypeResolver;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.context.request.async.DeferredResult;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.schema.WildcardType;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import static springfox.documentation.schema.AlternateTypeRules.newRule;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//	@Autowired
//    private TypeResolver typeResolver;
//	@Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.yp.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(apiInfo()).alternateTypeRules( //自定义规则，如果遇到DeferredResult，则把泛型类转成json
//                        newRule(typeResolver.resolve(DeferredResult.class,
//                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
//                                typeResolver.resolve(WildcardType.class)));
//
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("壹养精品")
//                //.description("Spring cloud中使用Swagger2构建RESTful APIs")
//                //.termsOfServiceUrl("")
//                .version("1.0")
//                .build();
//    }
//}
