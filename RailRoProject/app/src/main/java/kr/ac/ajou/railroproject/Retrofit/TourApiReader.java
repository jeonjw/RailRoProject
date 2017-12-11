package kr.ac.ajou.railroproject.Retrofit;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jinwoo on 2017. 12. 11..
 */

public class TourApiReader extends Thread {
    TourRepo tourRepo;
    String TAG = "API_READER";
    private String apiURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService";
    private String key = "l8FhPRcAWi8IadpttfL%2FHoQKxjdIzPQ1NImTqOQZnxBgE%2BtGwOPyX2%2FGKyoTVZc6ecH631rDmLJQO1xLAEbKcg%3D%3D";

    @Override
    public void run() {
        super.run();
        Retrofit client = new Retrofit.Builder().baseUrl("http://api.visitkorea.or.kr/openapi/service/rest/KorService/").addConverterFactory(GsonConverterFactory.create()).build();
        TourRepo.TourApiInterface service = client.create(TourRepo.TourApiInterface.class);
        Call<TourRepo> call = service.getTourRetrofit(key,"AND","test","json");
        call.enqueue(new Callback<TourRepo>() {
            @Override
            public void onResponse(Call<TourRepo> call, Response<TourRepo> response) {
                if(response.isSuccessful()){
                    tourRepo = response.body();
                    System.out.println("API : " + response.raw());
                    System.out.println("API : " + response.toString());
//                    System.out.println("API : " + response.);
//                    System.out.println("API : " + tourRepo.body.getItems());

//                    if(weatherRepo.getResult().getCode().equals("9200")){ // 9200 = 성공
//                        Weather.getInstance().setTemperature(weatherRepo.getWeather().getHourly().get(0).getTemperature().getTc());
//                        Weather.getInstance().setCloud(weatherRepo.getWeather().getHourly().get(0).getSky().getName());
//                        Weather.getInstance().setWind_direction(weatherRepo.getWeather().getHourly().get(0).getWind().getWdir());
//                        Weather.getInstance().setWind_speed(weatherRepo.getWeather().getHourly().get(0).getWind().getWspd());
//                        Weather.getInstance().setIcon(weatherRepo.getWeather().getHourly().get(0).getSky().getCode());
//
//
//                    }else{
//                        Log.e(TAG,"요청 실패 :"+weatherRepo.getResult().getCode());
//                        Log.e(TAG,"메시지 :"+weatherRepo.getResult().getMessage());
//                    }
                }
            }

            @Override
            public void onFailure(Call<TourRepo> call, Throwable t) {

            }


        });

    }

    public TourApiReader(){
    }
}
