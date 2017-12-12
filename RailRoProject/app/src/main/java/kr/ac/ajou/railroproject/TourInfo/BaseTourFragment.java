package kr.ac.ajou.railroproject.TourInfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import kr.ac.ajou.railroproject.OnApiReadListener;
import kr.ac.ajou.railroproject.R;
import kr.ac.ajou.railroproject.Retrofit.TourApiReader;
import kr.ac.ajou.railroproject.Retrofit.TourRepo;


public class BaseTourFragment extends Fragment {
    private List<TourRepo.Response.Body.Items.Item> mainAreaList;
    private List<TourRepo.Response.Body.Items.Item> detailAreaList;
    private List<TourRepo.TourSpotRepo.Response.Body.Items.Item> tourSpotList;
    private TourApiReader apiReader;
    private ArrayAdapter<String> mainAreaAdapter;
    private ArrayAdapter<String> detailAreaAdapter;
    private TourInfoAdapter tourInfoAdapter;
    private ArrayList<String> mainAreaNameList;
    private ArrayList<String> detailAreaNameList;

    private int currentAreaCode;
    private int currentDetailAreaCode;

    private static final int MAIN_AREA_CODE = 0;
    private static final int DETAIL_AREA_CODE = 1;
    private static final int AREA_BASED_SPOT_CODE = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_tour, container, false);

        Spinner mainAreaSpinner = view.findViewById(R.id.tour_main_area_spinner);
        Spinner detailAreaSpinner = view.findViewById(R.id.tour_detail_area_spinner);
        RecyclerView recyclerView = view.findViewById(R.id.info_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tourInfoAdapter = new TourInfoAdapter();
        recyclerView.setAdapter(tourInfoAdapter);

        mainAreaNameList = new ArrayList<>();
        detailAreaNameList = new ArrayList<>();


        apiReader = new TourApiReader(new OnApiReadListener() {
            @Override
            public void onRead(int requestType) {

                if (requestType == MAIN_AREA_CODE) {
                    mainAreaList = apiReader.getMainAreaList();
                    for (int i = 0; i < mainAreaList.size(); i++) {
                        mainAreaNameList.add(mainAreaList.get(i).getName());
                    }
                    mainAreaAdapter.notifyDataSetChanged();
                } else if (requestType == DETAIL_AREA_CODE) {
                    detailAreaList = apiReader.getDetailAreaList();

                    for (int i = 0; i < detailAreaList.size(); i++) {
                        detailAreaNameList.add(detailAreaList.get(i).getName());
                    }
                    detailAreaAdapter.notifyDataSetChanged();
                } else if (requestType == AREA_BASED_SPOT_CODE) {
                    tourSpotList = apiReader.getTourSpotList();
                    tourInfoAdapter.setSpotList(tourSpotList);
                }
            }
        });

        apiReader.readMainAreaCode();

        mainAreaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (detailAreaList != null) {
                    detailAreaList.clear();
                    detailAreaNameList.clear();
                }
                currentAreaCode = mainAreaList.get(position).getCode();
                apiReader.readDetailAreaCode(String.valueOf(currentAreaCode));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        detailAreaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentDetailAreaCode = detailAreaList.get(position).getCode();
                apiReader.readTourSpot(String.valueOf(currentAreaCode), String.valueOf(currentDetailAreaCode));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mainAreaAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, mainAreaNameList);
        mainAreaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mainAreaSpinner.setAdapter(mainAreaAdapter);

        detailAreaAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, detailAreaNameList);
        detailAreaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        detailAreaSpinner.setAdapter(detailAreaAdapter);

        return view;
    }
}
