package kr.ac.ajou.railroproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by admin on 2017-10-16.
 */

public class CourseItemAdapter extends RecyclerView.Adapter<CourseItemAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        //View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_text, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            //textView = (TextView) itemView.findViewById(R.id.text_view);
        }
    }
}
