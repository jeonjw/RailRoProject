package kr.ac.ajou.railroproject.MyInfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kr.ac.ajou.railroproject.Board.BaseBoardFragment;
import kr.ac.ajou.railroproject.Board.Board;
import kr.ac.ajou.railroproject.Board.MyBoardAdapter;

public class MyBoardFragment extends BaseBoardFragment {

    private List<Board> dataList = new ArrayList<>();
    private MyBoardAdapter myBoardAdapter;

    @Override
    public String getPostType() {
        return "Board_Tip";
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createList();

        myBoardAdapter = new MyBoardAdapter();
        myBoardAdapter.setDataList(dataList);
        getRecyclerView().setAdapter(myBoardAdapter);
    }

    public void createList() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Board_Accompanied").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Board board = ds.getValue(Board.class);
                    if (Objects.equals(board.getAuthorUid(), FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        dataList.add(board);
                        myBoardAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        databaseReference.child("Board_Tip").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Board board = ds.getValue(Board.class);
                    if (Objects.equals(board.getAuthorUid(), FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        dataList.add(board);
                        myBoardAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        databaseReference.child("Board_Recommendation").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Board board = ds.getValue(Board.class);
                    if (Objects.equals(board.getAuthorUid(), FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        dataList.add(board);
                        myBoardAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


    }
}
