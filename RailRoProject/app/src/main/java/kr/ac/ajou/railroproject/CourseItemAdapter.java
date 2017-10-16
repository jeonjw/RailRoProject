package kr.ac.ajou.railroproject;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by admin on 2017-10-16.
 */

public class CourseItemAdapter extends RecyclerView.Adapter<CourseItemAdapter.CourseViewHolder> {

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View itemCourse = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_course, parent, false);
        return new CourseViewHolder(itemCourse);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ImageView ivProfile;
        private RecyclerView rvStationListView;
        private ImageView ivLike;
        private ImageView ivComment;
        private TextView tvLikeCount;
        private TextView tvCommentCount;

        CourseViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_course_item_title);
            ivProfile = itemView.findViewById(R.id.iv_course_item_profile);
            rvStationListView = itemView.findViewById(R.id.course_item_station_list);
            ivLike = itemView.findViewById(R.id.iv_course_item_like);
            tvLikeCount = itemView.findViewById(R.id.tv_course_item_like_count);
            ivComment = itemView.findViewById(R.id.iv_course_item_comment);
            tvCommentCount = itemView.findViewById(R.id.tv_course_item_comment_count);

            ivLike.setImageDrawable(ContextCompat.getDrawable(ivLike.getContext(), R.drawable.like));
            ivComment.setImageDrawable(ContextCompat.getDrawable(ivComment.getContext(), R.drawable.like));
        }
    }
}
