package kr.ac.ajou.railroproject.Board;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import kr.ac.ajou.railroproject.OnUploadImageListener;
import kr.ac.ajou.railroproject.R;


public class BoardModel {
    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter<Board, BoardViewHolder> adapter;
    private List<String> urlList;

    public BoardModel(final String postType) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        urlList = new ArrayList<>();

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

    public void writeBoardWithImage(String postType, String userName, String title, String contents, List<String> urlList) {
        databaseReference.child(postType).push().setValue(Board.newBoardWithImages(userName, title, contents, 0, urlList));

    }


    public void uploadImages(final List<InputStream> selectedPhotos, final OnUploadImageListener listener) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReferenceFromUrl("gs://railroproject.appspot.com").child("postImages");

        for (InputStream inputStream : selectedPhotos) {
            UploadTask uploadTask = storageReference.child(generateTempFilename()).putStream(inputStream);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    urlList.add(taskSnapshot.getDownloadUrl().toString());

                    if (urlList.size() == selectedPhotos.size())
                        listener.onSuccess(urlList);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        }

    }

    public void correctBoard(String postType, String userName, String title, String contents, String postKey, int commentCount) {
        databaseReference.child(postType).
                child(postKey).setValue(Board.newBoard(userName, title, contents, commentCount));

    }

    public void correctBoardWithImages(String postType, String userName, String title, String contents, String postKey, int commentCount, List<String> urlList) {
        databaseReference.child(postType).
                child(postKey).setValue(Board.newBoardWithImages(userName, title, contents, commentCount, urlList));
    }

    private String generateTempFilename() {
        return UUID.randomUUID().toString();
    }

    public FirebaseRecyclerAdapter<Board, BoardViewHolder> getAdapter() {
        return adapter;
    }


    public void removePhotoFromStorage(List<String> urlList) {
        for (String imageUrl : urlList) {
            FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    System.out.println("삭제 실패  " + e.getMessage());
                }
            });
        }

    }
}
