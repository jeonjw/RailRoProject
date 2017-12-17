package kr.ac.ajou.railroproject.Course;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.ac.ajou.railroproject.R;

public class StationInputAdapter extends RecyclerView.Adapter<StationInputViewHolder> {

    private List<Station> stationList = new ArrayList<Station>();
    private List<StationInputViewHolder> holderList = new ArrayList<>();

    public StationInputAdapter(List<Station> stationList) {
        this.stationList = stationList;
    }

    @Override
    public StationInputViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_station_add, parent, false);
        StationInputViewHolder viewHolder = new StationInputViewHolder(itemView);
        holderList.add(viewHolder);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(StationInputViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }


    public List<Station> getStationList() {
        return stationList;
    }

    public ArrayList<Station> finish() {
        ArrayList<Station> tempStationList = new ArrayList<>();
        for (StationInputViewHolder holder : holderList) {
            tempStationList.add(holder.getStation());
        }

        return tempStationList;
    }

}
