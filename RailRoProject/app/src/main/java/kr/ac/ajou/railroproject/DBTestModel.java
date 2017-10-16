package kr.ac.ajou.railroproject;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBTestModel {
    private DatabaseReference databaseReference;

    public DBTestModel() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void pushData(){
        List<Station> stationList = new ArrayList<>();

        stationList.add(new Station("수원역"));
        stationList.add(new Station("속초역"));
        stationList.add(new Station("망포역"));
        stationList.add(new Station("광교역"));
        stationList.add(new Station("울산역"));
        Course course = new Course("여행가자",123,123,stationList);

        databaseReference.child("Course").push().setValue(course);
    }
}
