package com.example.movie.play.ctrl;

import com.example.movie.play.ctrl.dto.AddMovieInput;
import com.example.movie.play.ctrl.dto.UpdateMovieInput;
import com.example.movie.play.entity.MovieEntity;
import com.example.movie.play.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/4/1
 */
@RestController
@RequestMapping(value = "/movie", method = {RequestMethod.POST})
@Api(tags = "电影操作")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/getMovieByName")
    @ApiOperation(value = "按名称获取电影")
    public MovieEntity getMovieByName(String name) {
        return movieService.findByMovieName(name);
    }

    @RequestMapping(value = "/getMovieByUserIds")
    @ApiOperation(value = "按用户IDs获取电影")
    public List<MovieEntity> getMovieByUserIds(String userIds) {
        String[] split = userIds.split(",");
        List<String> list = Arrays.asList(split);
        List<Integer> integerList = list.stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList());
        return movieService.findByUserIds(integerList);
    }

    @RequestMapping(value = "/update")
    @ApiOperation(value = "更新")
    public void update(@Valid UpdateMovieInput input) {
        MovieEntity movieEntity = new MovieEntity();
        if (null != input.getMovieId()) {
            movieEntity.setMovieId(input.getMovieId());
        }
        if (null != input.getUserId()) {
            movieEntity.setUserId(input.getUserId());
        }
        if (StringUtils.isNotBlank(input.getMovieName())) {
            movieEntity.setMovieName(input.getMovieName());
        }
        if (StringUtils.isNotBlank(input.getMovieName())) {
            movieEntity.setUrl(input.getUrl());
        }
        movieService.update(movieEntity);
    }

    @RequestMapping(value = "/add")
    @ApiOperation(value = "新增")
    public void add(@Valid AddMovieInput input) {
        MovieEntity movieEntity = new MovieEntity();
        if (null != input.getUserId()) {
            movieEntity.setUserId(input.getUserId());
        }
        if (StringUtils.isNotBlank(input.getMovieName())) {
            movieEntity.setMovieName(input.getMovieName());
        }
        if (StringUtils.isNotBlank(input.getMovieName())) {
            movieEntity.setUrl(input.getUrl());
        }
        movieService.add(movieEntity);
    }

    @RequestMapping(value = "/delete")
    @ApiOperation(value = "删除")
    public void delete(@ApiParam("电影ID") @RequestParam Integer movieId){
        movieService.delete(movieId);
    }
}
