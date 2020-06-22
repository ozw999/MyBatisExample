package com.example.movie.utils;

import com.example.movie.component.SecurityConstant;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description: response操作工具类
 * @Author: Ou
 * @Date: 2020/6/22
 */
public class ResponseUtil {

    /**
     * response数据写入
     * @param response
     * @param resultObj
     * @throws IOException
     */
    public static void writeData(HttpServletResponse response, Object resultObj) throws IOException {
        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
        response.setHeader("Access-Control-Allow-Headers",
                SecurityConstant.KEY_HEAD_TOKEN + ","
//                        + SecurityConstant.KEY_HEAD_LOCAL + ","
                        + "Accept, Origin, X-Requested-With, Content-Type, Last-Modified");

        //定义可以作为响应的一部分暴露给外部的内容
        response.setHeader("Access-Control-Expose-Headers",
                SecurityConstant.KEY_HEAD_TOKEN + ","
//                        + SecurityConstant.KEY_HEAD_LOCAL + ","
                        + "Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
        // 设置编码
        response.setCharacterEncoding("UTF-8");
        // 设置数据类型
        response.setContentType("text/json");
        String result = new ObjectMapper().writeValueAsString(resultObj);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(result);
            out.flush();
        } catch (IOException e){
            throw e;
        } finally {
            if(null != out){
                out.close();
            }
        }
    }

}
