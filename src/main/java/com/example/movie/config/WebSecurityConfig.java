package com.example.movie.config;

import com.example.movie.component.CommonResponse;
import com.example.movie.component.LoginFailureEnum;
import com.example.movie.user.entity.UserEntity;
import com.example.movie.utils.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;

/**
 * @Description: Security相关配置
 *
 * @Author: Ou
 * @Date: 2020/6/10
 */

@Configuration
//@EnableWebMvc
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //从数据库表查询所需注入
//    @Autowired
//    private DataSource dataSource;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * 注入密码加密方式
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new MD5PasswordEncoder();    //自定义MD5加密
//        return NoOpPasswordEncoder.getInstance();     //明文方式，不推荐使用
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

    /**
     * 配置如何通过拦截器保护请求
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //跨域资源共享（Cross-origin resource sharing）,它允许浏览器向跨源(协议 + 域名 + 端口)服务器，发出XMLHttpRequest请求，从而克服了AJAX只能同源使用的限制。
                //跨站请求伪造（Cross-site request fogery）,是一种挟制用户在当前已登录的WEB应用程序上执行非本意的操作的攻击方法。
                //Security通过csrftoken进行防御
                .cors().and().csrf().disable()  //关闭csrf防御    // 允许跨域访问
                .authorizeRequests()    //开启URL权限配置
                //定义了过滤器后,需要同步在过滤器中处理
                // 开放swagger start
                .antMatchers("/swagger-ui.html",
                        "/swagger-resources/**",
                        "/images/**",
                        "/webjars/**",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/configuration/security")
                .permitAll()//以上的请求都不需要认证   swagger end
                .antMatchers("/user/**").hasRole("ADMIN")   //角色区分大小写
                .anyRequest().authenticated()   //剩余的其他接口，登录之后就能访问
                .and()
                .formLogin()    //使用表单登录，不再使用默认httpBasic方式
//                .loginPage("/login.html")
//                .loginProcessingUrl("/login")
                .usernameParameter("username")  //用户名参数名称
                .passwordParameter("password")  //密码参数名称
                .successHandler(new CustomAuthSuccessHandler())     //登录成功处理器
                .failureHandler(((request, response, authException) -> {
                    CommonResponse result = new CommonResponse(CommonResponse.FAILURE_CODE, LoginFailureEnum.getValue(authException.getMessage()));
                    ResponseUtil.writeData(response, result);
                }))     //登录失败处理器
                .and().exceptionHandling()
                .and().addFilter(new AuthenticationFilter(authenticationManager()))     //认证过滤器
        ;
    }

    /**
     * 配置Spring Security的filter链
     * 用来忽略一些url地址，对其不进行校验，通常用在一些静态文件中。
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
//        web.ignoring().antMatchers("/js/**","/css/**","/images/**");
    }
}
