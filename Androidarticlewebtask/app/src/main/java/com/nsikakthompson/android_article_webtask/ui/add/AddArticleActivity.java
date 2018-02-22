package com.nsikakthompson.android_article_webtask.ui.add;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nsikakthompson.android_article_webtask.AppController;
import com.nsikakthompson.android_article_webtask.R;
import com.nsikakthompson.android_article_webtask.model.Article;
import com.nsikakthompson.android_article_webtask.ui.CallbackView;
import com.nsikakthompson.android_article_webtask.ui.list.MainActivity;
import com.nsikakthompson.android_article_webtask.viewmodel.ViewmodelFactoryins;

public class AddArticleActivity extends AppCompatActivity implements CallbackView {

    EditText mAuthorTxt, mTitleTxt, mThumbUrlTxt, mContentTxt;
    private AddArticleActivityViewmodel addArticleActivityViewmodel;
    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        mAuthorTxt = findViewById(R.id.author_text);
        mTitleTxt = findViewById(R.id.title_text);
        mThumbUrlTxt = findViewById(R.id.thumburl_text);
        mContentTxt = findViewById(R.id.content_text);

        pd = new ProgressDialog(this);
        pd.setMessage("Adding Article");
        pd.setCancelable(false);


        AppController appController = (AppController) getApplication();
        addArticleActivityViewmodel = ViewModelProviders.of(this, new ViewmodelFactoryins(appController)).get(AddArticleActivityViewmodel.class);

        addArticleActivityViewmodel.attachView(this);
    }

    public void addArticle(View view) {

        pd.show();
        String author = mAuthorTxt.getText().toString();
        String title = mTitleTxt.getText().toString();
        String content = mContentTxt.getText().toString();
        String thumburl = mThumbUrlTxt.getText().toString();

        Article article = new Article();
        article.setAuthor(author);
        article.setContent(content);
        article.setImageurl(thumburl);
        article.setTitle(title);

       /* Call the viewmodel to add the article via the API service*/
        addArticleActivityViewmodel.addArticle(article);


    }

    @Override
    public void onSuccess(String message) {

        pd.hide();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }

    @Override
    public void onError(String e) {
        pd.hide();
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();

    }
}
