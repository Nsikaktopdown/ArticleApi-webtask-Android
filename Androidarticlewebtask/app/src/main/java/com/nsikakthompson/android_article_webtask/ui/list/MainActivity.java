package com.nsikakthompson.android_article_webtask.ui.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nsikakthompson.android_article_webtask.AppController;
import com.nsikakthompson.android_article_webtask.R;
import com.nsikakthompson.android_article_webtask.model.Article;
import com.nsikakthompson.android_article_webtask.ui.CallbackView;
import com.nsikakthompson.android_article_webtask.ui.add.AddArticleActivity;
import com.nsikakthompson.android_article_webtask.viewmodel.ViewmodelFactoryins;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CallbackView {

    private List<Article> articlesList = new ArrayList<>();
    private RecyclerView recyclerView2;
    private ArticleAdapter mAdapter;
    private MainActivityViewmodel mainActivityViewmodel;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

               startActivity(new Intent(getApplicationContext(), AddArticleActivity.class));
            }
        });

        mProgressBar = findViewById(R.id.progress);
        mProgressBar.setVisibility(View.VISIBLE);

        recyclerView2 = (RecyclerView) findViewById(R.id.recycler_articles);
        mAdapter = new ArticleAdapter(articlesList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setNestedScrollingEnabled(false);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setAdapter(mAdapter);


        AppController appController = (AppController) getApplication();
        mainActivityViewmodel = ViewModelProviders.of(this, new ViewmodelFactoryins(appController)).get(MainActivityViewmodel.class);

        mainActivityViewmodel.attachView(this);

        //Call the viewmodel to fetch data from the server
        mainActivityViewmodel.requestData();

        //observe for livedata from the server
        mainActivityViewmodel.getArticles().observe(this, articles -> {
            mAdapter.setItems(articles);
            mAdapter.notifyDataSetChanged();

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(String message) {
        mProgressBar.setVisibility(View.GONE);

        Log.e("Mainactivity", "Article fetched");



    }

    @Override
    public void onError(String e) {
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();

    }
}
