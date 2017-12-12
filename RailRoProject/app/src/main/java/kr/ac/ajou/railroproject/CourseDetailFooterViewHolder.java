package kr.ac.ajou.railroproject;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import kr.ac.ajou.railroproject.Comment.CommentDialogFragment;
import kr.ac.ajou.railroproject.CourseComment.CourseCommentDialogFragment;

/**
 * Created by admin on 2017-12-12.
 */

public class CourseDetailFooterViewHolder extends RecyclerView.ViewHolder{

    private Button btn_like;
    private Button btn_comment;
    private String courseKey;
    private String courseTitle;

    public CourseDetailFooterViewHolder(final View itemView) {
        super(itemView);

        btn_like = itemView.findViewById(R.id.btn_footer_like);
        btn_comment = itemView.findViewById(R.id.btn_footer_course_comment);

        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager =
                        ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager();

                CourseCommentDialogFragment dialog = CourseCommentDialogFragment.newInstance(courseKey, courseTitle);
                dialog.show(fragmentManager, "courseCommentDialog");
            }
        });
    }

    public void setCourseData(String courseKey, String courseTitle){
        this.courseKey = courseKey;
        this.courseTitle = courseTitle;
    }

}
