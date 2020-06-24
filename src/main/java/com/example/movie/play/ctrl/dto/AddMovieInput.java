package com.example.movie.play.ctrl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/4/14
 */
@Data
@ApiModel(description = "电影新增入参")
public class AddMovieInput {

    @NotNull
    @ApiModelProperty(value = "用户ID",required = true)
    private Integer userId;

    @NotBlank
    @Size(max = 50 , message = "电影名称不能大于{max}")
    @ApiModelProperty(value = "电影名称",required = true)
    private String movieName;

    @ApiModelProperty(value = "电影链接",required = true)
    private String url;
}
