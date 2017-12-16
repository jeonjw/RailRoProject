package kr.ac.ajou.railroproject;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kr.ac.ajou.railroproject.CourseComment.CourseCommentDialogFragment;

/**
 * Created by admin on 2017-12-14.
 */

public class StationInputFooterViewHolder extends RecyclerView.ViewHolder {

    private Button btn_enroll;
    private Button btn_cancel;
    private DatabaseReference databaseReference;

    public StationInputFooterViewHolder(View itemView) {
        super(itemView);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        btn_enroll = itemView.findViewById(R.id.btn_station_add_enroll);
        btn_cancel = itemView.findViewById(R.id.btn_station_add_cancel);

        btn_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
