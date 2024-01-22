package com.yangsx95.notes.spring.ioc.qualifier.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 电影推荐
 */
public class MovieRecommender {

    private SimpleMovieCatalog movieCatalog;

    @Autowired
    public void prepare(@Qualifier("main") SimpleMovieCatalog movieCatalog) {
        this.movieCatalog = movieCatalog;
    }

    public void recommend() {
        System.out.println("推荐电影:" + movieCatalog.listMovie().get(0));
    }

}
