package kr.ac.ajou.railroproject.Course;


import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import kr.ac.ajou.railroproject.PhotoAdapter;
import kr.ac.ajou.railroproject.R;

public class PlaceViewHolder extends RecyclerView.ViewHolder {
    private TextView placeNameTextView;
    private TextView placeDetailTextView;
    private RecyclerView placePhotoRecyclerView;
    private PhotoAdapter photoAdapter;

    public PlaceViewHolder(View itemView) {
        super(itemView);
        placeNameTextView = itemView.findViewById(R.id.tv_course_view_item_place_name);
        placeDetailTextView = itemView.findViewById(R.id.tv_course_view_item_place_contents);
        placePhotoRecyclerView = itemView.findViewById(R.id.place_view_photo_recycler_view);
        placePhotoRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
    }

    public void bindData(Place place){
        placeNameTextView.setText(place.getPlaceName());
        placeDetailTextView.setText(place.getPlaceContent());
        photoAdapter = new PhotoAdapter(place.getImageUrlList());
        placePhotoRecyclerView.setAdapter(photoAdapter);
    }


}
