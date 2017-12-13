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
    private DatabaseReference databaseReference;

    public DBTestModel() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void pushData() {

        Course course = new Course("여행가자",123,23,"전진우UID");
        Station station1 = new Station("수원역", "2017-09-11");
        Place place1 = new Place("역전시장", "도너츠 사먹음 ㅎ",null);
        Place place2 = new Place("아주대", "아주대 짱",null);
        Station station2 = new Station("속초역", "2017-09-12");
        Place place3 = new Place("속초횟집", "회 사먹음 ㅎ",null);
        station1.addPlace(place1);
        station1.addPlace(place2);
        station2.addPlace(place3);
        course.addStation(station1);
        course.addStation(station2);


        databaseReference.child("Course").push().setValue(course);

    }
}
