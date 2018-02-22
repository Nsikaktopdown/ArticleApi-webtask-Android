package com.nsikakthompson.android_article_webtask.injection;

import com.nsikakthompson.android_article_webtask.ui.add.AddArticleActivityViewmodel;
import com.nsikakthompson.android_article_webtask.ui.list.MainActivityViewmodel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Nsikak on 2/21/18.
 */
@Singleton
@Component( modules = {AppModule.class})
public interface AppComponent {


     void inject(MainActivityViewmodel mainActivityViewmodel);
     void inject(AddArticleActivityViewmodel addArticleActivityViewmodel);

        interface  Injectable{
            void inject(AppComponent appComponent);
        }
    }


