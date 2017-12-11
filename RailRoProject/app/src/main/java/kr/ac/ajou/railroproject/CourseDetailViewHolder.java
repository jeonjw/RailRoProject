package kr.ac.ajou.railroproject;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017-11-09.
 */

public class CourseDetailViewHolder extends RecyclerView.ViewHolder{

    private TextView tvStationName;
    private TextView tvCountDay;
    private RecyclerView courseDetailPlaceRecyclerView;
    private Date minDate;
    private List<Place> placeList;

    public CourseDetailViewHolder(View itemView, Date minDate) {
        super(itemView);

        this.minDate = minDate;

        tvStationName = itemView.findViewById(R.id.tv_course_detail_item_station_name);
        tvCountDay = itemView.findViewById(R.id.tv_course_detail_item_station_day);
        courseDetailPlaceRecyclerView = itemView.findViewById(R.id.course_detail_place_recyler_view);
        courseDetailPlaceRecyclerView.setNestedScrollingEnabled(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
        courseDetailPlaceRecyclerView.setLayoutManager(layoutManager);

    }

    public void bindDetailStation(Station station) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(station.getDate());

        courseDetailPlaceRecyclerView.setAdapter(new PlaceAdapter(station.getPlaceList()));
        tvCountDay.setText("day " + String.valueOf(((date.getTime() - minDate.getTime())/(1000*60*60*24))+1));
        tvStationName.setText(station.getStationName());
    }
}
