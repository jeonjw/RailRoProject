package kr.ac.ajou.railroproject.Course;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kr.ac.ajou.railroproject.R;

/**
 * Created by admin on 2017-11-09.
 */

public class CourseDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    private List<Station> stationList;
    private Course course;

    public CourseDetailAdapter(List<Station> list, Course course){
        this.stationList = list;
        this.course = course;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;

        if(viewType == TYPE_ITEM){
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

            return new CourseDetailViewHolder(itemView, minDate);
        }else if (viewType == TYPE_FOOTER){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_course_footer, parent, false);

            return new CourseDetailFooterViewHolder(itemView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof CourseDetailViewHolder) {
            //뷰 타입이 아이템일 경우
            CourseDetailViewHolder item_holder = (CourseDetailViewHolder) holder;
            try {
                item_holder.bindDetailStation(stationList.get(position));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (holder instanceof CourseDetailFooterViewHolder) {
            //뷰타입이 푸터 생성하기 버튼일 경우
            CourseDetailFooterViewHolder footerHolder = (CourseDetailFooterViewHolder) holder;
            footerHolder.setCourseData(course.getCourseKey(), course.getTitle());
        }

    }


    @Override
    public int getItemCount() {
        return stationList.size()+1;
    }

    //뷰타입 정하기
    @Override
    public int getItemViewType(int position) {
        if (isPositionFooter(position)) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    //true가 반환되면 리스트의 끝임을 알수있다.
    private boolean isPositionFooter(int position) {
        return position == stationList.size();
    }

}
