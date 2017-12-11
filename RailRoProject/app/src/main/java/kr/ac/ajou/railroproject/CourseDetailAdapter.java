package kr.ac.ajou.railroproject;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017-11-09.
 */

public class CourseDetailAdapter extends RecyclerView.Adapter<CourseDetailViewHolder>{

    private List<Station> stationList;
    private String courseKey;

    public CourseDetailAdapter(List<Station> list, String courseKey){
        this.stationList = list;
        this.courseKey = courseKey;
    }

    @Override
    public CourseDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_course_view_station, parent, false);

        Date minDate = null;
        try {
            minDate = new SimpleDateFormat("yyyy-MM-dd").parse("2200-12-31");
        } catch (ParseException e) {e.printStackTrace();}

        for(Station station : stationList){

            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(station.getDate());

                if(date.before(minDate)){
                    minDate = date;
                }
            } catch (ParseException e) {e.printStackTrace();}

        }

        minDate.setTime(minDate.getTime()+(100*60*60*24));
        return new CourseDetailViewHolder(itemView, minDate);
    }

    @Override
    public void onBindViewHolder(CourseDetailViewHolder holder, int position) {
        try {
            holder.bindDetailStation(stationList.get(position));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return stationList.size();
    }
}
