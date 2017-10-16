package kr.ac.ajou.railroproject;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by admin on 2017-10-16.
 */

public class CourseViewHolder extends RecyclerView.ViewHolder {
    private TextView tvTitle;
    private ImageView ivProfile;
    private RecyclerView stationRecyclerView;
    private ImageView ivLike;
    private ImageView ivComment;
    private TextView tvLikeCount;
    private TextView tvCommentCount;

    CourseViewHolder(View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.tv_course_item_title);
        ivProfile = itemView.findViewById(R.id.iv_course_item_profile);
        stationRecyclerView = itemView.findViewById(R.id.course_item_station_list);
        ivLike = itemView.findViewById(R.id.iv_course_item_like);
        tvLikeCount = itemView.findViewById(R.id.tv_course_item_like_count);
        ivComment = itemView.findViewById(R.id.iv_course_item_comment);
        tvCommentCount = itemView.findViewById(R.id.tv_course_item_comment_count);

        ivProfile.setImageDrawable(ContextCompat.getDrawable(ivLike.getContext(), R.drawable.like));
        ivLike.setImageDrawable(ContextCompat.getDrawable(ivLike.getContext(), R.drawable.like));
        ivComment.setImageDrawable(ContextCompat.getDrawable(ivComment.getContext(), R.drawable.like));

        LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        stationRecyclerView.setLayoutManager(layoutManager);
    }

    public void bindCourse(Course course){
        //TO DO: ivProfile이미지갖다붙이기

        stationRecyclerView.setAdapter(new StationAdapter(course.getStationList()));
        tvTitle.setText(course.getTitle());
        tvCommentCount.setText(""+course.getCommentCount());
        tvLikeCount.setText(""+course.getLikeCount());
    }
}
