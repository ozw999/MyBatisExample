package com.example.movie.play.service;

import com.example.movie.play.dao.MovieMapper;
import com.example.movie.play.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
