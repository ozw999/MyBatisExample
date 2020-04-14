package com.example.movie.play.service;

import com.example.movie.play.dao.MovieMapper;
import com.example.movie.play.entity.MovieEntity;
import com.sun.xml.internal.ws.api.model.MEP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/4/1
 */
@Service
public class MovieService {
    @Autowired
    private MovieMapper movieMapper;

    public MovieEntity findByMovieName(String name){
        return movieMapper.findByMovieName(name);
    }

    public List<MovieEntity> findByUserIds(List userIds){
        return movieMapper.findByUserIds(userIds);
    }

    public void update(MovieEntity entity){
        movieMapper.update(entity);
    }

    public void add(MovieEntity entity){
        movieMapper.add(entity);
    }

    public void delete(Integer movieId){
        movieMapper.delete(movieId);
    }
}
