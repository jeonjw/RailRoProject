package kr.ac.ajou.railroproject.Course;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kr.ac.ajou.railroproject.R;

public class StationAdapter extends RecyclerView.Adapter<StationViewHolder>{

    private List<Station> stationList;

    public StationAdapter(List<Station> stationList) {
        this.stationList = stationList;
    }

    @Override
    public StationViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_station, parent, false);
        return new StationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StationViewHolder stationViewHolder, int position) {
        stationViewHolder.bindStation(stationList.get(position));
    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }
}
