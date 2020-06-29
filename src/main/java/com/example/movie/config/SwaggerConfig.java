package com.example.movie.config;

import com.example.movie.component.SwaggerHeaderProp;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: swagger配置类
 * @Author: Ou
 * @Date: 2020/3/31
 */
//相当于<beans>,为bean的集合
@Configuration
@EnableSwagger2
//成员变量需要加上setter，否则属性值无法自动注入
@ConfigurationProperties(prefix = "swagger.headers")
public class SwaggerConfig {

    private boolean open;

    private List<SwaggerHeaderProp> params = new ArrayList<>();

    //@Bean是一个方法级别的注解，用于产生一个被Spring IoC容器所管理的Bean(默认单例模式)
    @Bean
    public Docket createDocket() {
        List<Parameter> pars = new ArrayList<>();
        if (open) {
            for (SwaggerHeaderProp params : params) {
                ParameterBuilder ticketPar = new ParameterBuilder();
                ticketPar.name(params.getName())
                        .description(params.getDescription())
                        .modelRef(new ModelRef(params.getType()))
                        .parameterType("header")
                        .required(params.isRequired())
                        .defaultValue(params.getDefaultValue())
                        .build();
                pars.add(ticketPar.build());
            }
        }
        //添加head参数start
//        ParameterBuilder tokenPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        tokenPar.name("authorization").description("认证").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
//        pars.add(tokenPar.build());
        //添加head参数end

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(pars)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.movie"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("MyBatis + SqLite 测试程序")
                .termsOfServiceUrl("NO terms of service")
                .version("1.0")
                .build();
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<SwaggerHeaderProp> getParams() {
        return params;
    }

    public void setParams(List<SwaggerHeaderProp> params) {
        this.params = params;
    }
}
