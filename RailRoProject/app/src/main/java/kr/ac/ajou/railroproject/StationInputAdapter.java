package kr.ac.ajou.railroproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StationInputAdapter extends RecyclerView.Adapter<StationInputViewHolder> {
    private int stationCount;
    @Override
    public StationInputViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_station_add, parent, false);
        return new StationInputViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StationInputViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return stationCount;
    }

    public void addInputView(){
        stationCount++;
    }
}
