package kr.ac.ajou.railroproject;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DBTestModel {
    private FirebaseFirestore db;
    private String stationKey;

    public DBTestModel() {
        db = FirebaseFirestore.getInstance();
    }

    public void pushData() {
        List<Station> stationList = new ArrayList<>();

        Station station1 = new Station("수원역");
        Place place1 = new Place("역전시장", "오늘은 역전시장에서 도너츠를 사먹었어요 ㅎㅎ", "1919-03-01");
        Place place2 = new Place("아주대", "아대짱", "1919-03-01");
        station1.addPlace(place1);
        station1.addPlace(place2);
        stationList.add(station1);

        Station station2 = new Station("속초역");
        Place place3 = new Place("속초시장", "오늘은 속초시장에서 회를 사먹었어요 ㅎㅎ", "1919-03-02");
        station2.addPlace(place3);
        stationList.add(station2);

        db.collection("StationList").add(stationList).
                addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        stationKey = documentReference.getId();
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                }).
                addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
        ;

        Course course = new Course("여행가자", 123, 123, "전진우", stationKey);


        db.collection("Course").add(course);
    }
}
