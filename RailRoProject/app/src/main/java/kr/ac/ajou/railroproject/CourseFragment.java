package kr.ac.ajou.railroproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CourseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);

        RecyclerView courseRecyclerView = view.findViewById(R.id.course_list);
        CourseModel courseModel = new CourseModel();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        courseRecyclerView.setLayoutManager(layoutManager);
        courseRecyclerView.setAdapter(courseModel.getAdapter());

        FloatingActionButton courseAddButton = view.findViewById(R.id.course_add_button);

        courseAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.main_container,new CourseAddFragment())
                        .addToBackStack(null).commit();
            }
        });


        return view;
    }
}
