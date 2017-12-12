package kr.ac.ajou.railroproject.Retrofit;

import java.util.List;

import kr.ac.ajou.railroproject.OnApiReadListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TourApiReader {
    private Retrofit client;
    private TourRepo tourRepo;
    private TourRepo.TourSpotRepo tourSpotRepo;
    private List<TourRepo.Response.Body.Items.Item> mainAreaList;
    private List<TourRepo.Response.Body.Items.Item> detailAreaList;
    private String apiURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/";
    private String key = "l8FhPRcAWi8IadpttfL%2FHoQKxjdIzPQ1NImTqOQZnxBgE%2BtGwOPyX2%2FGKyoTVZc6ecH631rDmLJQO1xLAEbKcg%3D%3D";
    private OnApiReadListener onDataChangedListener;
    private TourRepo.TourApiInterface service;

    private static final int MAIN_AREA_CODE = 0;
    private static final int DETAIL_AREA_CODE = 1;

    public TourApiReader(OnApiReadListener onDataChangedListener) {
        this.onDataChangedListener = onDataChangedListener;
        client = new Retrofit.Builder().baseUrl("http://api.visitkorea.or.kr/openapi/service/rest/KorService/").addConverterFactory(GsonConverterFactory.create()).build();
        service = client.create(TourRepo.TourApiInterface.class);
    }

    public void readMainAreaCode() {

        Call<TourRepo> call = service.getTourRetrofit("areaCode", key, null, "100",null, "ETC", "test", "json");

        call.enqueue(new Callback<TourRepo>() {
            @Override
            public void onResponse(Call<TourRepo> call, Response<TourRepo> response) {
                if (response.isSuccessful()) {
                    tourRepo = response.body();

                    mainAreaList = tourRepo.getResponse().getBody().getItems().getItemList();
                    onDataChangedListener.onRead(MAIN_AREA_CODE);
                }
            }

            @Override
            public void onFailure(Call<TourRepo> call, Throwable t) {
            }
        });
    }

    public void readDetailAreaCode(String detailCode) {

        Call<TourRepo> call = service.getTourRetrofit("areaCode", key, detailCode, "100",null, "ETC", "test", "json");

        call.enqueue(new Callback<TourRepo>() {
            @Override
            public void onResponse(Call<TourRepo> call, Response<TourRepo> response) {
                if (response.isSuccessful()) {
                    tourRepo = response.body();

                    detailAreaList = tourRepo.getResponse().getBody().getItems().getItemList();
                    onDataChangedListener.onRead(DETAIL_AREA_CODE);
                }
            }

            @Override
            public void onFailure(Call<TourRepo> call, Throwable t) {
            }
        });
    }

    public void readTourSpot(String areaCode, String detailAreaCode) {
        Call<TourRepo.TourSpotRepo> call = service.getTourSpotRetrofit("areaBasedList", key, areaCode, detailAreaCode,null, null,"ETC", "test", "json");

        call.enqueue(new Callback<TourRepo.TourSpotRepo>() {
            @Override
            public void onResponse(Call<TourRepo.TourSpotRepo> call, Response<TourRepo.TourSpotRepo> response) {
                System.out.println(response.raw());
                if (response.isSuccessful()) {
                    tourSpotRepo = response.body();

                    System.out.println("TEST Spot" +response.raw());

//                    mainAreaList = tourRepo.getResponse().getBody().getItems().getItemList();
//                    onDataChangedListener.onDataChanged();
                }
            }

            @Override
            public void onFailure(Call<TourRepo.TourSpotRepo> call, Throwable t) {

            }
        });


    }


    public List<TourRepo.Response.Body.Items.Item> getMainAreaList() {
        return mainAreaList;
    }

    public List<TourRepo.Response.Body.Items.Item> getDetailAreaList() {
        return detailAreaList;
    }
}
