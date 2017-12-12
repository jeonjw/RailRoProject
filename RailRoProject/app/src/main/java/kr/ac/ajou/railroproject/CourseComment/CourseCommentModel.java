package kr.ac.ajou.railroproject.CourseComment;

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

public class CourseCommentModel {

    private final static String DATABASE_REF_NAME = "CourseComments";

    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;
    private String dataRefKey;
    private int commentCount;
    private FirebaseRecyclerAdapter<CourseComment, CourseCommentViewHolder> adapter;
    private OnDataChangedListener onDataChangedListener;

    public void setOnDataChangedListener(OnDataChangedListener listener) {
        this.onDataChangedListener = listener;
    }

    public CourseCommentModel(final String dataRef) {

        this.dataRefKey = dataRef;
        databaseReference = FirebaseDatabase.getInstance().getReference();

        valueEventListener = databaseReference.child(DATABASE_REF_NAME).child(dataRefKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                commentCount = (int) dataSnapshot.getChildrenCount();
                databaseReference.child("Course").child(dataRefKey).child("commentCount").setValue(commentCount);

                if (onDataChangedListener != null) {
                    onDataChangedListener.onDataChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    public FirebaseRecyclerAdapter<CourseComment, CourseCommentViewHolder> loadCommentList() {

        Query query =databaseReference.child(DATABASE_REF_NAME).child(dataRefKey);

        FirebaseRecyclerOptions<CourseComment> options =
                new FirebaseRecyclerOptions.Builder<CourseComment>()
                        .setQuery(query, CourseComment.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<CourseComment, CourseCommentViewHolder>(options) {
            @Override
            protected void onBindViewHolder(CourseCommentViewHolder holder, int position, CourseComment model) {
                String commentKey = getRef(position).getKey();
                holder.bindComment(model, dataRefKey, commentKey);
            }

            @Override
            public CourseCommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_comment, parent, false);

                return new CourseCommentViewHolder(view);
            }

        };
        adapter.startListening();

        return adapter;
    }

    public void writeComment(String author, String message) {
        databaseReference.child(DATABASE_REF_NAME).child(dataRefKey).push().setValue(CourseComment.newComment(author, message));
    }


    public void removeListener() {
        databaseReference.child(DATABASE_REF_NAME).child(dataRefKey).removeEventListener(valueEventListener);
    }
}

