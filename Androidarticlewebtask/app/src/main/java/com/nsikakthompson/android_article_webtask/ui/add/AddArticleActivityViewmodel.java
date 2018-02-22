package com.nsikakthompson.android_article_webtask.ui.add;

import android.arch.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.nsikakthompson.android_article_webtask.api.ArticleApiService;
import com.nsikakthompson.android_article_webtask.injection.AppComponent;
import com.nsikakthompson.android_article_webtask.model.Article;
import com.nsikakthompson.android_article_webtask.repository.ArticleRepo;
import com.nsikakthompson.android_article_webtask.repository.ArticleRepoImpl;
import com.nsikakthompson.android_article_webtask.ui.CallbackView;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Nsikak on 2/22/18.
 */

public class AddArticleActivityViewmodel extends ViewModel implements AppComponent.Injectable {


    static CallbackView callbackView;
    ArticleRepo articleRepo;
    @Inject
    Retrofit retrofit;
    @Inject
    Gson gson;
    @Inject
    OkHttpClient okHttpClient;
    ArticleApiService articleApiService;

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void attachView(CallbackView callbackView) {
        this.callbackView = callbackView;
    }


    //adding Article to the webtask server by calling the ApiService
    public void addArticle(Article article) {
        articleApiService = retrofit.create(ArticleApiService.class);
        articleRepo = new ArticleRepoImpl(articleApiService, callbackView);
        articleRepo.addArticle(article);
    }

}
