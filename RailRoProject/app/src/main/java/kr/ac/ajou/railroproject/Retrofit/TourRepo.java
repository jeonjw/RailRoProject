package kr.ac.ajou.railroproject.Retrofit;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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


    public static class TourSpotRepo {

        @SerializedName("response")
        Response response;

        public Response getResponse() {
            return response;
        }

        public static class Response {

            @SerializedName("header")
            Response.Header header;
            @SerializedName("body")
            Response.Body body;

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
                        @SerializedName("addr1") String addr1;

                        @SerializedName("addr2") String addr2;

                        @SerializedName("areacode") int areacode;

                        @SerializedName("cat1") String cat1;

                        @SerializedName("cat2") String cat2;

                        @SerializedName("cat3") String cat3;

                        @SerializedName("contentid") int contentid;

                        @SerializedName("contenttypeid") int contenttypeid;

                        @SerializedName("createdtime") long createdtime;

                        @SerializedName("firstimage") String firstimage;

                        @SerializedName("firstimage2") String firstimage2;

                        @SerializedName("mapx") double mapx;

                        @SerializedName("mapy") double mapy;

                        @SerializedName("mlevel") int mlevel;

                        @SerializedName("modifiedtime") long modifiedtime;

                        @SerializedName("readcount") int readcount;

                        @SerializedName("sigungucode") int sigungucode;

                        @SerializedName("tel") String tel;

                        @SerializedName("title") String title;

                        @SerializedName("zipcode") String zipcode;

                    }

                }

            }
        }
    }


    public interface TourApiInterface {
        @GET("{request}")
        Call<TourRepo> getTourRetrofit(
                @Path("request") String request,
                @Query(value = "ServiceKey", encoded = true) String ServiceKey,
                @Query("areaCode") String areaCode,
                @Query("numOfRows") String numOfRows,
                @Query("contentTypeId") String contentTypeId,
                @Query("MobileOS") String MobileOS,
                @Query("MobileApp") String MobileApp,
                @Query("_type") String _type);

        @GET("{request}")
        Call<TourRepo.TourSpotRepo> getTourSpotRetrofit(
                @Path("request") String request,
                @Query(value = "ServiceKey", encoded = true) String ServiceKey,
                @Query("areaCode") String areaCode,
                @Query("sigungucode") String sigungucode,
                @Query("numOfRows") String numOfRows,
                @Query("contentTypeId") String contentTypeId,
                @Query("MobileOS") String MobileOS,
                @Query("MobileApp") String MobileApp,
                @Query("_type") String _type);
    }




}