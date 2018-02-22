package com.nsikakthompson.android_article_webtask.injection;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.nsikakthompson.android_article_webtask.AppController;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nsikak on 2/21/18.
 */

@Module
public class AppModule {

        private AppController appController;

        public AppModule(AppController appController)
        {
            this.appController = appController;
        }

        @Provides
        Context applicationContext() {
            return appController;
        }

        @Provides
        @Singleton
        GsonConverterFactory provideGsonConverterFactory(Gson gson) {
            return GsonConverterFactory.create(gson);
        }
        @Provides
        @Singleton
        Cache provideHttpCache() {
            int cacheSize = 10 * 1024 * 1024;
            Cache cache = new Cache(applicationContext().getCacheDir(), cacheSize);
            return cache;
        }

        @Provides
        @Singleton
        Gson provideGson() {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
            return gsonBuilder.create();
        }

        @Provides
        @Singleton
        OkHttpClient provideOkhttpClient(Cache cache) {
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.cache(cache);
            return client.build();
        }


        @Provides
        @Singleton
        Retrofit provideRetrofit(OkHttpClient okHttpClient, GsonConverterFactory factory) {
            return new Retrofit.Builder()
                    .baseUrl("https://wt-e681794554391ac97c69e1539e0c67df-0.run.webtask.io/")
                    .client(okHttpClient)
                    .addConverterFactory(factory)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }







}
