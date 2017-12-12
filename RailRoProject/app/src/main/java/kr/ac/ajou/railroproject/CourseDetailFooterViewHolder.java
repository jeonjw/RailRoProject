package kr.ac.ajou.railroproject;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private long likeCount;
    private DatabaseReference databaseReference;

    public CourseDetailFooterViewHolder(final View itemView) {
        super(itemView);

        databaseReference = FirebaseDatabase.getInstance().getReference();

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

        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Course").child(courseKey).child("likeCount").setValue(likeCount+1);
            }
        });
    }

    public void setCourseData(final String courseKey, String courseTitle){
        this.courseKey = courseKey;
        this.courseTitle = courseTitle;

        databaseReference.child("Course").child(courseKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                likeCount = (long) dataSnapshot.child("likeCount").getValue();
                long hitCount = (long) dataSnapshot.child("hitCount").getValue();
                databaseReference.child("Course").child(courseKey).child("hitCount").setValue(hitCount+1);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
