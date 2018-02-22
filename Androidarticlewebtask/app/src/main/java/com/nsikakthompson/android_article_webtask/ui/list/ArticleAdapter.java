package com.nsikakthompson.android_article_webtask.ui.list;

/**
 * Created by Nsikak on 2/21/18.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nsikakthompson.android_article_webtask.R;
import com.nsikakthompson.android_article_webtask.model.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {

    private List<Article> articlesList = new ArrayList<>();

    public ArticleAdapter(List<Article> articlesList) {
        this.articlesList = articlesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Article item = articlesList.get(position);

        /// holder.layer.setBackgroundColor(item.getColor());
        // Toast.makeText(holder.layer.getContext(), item.getColor(), Toast.LENGTH_SHORT).show();

        holder.mTitle.setText(item.getTitle());
        holder.mContent.setText(item.getContent());
        holder.mAuthor.setText(holder.mAuthor.getContext().getString(R.string.author) + item.getAuthor());

        Glide.with(holder.mImageThum.getContext())
                .load(item.getImageurl())
                // resizes the image to these dimensions (in pixel). resize does not respect aspect ratio
                .into(holder.mImageThum);
     //   Picasso.with(holder.mImageThum.getContext()).load(item.getImageurl()).into(holder.mImageThum);




    }

    public void setItems(List<Article> articlesList) {
        this.articlesList = articlesList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mAuthor, mContent, mTitle;
        ImageView mImageThum;


        public MyViewHolder(View view) {
            super(view);
            mAuthor = (TextView) view.findViewById(R.id.article_author);
            mContent = (TextView) view.findViewById(R.id.article_content);
            mImageThum = view.findViewById(R.id.article_image);
            mTitle = view.findViewById(R.id.article_title);


        }
    }
}
