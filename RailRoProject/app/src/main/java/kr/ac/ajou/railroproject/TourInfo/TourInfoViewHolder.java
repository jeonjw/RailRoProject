package kr.ac.ajou.railroproject.TourInfo;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kr.ac.ajou.railroproject.GlideApp;
import kr.ac.ajou.railroproject.R;
import kr.ac.ajou.railroproject.Retrofit.TourRepo;

public class TourInfoViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView titleTextView;
    private TextView telNumberTextView;
    private TourRepo.TourSpotRepo.Response.Body.Items.Item item;

    public TourInfoViewHolder(final View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.tour_info_image_view);
        titleTextView = itemView.findViewById(R.id.tour_info_title_text_view);
        telNumberTextView = itemView.findViewById(R.id.tour_info_tel_number_text_view);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(itemView.getContext(), TourDetailActivity.class);

                intent.putExtra("CONTENT_ID", String.valueOf(item.getContentid()));
                intent.putExtra("CONTENT_TYPE_ID", String.valueOf(item.getContenttypeid()));
                intent.putExtra("FIRST_IMAGE_URL", item.getFirstimage());
                intent.putExtra("ADDRESS", item.getAddr1());
                intent.putExtra("MAP_X", item.getMapx());
                intent.putExtra("MAP_Y", item.getMapy());
                intent.putExtra("TITLE", item.getTitle());
                itemView.getContext().startActivity(intent);
            }
        });
    }


    public void bindData(TourRepo.TourSpotRepo.Response.Body.Items.Item item) {
        GlideApp.with(imageView)
                .load(item.getFirstimage())
                .placeholder(R.drawable.image_not_found)
                .into(imageView);
        titleTextView.setText(item.getTitle());
        String tel;
        if (item.getTel() != null)
            tel = item.getTel();
        else
            tel = "전화번호 없음";
        telNumberTextView.setText("tel : " + tel);

        this.item = item;
    }
}
