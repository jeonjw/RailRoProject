package kr.ac.ajou.railroproject;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class CourseModel {
    private FirebaseRecyclerAdapter<Course, CourseViewHolder> adapter;

    public CourseModel() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        Query query = databaseReference
                .child("Course");

        FirebaseRecyclerOptions<Course> options =
                new FirebaseRecyclerOptions.Builder<Course>()
                        .setQuery(query, Course.class)
                        .build();



        adapter = new FirebaseRecyclerAdapter<Course, CourseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(CourseViewHolder holder, int position, Course model) {
                Course course = new Course();
                course = getItem(position);
                String strs[] = String.valueOf(getRef(position)).split("/");
                course.setCourseKey(strs[strs.length-1]);

                holder.bindCourse(course);
            }

            @Override
            public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_course, parent, false);

                return new CourseViewHolder(view);
            }

        };
        adapter.startListening();

    }

    public FirebaseRecyclerAdapter<Course, CourseViewHolder> getAdapter() {
        return adapter;
    }


}