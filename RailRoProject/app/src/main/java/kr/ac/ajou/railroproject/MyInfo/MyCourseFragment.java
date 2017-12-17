package kr.ac.ajou.railroproject.MyInfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


import kr.ac.ajou.railroproject.Course.Course;
import kr.ac.ajou.railroproject.Course.CourseFragment;
import kr.ac.ajou.railroproject.Course.CourseViewHolder;
import kr.ac.ajou.railroproject.R;

public class MyCourseFragment extends CourseFragment {
    private FirebaseRecyclerAdapter<Course, CourseViewHolder> adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.tv_recommend_course).setVisibility(View.INVISIBLE);
    }

    private void createAdapter() {
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
                course.setCourseKey(strs[strs.length - 1]);

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
}
