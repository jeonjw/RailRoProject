package kr.ac.ajou.railroproject.Retrofit;


import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public class TourRepo {

    @SerializedName("header")
    Header result;
    @SerializedName("body")
    Body body;

    public static class Header {
        @SerializedName("resultCode")
        String resultCode;
        @SerializedName("resultMsg")
        String resultMsg;

        public String getResultCode() {
            return resultCode;
        }

        public String getResultMsg() {
            return resultMsg;
        }
    }

    public static class Body {
        @SerializedName("items")
        String items;

        public String getItems() {
            return items;
        }

    }


    public interface TourApiInterface {
//        _type
        @GET("areaCode")
        Call<TourRepo> getTourRetrofit(@Query("ServiceKey") String ServiceKey,
                                       @Query("MobileOS") String MobileOS,
                                       @Query("MobileApp") String MobileApp,
                                       @Query("_type") String _type);
    }



}
