package kr.ac.ajou.railroproject.TourInfo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import kr.ac.ajou.railroproject.OnApiReadListener;
import kr.ac.ajou.railroproject.R;
import kr.ac.ajou.railroproject.Retrofit.TourApiReader;
import kr.ac.ajou.railroproject.Retrofit.TourRepo;

public abstract class BaseTourFragment extends Fragment implements LocationListener {
    private List<TourRepo.Response.Body.Items.Item> mainAreaList;
    private List<TourRepo.Response.Body.Items.Item> detailAreaList;
    private List<TourRepo.TourSpotRepo.Response.Body.Items.Item> tourSpotList;
    private TourApiReader apiReader;
    private ArrayAdapter<String> mainAreaAdapter;
    private ArrayAdapter<String> detailAreaAdapter;
    private TourInfoAdapter tourInfoAdapter;
    private ArrayList<String> mainAreaNameList;
    private ArrayList<String> detailAreaNameList;

    private LocationManager locationManager;
    private Location location;
    private int currentAreaCode;
    private int currentDetailAreaCode;

    private static final int MAIN_AREA_CODE = 0;
    private static final int DETAIL_AREA_CODE = 1;
    private static final int AREA_BASED_SPOT_CODE = 2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_tour, container, false);
        setHasOptionsMenu(true);
        Spinner mainAreaSpinner = view.findViewById(R.id.tour_main_area_spinner);
        Spinner detailAreaSpinner = view.findViewById(R.id.tour_detail_area_spinner);
        RecyclerView recyclerView = view.findViewById(R.id.info_recycler_view);
        TextView locationBasedSearch = view.findViewById(R.id.location_based_search);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        tourInfoAdapter = new TourInfoAdapter();
        recyclerView.setAdapter(tourInfoAdapter);

        mainAreaNameList = new ArrayList<>();
        detailAreaNameList = new ArrayList<>();

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        checkPermission();


        locationBasedSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiReader.readTourInfo("locationBasedList",
                        null,
                        null,
                        String.valueOf(location.getLongitude()),
                        String.valueOf(location.getLatitude()), "20000",
                        getContentTypeId(), null);
            }
        });


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
                apiReader.readTourInfo(getApiRequest(),
                        String.valueOf(currentAreaCode), null,
                        null, null, null,
                        getContentTypeId(), null);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        detailAreaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentDetailAreaCode = detailAreaList.get(position).getCode();
                apiReader.readTourInfo(getApiRequest(),
                        String.valueOf(currentAreaCode),
                        String.valueOf(currentDetailAreaCode),
                        null, null, null,
                        getContentTypeId(), null);

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

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Toast.makeText(getActivity(), "위치 권한 허용", Toast.LENGTH_SHORT).show();
                }
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 999);
            }
        } else {
            getLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 999:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }


    @Override
    public void onPrepareOptionsMenu(final Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        searchItem.setVisible(true);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                System.out.println("SEARCH : " + text);
                String keyword = null;
                try {
                    keyword = URLEncoder.encode(text, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                apiReader.readTourInfo("searchKeyword",
                        String.valueOf(currentAreaCode),
                        null,
                        null, null, null,
                        getContentTypeId(), keyword);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                return true;
            }
        });

        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                apiReader.readTourInfo(getApiRequest(),
                        String.valueOf(currentAreaCode),
                        String.valueOf(currentDetailAreaCode),
                        null, null, null,
                        getContentTypeId(), null);
                return true;
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            Log.v("Location Changed", location.getLatitude() + " and " + location.getLongitude());
            locationManager.removeUpdates(this);
        }
    }

    private void getLocation() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, BaseTourFragment.this);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    abstract public String getApiRequest();

    abstract public String getContentTypeId();
}
