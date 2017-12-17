package kr.ac.ajou.railroproject.Course;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import kr.ac.ajou.railroproject.R;

public class CourseAddFragment extends Fragment {

    private List<Station> stationList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_add, container, false);
        final EditText courseNameEditText = view.findViewById(R.id.course_name_edit_text);


        FloatingActionButton floatingActionButton = view.findViewById(R.id.place_add_button);
        RecyclerView recyclerView = view.findViewById(R.id.station_input_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        final StationInputAdapter stationInputAdapter = new StationInputAdapter(stationList);
        recyclerView.setAdapter(stationInputAdapter);


        Button button = view.findViewById(R.id.tem_add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course course = new Course(courseNameEditText.getText().toString(),
                        FirebaseAuth.getInstance().getCurrentUser().getUid(),
                        stationInputAdapter.finish(), FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString());

                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                database.child("Course").push().setValue(course);
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Station station = new Station();
                stationList.add(station);
                stationInputAdapter.notifyDataSetChanged();

            }
        });
        return view;
    }
}
