package kr.ac.ajou.railroproject.Retrofit;

import java.util.List;

import kr.ac.ajou.railroproject.OnApiReadListener;
import kr.ac.ajou.railroproject.TourInfo.TourDetailRepo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TourApiReader {
    private Retrofit client;
    private TourRepo tourRepo;
    private TourRepo.TourSpotRepo tourSpotRepo;
    private TourDetailRepo tourDetailRepo;
    private List<TourRepo.Response.Body.Items.Item> mainAreaList;
    private List<TourRepo.Response.Body.Items.Item> detailAreaList;
    private List<TourRepo.TourSpotRepo.Response.Body.Items.Item> tourSpotList;
    private TourDetailRepo.Response.Body.Items.Item detailItem;
    private String apiURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/";
    private String key = "l8FhPRcAWi8IadpttfL%2FHoQKxjdIzPQ1NImTqOQZnxBgE%2BtGwOPyX2%2FGKyoTVZc6ecH631rDmLJQO1xLAEbKcg%3D%3D";
    private OnApiReadListener onApiReadListener;
    private TourRepo.TourApiInterface service;

    private static final int MAIN_AREA_CODE = 0;
    private static final int DETAIL_AREA_CODE = 1;
    private static final int AREA_BASED_SPOT_CODE = 2;

    public TourApiReader(OnApiReadListener onApiReadListener) {
        this.onApiReadListener = onApiReadListener;
        client = new Retrofit.Builder().baseUrl("http://api.visitkorea.or.kr/openapi/service/rest/KorService/").addConverterFactory(GsonConverterFactory.create()).build();
        service = client.create(TourRepo.TourApiInterface.class);
    }

    public void readMainAreaCode() {

        Call<TourRepo> call = service.getTourRetrofit("areaCode", key, null, "100", null, "ETC", "test", "json");

        call.enqueue(new Callback<TourRepo>() {
            @Override
            public void onResponse(Call<TourRepo> call, Response<TourRepo> response) {
                if (response.isSuccessful()) {
                    tourRepo = response.body();

                    mainAreaList = tourRepo.getResponse().getBody().getItems().getItemList();
                    onApiReadListener.onRead(MAIN_AREA_CODE);
                }
            }

            @Override
            public void onFailure(Call<TourRepo> call, Throwable t) {
            }
        });
    }

    public void readDetailAreaCode(String detailCode) {

        Call<TourRepo> call = service.getTourRetrofit("areaCode", key, detailCode, "100", null, "ETC", "test", "json");

        call.enqueue(new Callback<TourRepo>() {
            @Override
            public void onResponse(Call<TourRepo> call, Response<TourRepo> response) {
                if (response.isSuccessful()) {
                    tourRepo = response.body();

                    detailAreaList = tourRepo.getResponse().getBody().getItems().getItemList();
                    onApiReadListener.onRead(DETAIL_AREA_CODE);
                }
            }

            @Override
            public void onFailure(Call<TourRepo> call, Throwable t) {
            }
        });
    }

    public void readTourInfo(String base, String areaCode, String detailAreaCode, String mapX, String mapY, String radius, String contentId, String keyword) {
        Call<TourRepo.TourSpotRepo> call = service.getTourSpotRetrofit(base, key, null,
                areaCode, detailAreaCode,
                mapX, mapY, radius,
                "1000000", contentId, keyword,
                "ETC", "test", "json");

        call.enqueue(new Callback<TourRepo.TourSpotRepo>() {
            @Override
            public void onResponse(Call<TourRepo.TourSpotRepo> call, Response<TourRepo.TourSpotRepo> response) {
                System.out.println(response.raw());
                if (response.isSuccessful()) {
                    tourSpotRepo = response.body();

                    tourSpotList = tourSpotRepo.getResponse().getBody().getItems().getItemList();
                    onApiReadListener.onRead(AREA_BASED_SPOT_CODE);
                }
            }

            @Override
            public void onFailure(Call<TourRepo.TourSpotRepo> call, Throwable t) {
                System.out.println(t.getMessage());
                System.out.println(call.request());
            }
        });


    }

    public void readTourDetailInfo(String contentId, final String contentTypeId) {
        Call<TourDetailRepo> call = service.getTourDetailInfo("detailIntro", key,
                "1000000", contentId, contentTypeId,
                "ETC", "test", "json");

        call.enqueue(new Callback<TourDetailRepo>() {
            @Override
            public void onResponse(Call<TourDetailRepo> call, Response<TourDetailRepo> response) {
                System.out.println(response.raw());
                if (response.isSuccessful()) {
                    tourDetailRepo = response.body();
                    detailItem = tourDetailRepo.getResponse().getBody().getItems().getItem();
                    onApiReadListener.onRead(Integer.parseInt(contentTypeId));
                    System.out.println("CTI : " + contentTypeId);
                }
            }

            @Override
            public void onFailure(Call<TourDetailRepo> call, Throwable t) {
                System.out.println(t.getMessage());
                System.out.println(call.request());
            }
        });


    }


    public List<TourRepo.Response.Body.Items.Item> getMainAreaList() {
        return mainAreaList;
    }

    public List<TourRepo.Response.Body.Items.Item> getDetailAreaList() {
        return detailAreaList;
    }

    public List<TourRepo.TourSpotRepo.Response.Body.Items.Item> getTourSpotList() {
        return tourSpotList;
    }

    public TourDetailRepo.Response.Body.Items.Item getDetailItem() {
        return detailItem;
    }
}
