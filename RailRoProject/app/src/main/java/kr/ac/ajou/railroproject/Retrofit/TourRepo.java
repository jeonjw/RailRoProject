package kr.ac.ajou.railroproject.Retrofit;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class TourRepo {

    @SerializedName("response")
    Response response;

    public Response getResponse() {
        return response;
    }

    public static class Response {

        @SerializedName("header")
        Header header;
        @SerializedName("body")
        Body body;

        public Header getHeader() {
            return header;
        }

        public Body getBody() {
            return body;
        }

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
            Items items;

            public Items getItems() {
                return items;
            }

            public static class Items {

                @SerializedName("item")
                public List<Item> itemList = new ArrayList<>();

                public List<Item> getItemList() {
                    return itemList;
                }


                public static class Item {
                    @SerializedName("code")
                    int code;

                    @SerializedName("name")
                    String name;

                    @SerializedName("rnum")
                    int rnum;

                    public int getCode() {
                        return code;
                    }

                    public String getName() {
                        return name;
                    }

                    public int getRnum() {
                        return rnum;
                    }
                }

            }


        }

    }


    public interface TourApiInterface {
        @GET("rest/KorService/areaCode")
        Call<TourRepo> getTourRetrofit(@Query(value = "ServiceKey", encoded = true) String ServiceKey,
                                       @Query("MobileOS") String MobileOS,
                                       @Query("MobileApp") String MobileApp,
                                       @Query("_type") String _type);
    }


}
