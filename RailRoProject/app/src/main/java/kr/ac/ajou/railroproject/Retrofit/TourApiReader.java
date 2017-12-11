package kr.ac.ajou.railroproject.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TourApiReader extends Thread {
    private Retrofit client;
    private TourRepo tourRepo;
    private String apiURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/";
    private String key = "l8FhPRcAWi8IadpttfL%2FHoQKxjdIzPQ1NImTqOQZnxBgE%2BtGwOPyX2%2FGKyoTVZc6ecH631rDmLJQO1xLAEbKcg%3D%3D";

    public TourApiReader() {
        client = new Retrofit.Builder().baseUrl("http://api.visitkorea.or.kr/openapi/service/").addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Override
    public void run() {

        super.run();

        TourRepo.TourApiInterface service = client.create(TourRepo.TourApiInterface.class);
        Call<TourRepo> call = service.getTourRetrofit(key, "ETC", "test", "json");

        call.enqueue(new Callback<TourRepo>() {
            @Override
            public void onResponse(Call<TourRepo> call, Response<TourRepo> response) {
                if (response.isSuccessful()) {
                    tourRepo = response.body();

                    for(TourRepo.Response.Body.Items.Item s : tourRepo.getResponse().getBody().getItems().getItemList()){
                        System.out.println(s.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<TourRepo> call, Throwable t) {

            }
        });

    }

}
