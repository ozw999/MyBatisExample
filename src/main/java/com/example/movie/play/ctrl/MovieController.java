package com.example.movie.play.ctrl;

import com.example.movie.play.entity.MovieEntity;
import com.example.movie.play.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/4/1
 */
@RestController
@RequestMapping(value = "/movie",method = {RequestMethod.POST})
@Api(tags = "电影操作")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/getMovieByName")
    @ApiOperation(value = "按名称获取电影")
    public MovieEntity getMovieByName(String name){
        return movieService.findByMovieName(name);
    }
}
