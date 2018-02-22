package com.nsikakthompson.android_article_webtask.api;

import com.nsikakthompson.android_article_webtask.model.Article;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Nsikak on 2/21/18.
 */

public interface ArticleApiService {

    @GET("article-api/articles")
    @Headers("Content-Type: application/json")
    Observable<List<Article>> getAllArticles();

    @POST("article-api/articles")
    @Headers({"Content-Type: application/json, Accept: application/json "})
    Observable<Article> addArticle(@Body Article article);

    @DELETE("article-api/articles")
    @Headers("Content-Type : application/x-www-form-urlencoded")
    Completable deleteArticle(String id);
}
