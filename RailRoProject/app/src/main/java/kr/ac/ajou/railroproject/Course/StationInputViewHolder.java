package kr.ac.ajou.railroproject.Course;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import kr.ac.ajou.railroproject.OnEnrollmentListener;
import kr.ac.ajou.railroproject.R;

public class StationInputViewHolder extends RecyclerView.ViewHolder {
    private EditText stationNameEditText;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private ImageButton btnDatePicker;
    private TextView tvDate;

    private PlaceAdapter placeAdapter;
    private Calendar myCalendar = Calendar.getInstance();
    private String dateStr;

    public StationInputViewHolder(View itemView) {
        super(itemView);
        final Context context = itemView.getContext();
        stationNameEditText = itemView.findViewById(R.id.course_name_edit_text);
        recyclerView = itemView.findViewById(R.id.place_recycler_view);
        btnDatePicker = itemView.findViewById(R.id.btn_date_picker);
        LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        placeAdapter = new PlaceAdapter();

        recyclerView.setAdapter(placeAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        floatingActionButton = itemView.findViewById(R.id.place_add_button);
        tvDate = itemView.findViewById(R.id.tv_date);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager =
                        ((AppCompatActivity) context).getSupportFragmentManager();
                PlaceInputDialog dialog = PlaceInputDialog.newInstance();

                dialog.setOnEnrollListener(new OnEnrollmentListener() {
                    @Override
                    public void onEnroll(Place place) {
                        placeAdapter.addPlace(place);
                        System.out.println("TEST " + placeAdapter.getItemCount());
                    }

                });


                dialog.show(fragmentManager, "placeInputDialog");
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
                dateStr = sdf.format(myCalendar.getTime());
                tvDate.setText(dateStr);
            }

        };

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(btnDatePicker.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public Station getStation() {
        return new Station(stationNameEditText.getText().toString(),
                dateStr, placeAdapter.getPlaceList());
    }

}
