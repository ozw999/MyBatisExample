package com.example.movie.component;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:Swagger请求头参数
 * @Author: Ou
 * @Date: 2020/6/24
 */
@Data
public class SwaggerHeaderProp {
    private boolean required;

    private String name;

    private String description;

    private String defaultValue;

    private String type = "string";
}
