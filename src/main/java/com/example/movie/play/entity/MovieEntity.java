package com.example.movie.play.entity;

import lombok.Data;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/4/1
 */
@Data
public class MovieEntity {
    private Integer movieId;
    private Integer userId;
    private String movieName;
    private String url;
    private Boolean deleted;
}
