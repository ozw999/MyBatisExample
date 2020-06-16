package com.example.movie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * @Description:configure(AuthenticationManagerBuilder)配置user-detail服务，configure(WebSecurity)配置Spring Security的filter链，configure(HttpSecurity)配置如何通过拦截器保护请求。
 * @Author: Ou
 * @Date: 2020/6/10
 */

@Configuration
//@EnableWebMvc
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private DataSource dataSource;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * 密码加密方式
     * 不注入会报错
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new MD5PasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 配置user-detail服务,此处配置之后,yml配置的spring.security.user 将失效
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);

//        //将用户信息写入内存
//        auth.inMemoryAuthentication()
//                .withUser("admin").roles("ADMIN").password("cabf7fb485d63d9c753220018eb28081"); //password为admin的MD5密文

        //通过注入dataSource，从数据库表查询
//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("SELECT user_name AS username, password ,enabled FROM user WHERE user_name=?")
//                .authoritiesByUsernameQuery("SELECT user_name AS username, role_name FROM role_user WHERE user_name=?")
//        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //跨域资源共享（Cross-origin resource sharing）,它允许浏览器向跨源(协议 + 域名 + 端口)服务器，发出XMLHttpRequest请求，从而克服了AJAX只能同源使用的限制。
                //跨站请求伪造（Cross-site request fogery）,是一种挟制用户在当前已登录的WEB应用程序上执行非本意的操作的攻击方法。
                //Security通过csrftoken进行防御
                .cors().and().csrf().disable()  //关闭csrf防御    // 允许跨域访问
                .authorizeRequests()    //开启URL权限配置
//                .antMatchers("/**").permitAll()
                // 开放swagger start
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/configuration/security").permitAll()
                // swagger end
                .antMatchers("/user/**").hasRole("ADMIN")   //角色区分大小写
                .anyRequest().authenticated()   //剩余的其他接口，登录之后就能访问
                .and()
                .formLogin()
//                .loginProcessingUrl("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .successHandler()

        ;
    }

}
