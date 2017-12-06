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

public class CourseAddFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_add, container, false);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.place_add_button);
        RecyclerView recyclerView = view.findViewById(R.id.station_input_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        final StationInputAdapter stationInputAdapter = new StationInputAdapter();
        recyclerView.setAdapter(stationInputAdapter);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stationInputAdapter.addInputView();
                stationInputAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }
}
