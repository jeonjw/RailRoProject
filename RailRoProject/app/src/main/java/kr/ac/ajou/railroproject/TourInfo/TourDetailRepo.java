package kr.ac.ajou.railroproject.TourInfo;

import com.google.gson.annotations.SerializedName;

public class TourDetailRepo {

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
                public Item item;

                public Item getItem() {
                    return item;
                }

                public static class Item {
                    @SerializedName("chkbabycarriage")
                    String chkbabycarriage;

                    @SerializedName("agelimit")
                    String agelimit;

                    @SerializedName("discountinfofestival")
                    String discountinfofestival;

                    @SerializedName("eventstartdate")
                    int eventstartdate;

                    @SerializedName("eventenddate")
                    int eventenddate;

                    @SerializedName("eventplace")
                    String eventplace;

                    @SerializedName("placeinfo")
                    String placeinfo;

                    @SerializedName("spendtimefestival")
                    String spendtimefestival;

                    @SerializedName("bookingplace")
                    String bookingplace;

                    @SerializedName("playtime")
                    String playtime;

                    @SerializedName("program")
                    String program;

                    @SerializedName("sponsor1")
                    String sponsor1;

                    @SerializedName("sponsor1tel")
                    String sponsor1tel;

                    @SerializedName("subevent")
                    String subevent;

                    @SerializedName("usetimefestival")
                    String usetimefestival;

                    @SerializedName("chkcreditcard")
                    String chkcreditcard;

                    @SerializedName("chkpet")
                    String chkpet;

                    @SerializedName("expguide")
                    String expguide;

                    @SerializedName("heritage1")
                    int heritage1;

                    @SerializedName("heritage2")
                    int heritage2;

                    @SerializedName("heritage3")
                    int heritage3;

                    @SerializedName("contentid")
                    int contentid;

                    @SerializedName("contenttypeid")
                    int contenttypeid;

                    @SerializedName("infocenter")
                    String infocenter;

                    @SerializedName("parking")
                    String parking;

                    @SerializedName("usetime")
                    String usetime;

                    @SerializedName("restdate")
                    String restdate;

                    @SerializedName("chkcreditcardfood")
                    String chkcreditcardfood;

                    @SerializedName("discountinfofood")
                    String discountinfofood;

                    @SerializedName("firstmenu")
                    String firstmenu;

                    @SerializedName("kidsfacility")
                    int kidsfacility;

                    @SerializedName("opendatefood")
                    String opendatefood;

                    @SerializedName("packing")
                    String packing;

                    @SerializedName("infocenterfood")
                    String infocenterfood;

                    @SerializedName("opentimefood")
                    String opentimefood;

                    @SerializedName("parkingfood")
                    String parkingfood;

                    @SerializedName("reservationfood")
                    String reservationfood;

                    @SerializedName("restdatefood")
                    String restdatefood;

                    @SerializedName("smoking")
                    String smoking;

                    @SerializedName("treatmenu")
                    String treatmenu;

                    @SerializedName("barbecue")
                    int barbecue;

                    @SerializedName("beauty")
                    int beauty;

                    @SerializedName("benikia")
                    int benikia;

                    @SerializedName("beverage")
                    int beverage;

                    @SerializedName("bicycle")
                    int bicycle;

                    @SerializedName("campfire")
                    int campfire;

                    @SerializedName("checkintime")
                    String checkintime;

                    @SerializedName("checkouttime")
                    String checkouttime;

                    @SerializedName("chkcooking")
                    String chkcooking;

                    @SerializedName("fitness")
                    int fitness;

                    @SerializedName("foodplace")
                    String foodplace;

                    @SerializedName("goodstay")
                    int goodstay;

                    @SerializedName("hanok")
                    int hanok;

                    @SerializedName("infocenterlodging")
                    String infocenterlodging;

                    @SerializedName("karaoke")
                    int karaoke;

                    @SerializedName("parkinglodging")
                    String parkinglodging;

                    @SerializedName("publicbath")
                    int publicbath;

                    @SerializedName("publicpc")
                    int publicpc;

                    @SerializedName("reservationlodging")
                    String reservationlodging;

                    @SerializedName("reservationurl")
                    String reservationurl;

                    @SerializedName("roomcount")
                    String roomcount;

                    @SerializedName("roomtype")
                    String roomtype;

                    @SerializedName("sauna")
                    int sauna;

                    @SerializedName("seminar")
                    int seminar;

                    @SerializedName("sports")
                    int sports;

                    @SerializedName("subfacility")
                    String subfacility;

                    public String getSmoking() {
                        return smoking;
                    }

                    public String getTreatmenu() {
                        return treatmenu;
                    }

                    public int getContentid() {
                        return contentid;
                    }

                    public int getContenttypeid() {
                        return contenttypeid;
                    }

                    public String getChkbabycarriage() {
                        return chkbabycarriage;
                    }

                    public String getChkcreditcard() {
                        return chkcreditcard;
                    }

                    public String getChkpet() {
                        return chkpet;
                    }

                    public String getExpguide() {
                        return expguide;
                    }

                    public int getHeritage1() {
                        return heritage1;
                    }

                    public int getHeritage2() {
                        return heritage2;
                    }

                    public int getHeritage3() {
                        return heritage3;
                    }

                    public String getInfocenter() {
                        return infocenter;
                    }

                    public String getParking() {
                        return parking;
                    }

                    public String getUsetime() {
                        return usetime;
                    }

                    public String getRestdate() {
                        return restdate;
                    }

                    public String getAgelimit() {
                        return agelimit;
                    }

                    public String getDiscountinfofestival() {
                        return discountinfofestival;
                    }

                    public int getEventstartdate() {
                        return eventstartdate;
                    }

                    public int getEventenddate() {
                        return eventenddate;
                    }

                    public String getEventplace() {
                        return eventplace;
                    }

                    public String getPlaceinfo() {
                        return placeinfo;
                    }

                    public String getSpendtimefestival() {
                        return spendtimefestival;
                    }

                    public String getBookingplace() {
                        return bookingplace;
                    }

                    public String getInfocenterfood() {
                        return infocenterfood;
                    }

                    public String getPlaytime() {
                        return playtime;
                    }

                    public String getProgram() {
                        return program;
                    }

                    public String getSponsor1() {
                        return sponsor1;
                    }

                    public String getSponsor1tel() {
                        return sponsor1tel;
                    }

                    public String getSubevent() {
                        return subevent;
                    }

                    public String getUsetimefestival() {
                        return usetimefestival;
                    }

                    public String getChkcreditcardfood() {
                        return chkcreditcardfood;
                    }

                    public String getDiscountinfofood() {
                        return discountinfofood;
                    }

                    public String getFirstmenu() {
                        return firstmenu;
                    }

                    public int getKidsfacility() {
                        return kidsfacility;
                    }

                    public String getOpendatefood() {
                        return opendatefood;
                    }

                    public String getPacking() {
                        return packing;
                    }

                    public String getOpentimefood() {
                        return opentimefood;
                    }

                    public String getParkingfood() {
                        return parkingfood;
                    }

                    public String getReservationfood() {
                        return reservationfood;
                    }

                    public String getRestdatefood() {
                        return restdatefood;
                    }

                    public int getBarbecue() {
                        return barbecue;
                    }

                    public int getBeauty() {
                        return beauty;
                    }

                    public int getBenikia() {
                        return benikia;
                    }

                    public int getBeverage() {
                        return beverage;
                    }

                    public int getBicycle() {
                        return bicycle;
                    }

                    public int getCampfire() {
                        return campfire;
                    }

                    public String getCheckintime() {
                        return checkintime;
                    }

                    public String getCheckouttime() {
                        return checkouttime;
                    }

                    public String getChkcooking() {
                        return chkcooking;
                    }

                    public int getFitness() {
                        return fitness;
                    }

                    public String getFoodplace() {
                        return foodplace;
                    }

                    public int getGoodstay() {
                        return goodstay;
                    }

                    public int getHanok() {
                        return hanok;
                    }

                    public String getInfocenterlodging() {
                        return infocenterlodging;
                    }

                    public int getKaraoke() {
                        return karaoke;
                    }

                    public String getParkinglodging() {
                        return parkinglodging;
                    }

                    public int getPublicbath() {
                        return publicbath;
                    }

                    public int getPublicpc() {
                        return publicpc;
                    }

                    public String getReservationlodging() {
                        return reservationlodging;
                    }

                    public String getReservationurl() {
                        return reservationurl;
                    }

                    public String getRoomcount() {
                        return roomcount;
                    }

                    public String getRoomtype() {
                        return roomtype;
                    }

                    public int getSauna() {
                        return sauna;
                    }

                    public int getSeminar() {
                        return seminar;
                    }

                    public int getSports() {
                        return sports;
                    }

                    public String getSubfacility() {
                        return subfacility;
                    }
                }

            }

        }
    }
}
