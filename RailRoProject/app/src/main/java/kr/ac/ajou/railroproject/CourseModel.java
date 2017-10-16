package kr.ac.ajou.railroproject;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by admin on 2017-10-16.
 */

public class CourseModel {
    private FirebaseRecyclerAdapter<Course, CourseViewHolder> adapter;

    public CourseModel() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        adapter = new FirebaseRecyclerAdapter<Course, CourseViewHolder>(Course.class, R.layout.list_item_course,
                CourseViewHolder.class, databaseReference.child("Course")) {

            @Override
            protected void populateViewHolder(CourseViewHolder viewHolder, Course model, int position) {

                viewHolder.bindCourse(model);
            }

        };

    }

    public FirebaseRecyclerAdapter<Course, CourseViewHolder> getAdapter() {
        return adapter;
    }


}