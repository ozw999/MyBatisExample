package com.example.movie.component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Map;

/**
 * @Description: 通用返回格式对象
 * @Author: Ou
 * @Date: 2020/6/22
 */

@ApiModel(description = "通用返回对象")
public class CommonResponse implements Serializable {
    /**
     * 成功代码
     */
    public static final String SUCCESS_CODE = "0";
    /**
     * 失败代码
     */
    public static final String FAILURE_CODE = "1";

    public static final CommonResponse SUCCESS = new CommonResponse(SUCCESS_CODE);

    public static final CommonResponse FAILURE = new CommonResponse(FAILURE_CODE);

    public static final CommonResponse DELETE_SUCCESS = new CommonResponse(SUCCESS_CODE, "删除成功");

    public static final CommonResponse DELETE_FAILURE = new CommonResponse(FAILURE_CODE, "删除失败");

    public static final CommonResponse UPDATE_SUCCESS = new CommonResponse(SUCCESS_CODE, "更新成功");

    public static final CommonResponse UPDATE_FAILURE = new CommonResponse(FAILURE_CODE, "更新失败");

    public static final CommonResponse CREATE_SUCCESS = new CommonResponse(SUCCESS_CODE, "创建成功");

    public static final CommonResponse CREATE_FAILURE = new CommonResponse(FAILURE_CODE, "创建失败");

    @ApiModelProperty(value = "代码",position = 801)
    private String code = SUCCESS_CODE;
    @ApiModelProperty(value = "提示信息",position = 802)
    private String message;// 提示信息
    @ApiModelProperty(value = "数据",position = 803)
    private Object data;// 数据信息
    @ApiModelProperty(value = "附加信息",position = 804)
    private Map<String, ?> attributes;// 其他参数

    public CommonResponse() {
    }

    public CommonResponse(String code) {
        this.code = code;
    }

    public CommonResponse(String code, Object data) {
        this.code = code;
        this.data = data;
    }

    public CommonResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResponse(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, ?> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, ?> attributes) {
        this.attributes = attributes;
    }

}