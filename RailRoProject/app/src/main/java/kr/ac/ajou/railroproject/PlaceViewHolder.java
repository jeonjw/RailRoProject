package kr.ac.ajou.railroproject;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PlaceViewHolder extends RecyclerView.ViewHolder {
    private TextView placeNameTextView;
    private TextView placeDetailTextView;

    public PlaceViewHolder(View itemView) {
        super(itemView);
        placeNameTextView = itemView.findViewById(R.id.place_name_text_view);
        placeDetailTextView = itemView.findViewById(R.id.place_detail_text_view);
    }

    public void bindData(Place place){
        placeNameTextView.setText(place.getPlaceName());
        placeDetailTextView.setText(place.getCourseContent());
    }


}
