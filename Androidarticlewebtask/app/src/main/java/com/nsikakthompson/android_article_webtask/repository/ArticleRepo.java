package com.nsikakthompson.android_article_webtask.repository;

import android.arch.lifecycle.LiveData;

import com.nsikakthompson.android_article_webtask.model.Article;

import java.util.List;

/**
 * Created by Nsikak on 2/21/18.
 */

public interface ArticleRepo {

    void fetchArticles();
    LiveData<List<Article>> getAllArticles();
    void addArticle(Article article);

}
