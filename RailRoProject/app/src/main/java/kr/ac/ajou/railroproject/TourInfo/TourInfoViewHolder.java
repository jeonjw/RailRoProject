package kr.ac.ajou.railroproject.TourInfo;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kr.ac.ajou.railroproject.R;
import kr.ac.ajou.railroproject.Retrofit.TourRepo;

public class TourInfoViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView titleTextView;
    public TourInfoViewHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.tour_info_image_view);
        titleTextView = itemView.findViewById(R.id.tour_info_title_text_view);
    }


    public void bindData(TourRepo.TourSpotRepo.Response.Body.Items.Item item) {

    }
}
