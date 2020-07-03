package com.example.movie.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Description: 注入配置在.yml中的URI序列和PattenUri序列
 * @Author: Ou
 * @Date: 2020/7/2
 */

@Configuration
//从配置文件中获取参数，遵从宽松绑定规则
@ConfigurationProperties(prefix = "auth.basic-filter")
@Data
public class AccessUriProperties {
    private List<String> accessUriList;

    private List<String> accessPattenUriList;
}
