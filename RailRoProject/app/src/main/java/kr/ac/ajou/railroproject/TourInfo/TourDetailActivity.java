package kr.ac.ajou.railroproject.TourInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kr.ac.ajou.railroproject.GlideApp;
import kr.ac.ajou.railroproject.MapActivity;
import kr.ac.ajou.railroproject.OnApiReadListener;
import kr.ac.ajou.railroproject.R;
import kr.ac.ajou.railroproject.Retrofit.TourApiReader;

public class TourDetailActivity extends AppCompatActivity {

    private String contentId;
    private String contentTypeId;
    private TourApiReader apiReader;
    private String address;
    private TourDetailRepo.Response.Body.Items.Item item;
    private TextView infoTextView;

    private double mapX;
    private double mapY;

    private String title;
    private static final int DETAIL_TOUR_INFO_CODE = 12;
    private static final int DETAIL_FESTIVAL_INFO_CODE = 15;
    private static final int DETAIL_ACCOMMODATION_INFO_CODE = 32;
    private static final int DETAIL_FOOD_INFO_CODE = 39;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail_tour_info);

        ImageView firstImageView = findViewById(R.id.detail_info_first_image_view);
        infoTextView = findViewById(R.id.info_text_view);
        infoTextView.setMovementMethod(new ScrollingMovementMethod());

        if (getIntent() != null) {
            contentId = getIntent().getExtras().getString("CONTENT_ID");
            contentTypeId = getIntent().getExtras().getString("CONTENT_TYPE_ID");
            address = getIntent().getExtras().getString("ADDRESS");
            mapX = getIntent().getExtras().getDouble("MAP_X");
            mapY = getIntent().getExtras().getDouble("MAP_Y");
            title = getIntent().getExtras().getString("TITLE");

            GlideApp.with(firstImageView)
                    .load(getIntent().getExtras().getString("FIRST_IMAGE_URL"))
                    .placeholder(R.drawable.image_not_found)
                    .into(firstImageView);

        }


        apiReader = new TourApiReader(new OnApiReadListener() {
            @Override
            public void onRead(int requestType) {

                item = apiReader.getDetailItem();
                if (requestType == DETAIL_TOUR_INFO_CODE) {
                    createTourInfoText();
                } else if (requestType == DETAIL_FESTIVAL_INFO_CODE) {
                    createFestivalInfoText();
                } else if (requestType == DETAIL_FOOD_INFO_CODE) {
                    createFoodInfoText();
                } else if (requestType == DETAIL_ACCOMMODATION_INFO_CODE)
                    createAccommodationInfoText();
            }
        });

        findViewById(R.id.show_map_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TourDetailActivity.this, MapActivity.class);
                intent.putExtra("MAP_X", mapX);
                intent.putExtra("MAP_Y", mapY);
                intent.putExtra("TITLE", title);
                startActivity(intent);
            }
        });


        apiReader.readTourDetailInfo(contentId, contentTypeId);

    }

    private void createTourInfoText() {
        String info = "문의 및 안내 : " + Html.fromHtml(item.getInfocenter()).toString();
        String useTime = item.getUsetime() == null ? "" : "이용 시간 : " + Html.fromHtml(item.getUsetime()).toString();
        String restDate = item.getRestdate() == null ? "" : "쉬는 날 : " + item.getRestdate();
        String addressInfo = address == null ? "" : "주소 : " + address + "\n";

        String checkCreditCard = item.getChkcreditcard() == null ? "" : "신용 카드 : " + item.getChkcreditcard();
        String checkBabyCarriage = item.getChkbabycarriage() == null ? "" : "유모차 시설 : " + item.getChkbabycarriage();
        String parking = item.getParking() == null ? "" : "주차 시설 : " + item.getParking();

        info = info + "\n" + useTime + "\n" + restDate + "\n" + addressInfo
                + "\n" + checkCreditCard + "\n" + checkBabyCarriage + "\n" + parking;
        infoTextView.setText(info);
    }

    private void createFestivalInfoText() {
        String program = item.getProgram().length() == 0 ? "" : "행사 프로그램 : " + Html.fromHtml(item.getProgram()).toString();
        String ageLimit = item.getAgelimit() == null ? "" : "관람 연령 : " + item.getAgelimit();
        String playTime = item.getPlaytime() == null ? "" : "공연 시간 : " + Html.fromHtml(item.getPlaytime()).toString();
        String festivalCost = item.getUsetimefestival().length() == 0 ? "" : "공연 요금 : " + item.getUsetimefestival();

        String eventStartDate = String.valueOf(item.getEventstartdate()) == null ? "" : "행사 시작 : " + item.getEventstartdate();
        String eventEndDate = String.valueOf(item.getEventenddate()) == null ? "" : "행사 종료 : " + item.getEventenddate();
        String eventPlace = item.getEventplace() == null ? "" : "행사장 장소 : " + item.getEventplace();
        String eventPlaceInfo = item.getPlaceinfo().length() == 0 ? "" : "행사장 위치안내 : " + Html.fromHtml(item.getPlaceinfo()).toString() + "\n";

        String subEvent = item.getSubevent().length() == 0 ? "" : "부대 행사 : " + Html.fromHtml(item.getSubevent()).toString() + "\n";
        String sponsor = item.getSponsor1() == null ? "" : "주최 : " + item.getSponsor1();
        String sponsorTel = item.getSponsor1tel() == null ? "" : "주최 전화번호 : " + item.getSponsor1tel();


        program = program + "\n" + ageLimit + "\n" + playTime + "\n" + festivalCost + "\n" + eventStartDate + "\n" + eventEndDate + "\n"
                + eventPlace + "\n" + eventPlaceInfo + "\n" + subEvent + "\n" + sponsor + "\n" + sponsorTel;

        infoTextView.setText(program);
    }

    private void createFoodInfoText() {
        String firstMenu = item.getFirstmenu() == null ? "" : "대표 메뉴 : " + Html.fromHtml(item.getFirstmenu()).toString();
        String openTimeFood = item.getOpentimefood() == null ? "" : "영업 시간 : " + Html.fromHtml(item.getOpentimefood()).toString();
        String infoCenterFood = item.getInfocenterfood() == null ? "" : "문의 안내 : " + item.getInfocenterfood() + "\n";

        String kids = item.getKidsfacility() == 0 ? "어린이 시설 : 없음" : "어린이 시설 : 있음";
        String smoking = item.getSmoking() == null ? "" : "흡연 시설 : " + item.getSmoking();
        String packing = item.getPacking() == null ? "" : "포장 가능 : " + item.getPacking();
        String parkingFood = item.getParkingfood() == null ? "" : "주차 시설 : " + item.getParkingfood() + "\n";

        String restDate = item.getRestdatefood() == null ? "" : "쉬는날 : " + item.getRestdatefood();
        String treatMenu = item.getTreatmenu() == null ? "" : "취급 메뉴 : " + item.getTreatmenu();

        firstMenu = firstMenu + "\n" + openTimeFood + "\n" + infoCenterFood + "\n" + kids + "\n" + smoking + "\n" + packing + "\n" + parkingFood + "\n"
                + restDate + "\n" + treatMenu;

        infoTextView.setText(firstMenu);
    }

    private void createAccommodationInfoText() {
        String roomCount = item.getRoomcount() == null ? "" : "객실수 : " + item.getRoomcount();
        String roomType = item.getRoomtype() == null ? "" : "객실 유형 : " + Html.fromHtml(item.getRoomtype()).toString();
        String foodPlace = item.getFoodplace().length() == 0 ? "" : "식음료장  : " + Html.fromHtml(item.getFoodplace()).toString();
        String subFacility = item.getSubfacility().length() == 0 ? "" : "부대 시설 : " + Html.fromHtml(item.getSubfacility()).toString() + "\n";

        String checkInTime = item.getCheckintime() == null ? "" : "입실 시간 : " + item.getCheckintime();
        String checkOutTime = item.getCheckouttime() == null ? "" : "퇴실 시간 : " + item.getCheckouttime();
        String infocenterlodging = item.getInfocenterlodging() == null ? "" : "문의 안내 : " + item.getInfocenterlodging();
        String parkingLoading = item.getParkinglodging() == null ? "" : "주차 안내 : " + item.getParkinglodging() + "\n";

        String barbecue = item.getBarbecue() == 0 ? "바비큐장 여부 : 없음" : "바비큐장 여부 : 있음";
        String beauty = item.getBeauty() == 0 ? "뷰티시설 : 없음" : "뷰티시설 : 있음";
        String benikia = item.getBenikia() == 0 ? "베니키아 여부 : 없음" : "베니키아 여부 : 있음";
        String beverage = item.getBeverage() == 0 ? "식음료장 여부" : "식음료장 여부 : 있음";
        String bicycle = item.getBicycle() == 0 ? "자전거 대여 여부 : 없음" : "자전거 대여 여부 : 있음";
        String campfire = item.getCampfire() == 0 ? "캠프파이어 여부 : 없음" : "캠프파이어 여부 : 있음";
        String karaoke = item.getKaraoke() == 0 ? "노래방 여부 : 없음" : "노래방 여부 : 있음";
        String fitness = item.getFitness() == 0 ? "운동 시설 여부 : 없음" : "운동 시설 여부 : 있음";
        String goodstay = item.getGoodstay() == 0 ? "굿스테이 여부 : 없음" : "굿스테이 여부 : 있음";
        String hanok = item.getHanok() == 0 ? "한옥 여부 : 없음" : "한옥 여부 : 있음";
        String publicbath = item.getPublicbath() == 0 ? "공용 샤워실 여부 : 없음" : "공용 샤워실 여부 : 있음";
        String publicpc = item.getPublicpc() == 0 ? "공용 PC실 여부 : 없음" : "공용 PC실 여부 : 있음";
        String sauna = item.getSauna() == 0 ? "사우나실 여부 : 없음" : "사우나실 여부 : 있음";
        String seminar = item.getSeminar() == 0 ? "회의실 여부 : 없음" : "회의실 여부 : 있음";
        String sports = item.getSports() == 0 ? "스포츠 시설 여부 : 없음" : "스포츠 시설 여부 : 있음";

        roomCount = roomCount + "\n" + roomType + "\n" + foodPlace + "\n" + subFacility + "\n"
                + checkInTime + "\n" + checkOutTime + "\n" + infocenterlodging + "\n" + parkingLoading
                + "\n" + barbecue + "\n" + beauty + "\n" + benikia + "\n" + goodstay + "\n" + hanok + "\n"
                + beverage + "\n" + bicycle + "\n" + campfire + "\n" + karaoke + "\n" + fitness + "\n"
                + publicbath + "\n" + publicpc + "\n" + sauna + "\n" + seminar + "\n" + sports;

        infoTextView.setText(roomCount);
    }


}
