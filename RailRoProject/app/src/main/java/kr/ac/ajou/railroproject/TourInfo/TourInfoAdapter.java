package kr.ac.ajou.railroproject.TourInfo;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.ac.ajou.railroproject.R;
import kr.ac.ajou.railroproject.Retrofit.TourRepo;

public class TourInfoAdapter extends RecyclerView.Adapter<TourInfoViewHolder>{
    List<TourRepo.TourSpotRepo.Response.Body.Items.Item> spotList = new ArrayList<>();
    @Override
    public TourInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_tour_info, parent, false);

        return new TourInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TourInfoViewHolder holder, int position) {
        holder.bindData(spotList.get(position));
    }

    public void setSpotList(List<TourRepo.TourSpotRepo.Response.Body.Items.Item> spotList) {
        this.spotList = spotList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return spotList.size();
    }
}
