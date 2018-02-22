package com.nsikakthompson.android_article_webtask;

import android.app.Application;

import com.nsikakthompson.android_article_webtask.injection.AppComponent;
import com.nsikakthompson.android_article_webtask.injection.AppModule;
import com.nsikakthompson.android_article_webtask.injection.DaggerAppComponent;

/**
 * Created by Nsikak on 2/21/18.
 */

public class AppController extends Application {

    private final AppComponent appComponent = createAppComponent();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    protected AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
