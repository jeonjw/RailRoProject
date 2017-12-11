package kr.ac.ajou.railroproject;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class APIExample extends AsyncTask<String, Void, Void> {
    private String apiURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService";
    private String key = "l8FhPRcAWi8IadpttfL%2FHoQKxjdIzPQ1NImTqOQZnxBgE%2BtGwOPyX2%2FGKyoTVZc6ecH631rDmLJQO1xLAEbKcg%3D%3D";
    private String appName;
    private String URLInfo;

    public APIExample() {
        try {
            appName = URLEncoder.encode("내일로넷", "UTF-8");
            URLInfo = "ServiceKey=" + key + "&MobileOS=ETC&MobileApp=" + appName + "&_type=json";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Void doInBackground(String... strings) {
        try {
//            String apiURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode?ServiceKey="+key+"&MobileOS=ETC&MobileApp="+appName+"&_type=json";
            apiURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService";
            String URL = apiURL + "/areaCode?" + URLInfo + "&areaCode=4";
            URL url = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");


            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println("API " + response.toString());
        } catch (Exception e) {
            System.out.println("API " + e);
        }

        return null;
    }
}