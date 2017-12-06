package kr.ac.ajou.railroproject.Board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import kr.ac.ajou.railroproject.R;


public class BoardModel {
    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter<Board, BoardViewHolder> adapter;

    public BoardModel(final String postType) {
        System.out.println("TEST POSTTYPE" + postType);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Query query = databaseReference
                .child(postType);

        FirebaseRecyclerOptions<Board> options =
                new FirebaseRecyclerOptions.Builder<Board>()
                        .setQuery(query, Board.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<Board, BoardViewHolder>(options) {
            @Override
            protected void onBindViewHolder(BoardViewHolder holder, int position, Board model) {
                DatabaseReference postRef = getRef(position);
                String postKey = postRef.getKey();
                holder.bindPost(model, postKey, postType);
            }

            @Override
            public BoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_board, parent, false);

                return new BoardViewHolder(view);
            }

        };
        adapter.startListening();
    }

    public void writeBoard(String postType, String userName, String title, String contents) {
        databaseReference.child(postType).
                push().setValue(Board.newBoard(userName, title, contents, 0));

    }

    public FirebaseRecyclerAdapter<Board, BoardViewHolder> getAdapter() {
        return adapter;
    }
}
