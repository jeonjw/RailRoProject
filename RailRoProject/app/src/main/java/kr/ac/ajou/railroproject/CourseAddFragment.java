package kr.ac.ajou.railroproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseAddFragment extends Fragment {

    private List<Station> stationList = new ArrayList<Station>();
    private Course course = new Course();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_add, container, false);

        course = new Course();

        FloatingActionButton floatingActionButton = view.findViewById(R.id.place_add_button);
        RecyclerView recyclerView = view.findViewById(R.id.station_input_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        final StationInputAdapter stationInputAdapter = new StationInputAdapter(stationList);
        recyclerView.setAdapter(stationInputAdapter);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stationInputAdapter.addInputView();
/*
                stationList = stationInputAdapter.getStationList();

                course.setStationList(stationList);

                for(Station stat : course.getStationList()){
                    stat.getStationName();
                }
*/

                Station station = new Station();
                stationList.add(station);
                stationInputAdapter.notifyDataSetChanged();


                /*
                dialog.setOnEnrollListener(new OnEnrollmentListener() {
                    @Override
                    public void onEnroll(Place place) {
                        placeAdapter.addPlace(place);
                        System.out.println("TEST " + placeAdapter.getItemCount());
                    }
                });*/
            }
        });
        return view;
    }
}
