package kr.ac.ajou.railroproject;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

public class StationInputViewHolder extends RecyclerView.ViewHolder {
    private EditText stationNameEditText;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private PlaceAdapter placeAdapter;

    public StationInputViewHolder(View itemView) {
        super(itemView);
        final Context context = itemView.getContext();
        stationNameEditText = itemView.findViewById(R.id.course_name_edit_text);
        recyclerView = itemView.findViewById(R.id.place_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        placeAdapter = new PlaceAdapter();

        recyclerView.setAdapter(placeAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        floatingActionButton = itemView.findViewById(R.id.place_add_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager =
                        ((AppCompatActivity) context).getSupportFragmentManager();
                PlaceInputDialog dialog = PlaceInputDialog.newInstance();

                dialog.setOnEnrollListener(new OnEnrollmentListener() {
                    @Override
                    public void onEnroll(Place place) {
                        placeAdapter.addPlace(place);
//                        station.addPlace(place);
                        System.out.println("TEST " + placeAdapter.getItemCount());
                    }

                });

                dialog.show(fragmentManager, "placeInputDialog");
            }
        });

    }

    public Station getStation() {
        return new Station(stationNameEditText.getText().toString(),
                "20140404",placeAdapter.getPlaceList());
    }

}
