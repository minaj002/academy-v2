package com.weststein.configuration;

import com.fasterxml.classmate.ResolvedType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerController {

    @Bean
    public Docket serviceApi() {
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.weststein.controller"))
                .build()
                .useDefaultResponseMessages(false)
                .enableUrlTemplating(false)
                .globalOperationParameters(
                        header()
                        );
    }

    private List<Parameter> header() {
        List list = new ArrayList();
        list.add(new ParameterBuilder()
                .name("X-Authorization")
                .description("JWT authorization header")
                .modelRef(new ModelRef("string"))
                .parameterType("HEADER")
                .required(true)


                .build());
        return list;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("WestStein API Documentation")
                .build();
    }

}
