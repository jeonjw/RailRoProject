package kr.ac.ajou.railroproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 2017-08-12.
 */

public class ArticleRecyclerAdapter extends RecyclerView.Adapter{

    private Context context;
    private ArrayList articleItems;

    public ArticleRecyclerAdapter(ArrayList items, Context mContext) {
        articleItems = items;
        context = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.articleCategory.setText("");
        holder.articleTitle.setText("");
        holder.articleWriter.setText("");
        holder.articleDate.setText("");
        holder.articleHitsCount.setText("");
        holder.articleGoodBad.setText("");
    }

    @Override
    public int getItemCount() {
        return articleItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView articleCategory;
        public TextView articleTitle;
        public TextView articleWriter;
        public TextView articleDate;
        public TextView articleHitsCount;
        public TextView articleGoodBad;

        public ViewHolder(View view) {
            super(view);
            articleCategory = (TextView) view.findViewById(R.id.article_item_category_textview);
            articleTitle = (TextView) view.findViewById(R.id.article_item_title_textview);
            articleWriter = (TextView) view.findViewById(R.id.article_item_writer_textview);
            articleDate = (TextView) view.findViewById(R.id.article_item_date_textview);
            articleHitsCount = (TextView) view.findViewById(R.id.article_item_hits_count_textview);
            articleGoodBad = (TextView) view.findViewById(R.id.article_item_good_bad_textview);
        }
    }

}
