package com.nsikakthompson.android_article_webtask.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.nsikakthompson.android_article_webtask.AppController;
import com.nsikakthompson.android_article_webtask.injection.AppComponent;

/**
 * Created by Nsikak on 2/21/18.
 */

public class ViewmodelFactoryins extends ViewModelProvider.NewInstanceFactory {

        private AppController application;

        public ViewmodelFactoryins(AppController application) {
            this.application = application;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            T t = super.create(modelClass);
            if (t instanceof AppComponent.Injectable) {
                ((AppComponent.Injectable) t).inject(application.getAppComponent());
            }
            return t;
        }




}
