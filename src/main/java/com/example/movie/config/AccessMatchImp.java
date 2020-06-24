package com.example.movie.config;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/6/24
 */
@Component
public class AccessMatchImp implements AccessMatch{

    private static final String ROOT_URI = "/";
    private static final String INDEX_URI = "/index.html";
    private static final String LOGIN_URI = "/login.html";
    private static final String SWAGGER_UI_HTML = "/swagger-ui.html";

    private List<String> accessList = new ArrayList<String>(){
        {
            add("/actionForward/login");
            add(ROOT_URI);
            add(INDEX_URI);
            add(LOGIN_URI);
        }
    };

    private List<String> accessPattenList = new ArrayList<String>(){
        {
            add(SWAGGER_UI_HTML);
            add("/swagger-resources");
            add("/images/");
            add("/webjars/");
            add("/v2/api-docs");
            add("/configuration/ui");
            add("/configuration/security");
        }
    };

    @Override
    public boolean isAccessUri(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        for (String accessUri : accessList) {
            if((contextPath + accessUri).equals(requestUri)){
                return true;
            }
        }
        for (String accessUri : accessPattenList) {
            if(null != requestUri && requestUri.startsWith(contextPath + accessUri)){
                return true;
            }
        }
        return false;
    }

    /**
     * 添加过滤路径（不包含项目名）
     * @param accessUri
     * @return
     */
    protected boolean addAccessUri(String accessUri){
        return accessList.add(accessUri);
    }

    /**
     * 添加起始通配过滤路径（不包含项目名）
     * @param accessPattenUri
     * @return
     */
    protected boolean addAccessPartenUri(String accessPattenUri){
        return accessPattenList.add(accessPattenUri);
    }

}
