package com.example.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @SpringBootApplication 含有@SpringBootConfiguration、@EnableAutoConfiguration、@ComponentScan
 *      @SpringBootConfiguration: 作用与@Configuration相同,声明当前类是一个配置类,可以通过＠Bean生成IOC容器管理的bean
 *      @EnableAutoConfiguration: 启用Spring应用程序上下文的自动配置, 通过这个注解把spring应用所需的bean注入IOC容器中
 *      @ComponentScan: 自动扫描被@Service,@Repository,@Component,@Controller标识的类,最终生成IOC容器里的Bean
 * @EnableSwagger2 启动swagger2
 */
@SpringBootApplication
@EnableSwagger2
public class MovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }

}
