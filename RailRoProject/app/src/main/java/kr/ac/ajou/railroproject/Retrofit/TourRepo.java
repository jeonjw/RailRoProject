package kr.ac.ajou.railroproject.Retrofit;


import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import kr.ac.ajou.railroproject.TourInfo.TourDetailRepo;
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
                    public List<Item> itemList;

                    public List<Item> getItemList() {
                        return itemList;
                    }

                    public static class Item {
                        @SerializedName("addr1")
                        String addr1;

                        @SerializedName("addr2")
                        String addr2;

                        @SerializedName("areacode")
                        int areacode;

                        @SerializedName("cat1")
                        String cat1;

                        @SerializedName("cat2")
                        String cat2;

                        @SerializedName("cat3")
                        String cat3;

                        @SerializedName("contentid")
                        int contentid;

                        @SerializedName("contenttypeid")
                        int contenttypeid;

                        @SerializedName("createdtime")
                        long createdtime;

                        @SerializedName("eventstarttime")
                        int eventstarttime;

                        @SerializedName("eventendtime")
                        int eventendtime;

                        @SerializedName("firstimage")
                        String firstimage;

                        @SerializedName("firstimage2")
                        String firstimage2;

                        @SerializedName("mapx")
                        double mapx;

                        @SerializedName("mapy")
                        double mapy;

                        @SerializedName("mlevel")
                        int mlevel;

                        @SerializedName("modifiedtime")
                        long modifiedtime;

                        @SerializedName("readcount")
                        int readcount;

                        @SerializedName("sigungucode")
                        int sigungucode;

                        @SerializedName("tel")
                        String tel;

                        @SerializedName("title")
                        String title;

                        @SerializedName("zipcode")
                        String zipcode;

                        public String getAddr1() {
                            return addr1;
                        }

                        public String getAddr2() {
                            return addr2;
                        }

                        public int getAreacode() {
                            return areacode;
                        }

                        public String getCat1() {
                            return cat1;
                        }

                        public String getCat2() {
                            return cat2;
                        }

                        public String getCat3() {
                            return cat3;
                        }

                        public int getContentid() {
                            return contentid;
                        }

                        public int getContenttypeid() {
                            return contenttypeid;
                        }

                        public long getCreatedtime() {
                            return createdtime;
                        }

                        public String getFirstimage() {
                            return firstimage;
                        }

                        public String getFirstimage2() {
                            return firstimage2;
                        }

                        public double getMapx() {
                            return mapx;
                        }

                        public double getMapy() {
                            return mapy;
                        }

                        public int getMlevel() {
                            return mlevel;
                        }

                        public long getModifiedtime() {
                            return modifiedtime;
                        }

                        public int getReadcount() {
                            return readcount;
                        }

                        public int getSigungucode() {
                            return sigungucode;
                        }

                        public String getTel() {
                            return tel;
                        }

                        public String getTitle() {
                            return title;
                        }

                        public String getZipcode() {
                            return zipcode;
                        }

                        public int getEventstarttime() {
                            return eventstarttime;
                        }

                        public int getEventendtime() {
                            return eventendtime;
                        }
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
                @Query("arrange") String arrange,
                @Query("areaCode") String areaCode,
                @Query("sigunguCode") String sigunguCode,
                @Query("mapX") String mapX,
                @Query("mapY") String mapY,
                @Query("radius") String radius,
                @Query("numOfRows") String numOfRows,
                @Query("contentTypeId") String contentTypeId,
                @Query(value = "keyword", encoded = true) String keyword,
                @Query("MobileOS") String MobileOS,
                @Query("MobileApp") String MobileApp,
                @Query("_type") String _type);

        @GET("{request}")
        Call<TourDetailRepo> getTourDetailInfo(
                @Path("request") String request,
                @Query(value = "ServiceKey", encoded = true) String ServiceKey,
                @Query("numOfRows") String numOfRows,
                @Query("contentId") String contentId,
                @Query("contentTypeId") String contentTypeId,
                @Query("MobileOS") String MobileOS,
                @Query("MobileApp") String MobileApp,
                @Query("_type") String _type);
    }


}
