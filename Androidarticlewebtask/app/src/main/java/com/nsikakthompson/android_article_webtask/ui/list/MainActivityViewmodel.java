package com.nsikakthompson.android_article_webtask.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.nsikakthompson.android_article_webtask.api.ArticleApiService;
import com.nsikakthompson.android_article_webtask.injection.AppComponent;
import com.nsikakthompson.android_article_webtask.model.Article;
import com.nsikakthompson.android_article_webtask.repository.ArticleRepo;
import com.nsikakthompson.android_article_webtask.repository.ArticleRepoImpl;
import com.nsikakthompson.android_article_webtask.ui.CallbackView;

import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Nsikak on 2/21/18.
 */

public class MainActivityViewmodel extends ViewModel implements AppComponent.Injectable {


    static CallbackView callbackView;
    ArticleRepo articleRepo;
    @Inject
    Retrofit retrofit;
    @Inject
    Gson gson;
    @Inject
    OkHttpClient okHttpClient;
    ArticleApiService articleApiService;
    LiveData<List<Article>> articleList = new MutableLiveData<List<Article>>();


    public void attachView(CallbackView callbackView) {
        this.callbackView = callbackView;
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
       // requestData();

    }

    public void requestData() {
        articleApiService = retrofit.create(ArticleApiService.class);
        articleRepo = new ArticleRepoImpl(articleApiService, callbackView);
        articleRepo.fetchArticles();

    }

    public LiveData<List<Article>> getArticles() {
        articleList = articleRepo.getAllArticles();
        return articleList;

    }
}
