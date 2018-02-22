package com.nsikakthompson.android_article_webtask.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.nsikakthompson.android_article_webtask.api.ArticleApiService;
import com.nsikakthompson.android_article_webtask.model.Article;
import com.nsikakthompson.android_article_webtask.ui.CallbackView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Nsikak on 2/21/18.
 */

public class ArticleRepoImpl implements ArticleRepo {

    public static final String TAG = ArticleRepoImpl.class.getSimpleName();
    ArticleApiService articleApiService;
    MutableLiveData<List<Article>> contactList = new MutableLiveData<List<Article>>();
    /**
     * Collects all subscriptions to unsubscribe later
     */
    @NonNull
    private Disposable mDisposable;
    CallbackView callbackView;
    @Inject
    OkHttpClient okHttpClient;
    @Inject
    Gson gson;
    @Inject
    Retrofit retrofit;

    public ArticleRepoImpl(ArticleApiService articleApiService, CallbackView callbackView) {
        this.articleApiService = articleApiService;
        this.callbackView = callbackView;
    }


    /*Fetch Articles*/
    @Override
    public void fetchArticles() {

        if (mDisposable != null) {
            mDisposable.dispose();
        }
        mDisposable = articleApiService.getAllArticles()
                .delay(600, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    callbackView.onSuccess("Article fetched successful");
                    contactList.setValue(response);

                }, throwable -> Log.e(TAG, throwable.getMessage().toString()));


    }

    @Override
    public LiveData<List<Article>> getAllArticles() {
        return contactList;
    }


    /*Add article*/
    @Override
    public void addArticle(Article article) {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
         mDisposable = articleApiService.addArticle(article)
                 .delay(600, TimeUnit.MILLISECONDS)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(response -> {
                     callbackView.onSuccess("Article Added successful");


                 }, throwable -> callbackView.onError(throwable.getMessage().toString()));

    }
}
