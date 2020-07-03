package com.example.movie.config;

import com.example.movie.component.AccessUriProperties;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 将yml中配置的自定义的过滤路径加入过滤器
 * @Author: Ou
 * @Date: 2020/7/2
 */
//使@ConfigurationProperties生效
//@EnableConfigurationProperties(AccessUriProperties.class)
public class AuthenticationFilterAccessMatch extends AccessMatchImp implements InitializingBean {
    @Autowired
    private AccessUriProperties accessUriProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (null != accessUriProperties) {
            //获取定义在application中的UriList
            List<String> accessUriList = accessUriProperties.getAccessUriList();
            if (CollectionUtils.isNotEmpty(accessUriList)) {
                for (String accessUri : accessUriList) {
                    this.addAccessUri(accessUri);
                }
            }
            List<String> accessPattenUriList = accessUriProperties.getAccessPattenUriList();
            if (CollectionUtils.isNotEmpty(accessPattenUriList)) {
                for (String accessPattenUri : accessPattenUriList) {
                    this.addAccessPartenUri(accessPattenUri);
                }
            }
        }
    }
}
