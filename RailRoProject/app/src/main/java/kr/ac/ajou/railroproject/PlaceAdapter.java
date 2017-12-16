package kr.ac.ajou.railroproject;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceViewHolder> {

    private List<Place> placeList = new ArrayList<>();

    public PlaceAdapter(){
    }

    public PlaceAdapter(List<Place> list){
        this.placeList = list;
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_course_view_place, parent, false);
        return new PlaceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        holder.bindData(placeList.get(position));
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public void addPlace(Place place){
        placeList.add(place);
        notifyDataSetChanged();
    }
}
