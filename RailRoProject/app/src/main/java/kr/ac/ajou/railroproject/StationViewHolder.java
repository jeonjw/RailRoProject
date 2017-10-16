package kr.ac.ajou.railroproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by admin on 2017-10-16.
 */

class StationViewHolder extends RecyclerView.ViewHolder{

    private ImageView staionImageView;
    private TextView stationNameTextView;

    public StationViewHolder(View itemView) {
        super(itemView);
        staionImageView = itemView.findViewById(R.id.iv_station_item_icon);
        stationNameTextView = itemView.findViewById(R.id.tv_station_item_name);
    }

    public void bindStation(Station station){
            stationNameTextView.setText(station.getStationName());
    }
}
