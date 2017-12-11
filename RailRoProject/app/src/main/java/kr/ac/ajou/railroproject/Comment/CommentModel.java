package kr.ac.ajou.railroproject.Comment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import kr.ac.ajou.railroproject.OnDataChangedListener;
import kr.ac.ajou.railroproject.R;

public class CommentModel {
    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;
    private String dataRefKey;
    private int commentCount;
    private FirebaseRecyclerAdapter<Comment, CommentViewHolder> adapter;
    private OnDataChangedListener onDataChangedListener;

    public void setOnDataChangedListener(OnDataChangedListener listener) {
        this.onDataChangedListener = listener;
    }

    public CommentModel(final String dataRef, final String postType) {

        this.dataRefKey = dataRef;
        databaseReference = FirebaseDatabase.getInstance().getReference();

        valueEventListener = databaseReference.child("Comments").child(dataRefKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                commentCount = (int) dataSnapshot.getChildrenCount();
                databaseReference.child(postType).child(dataRefKey).child("commentCount").setValue(commentCount);

                if (onDataChangedListener != null) {
                    onDataChangedListener.onDataChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    public FirebaseRecyclerAdapter<Comment, CommentViewHolder> loadCommentList() {

        Query query =databaseReference.child("Comments").child(dataRefKey);
        FirebaseRecyclerOptions<Comment> options =
                new FirebaseRecyclerOptions.Builder<Comment>()
                        .setQuery(query, Comment.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<Comment, CommentViewHolder>(options) {
            @Override
            protected void onBindViewHolder(CommentViewHolder holder, int position, Comment model) {
                String commentKey = getRef(position).getKey();
                holder.bindComment(model, dataRefKey, commentKey);
            }

            @Override
            public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_comment, parent, false);

                return new CommentViewHolder(view);
            }

        };
        adapter.startListening();

        return adapter;
    }

    public void writeComment(String author, String message) {
        databaseReference.child("Comments").child(dataRefKey).push().setValue(Comment.newComment(author, message));
    }


    public void removeListener() {
        databaseReference.child("Comments").child(dataRefKey).removeEventListener(valueEventListener);
    }
}

