package kr.ac.ajou.railroproject.Board;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import kr.ac.ajou.railroproject.OnUploadImageListener;
import kr.ac.ajou.railroproject.PhotoAdapter;
import kr.ac.ajou.railroproject.R;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;


public class BoardWriteActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText contentsEditText;
    private Spinner boardSpinner;
    private int currentPosition;
    private boolean rewrite;
    private BoardModel boardModel;
    private ToggleButton anonymousToggleButton;
    private List<String> selectedPhotos;
    private List<String> deletePhotos;
    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);
        RecyclerView recyclerView = findViewById(R.id.board_image_recycler_view);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));

        Button writeButton = findViewById(R.id.board_write_finish);
        Button closeButton = findViewById(R.id.board_write_close_button);
        ImageButton photoButton = findViewById(R.id.board_photo_button);
        titleEditText = findViewById(R.id.board_write_title_edit_text);
        contentsEditText = findViewById(R.id.board_write_content_edit_text);
        boardSpinner = findViewById(R.id.board_spinner);
        anonymousToggleButton = findViewById(R.id.board_write_anonymous_toggle_button);

        String postKey = null;
        int commentCount = 0;

        if (getIntent() != null) {
            deletePhotos = new ArrayList<>();
            selectedPhotos = getIntent().getExtras().getStringArrayList("PHOTO_URL_LIST");

            if (selectedPhotos == null)
                selectedPhotos = new ArrayList<>();
            else
                deletePhotos.addAll(selectedPhotos);

            currentPosition = getIntent().getExtras().getInt("CURRENT_BOARD_TAB");
            String reWriteTitle = getIntent().getExtras().getString("BOARD_TITLE");
            String reWriteContents = getIntent().getExtras().getString("BOARD_CONTENTS");
            postKey = getIntent().getExtras().getString("CORRECT_POST_KEY");
            commentCount = getIntent().getExtras().getInt("COMMENT_COUNT");

            if (reWriteTitle != null && reWriteContents != null) {
                titleEditText.setText(reWriteTitle);
                contentsEditText.setText(reWriteContents);
                boardSpinner.setVisibility(View.GONE);
                rewrite = true;
            }
        }

        photoAdapter = new PhotoAdapter(selectedPhotos);
        recyclerView.setAdapter(photoAdapter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BoardWriteActivity.this, R.array.board_spinner, R.layout.spinner_post_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boardSpinner.setAdapter(adapter);

        boardSpinner.post(new Runnable() {
            @Override
            public void run() {
                boardSpinner.setSelection(currentPosition);
            }
        });

        boardModel = new BoardModel((String) boardSpinner.getSelectedItem());

        final String finalPostKey = postKey;
        final int finalCommentCount = commentCount;

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTextInputError())
                    return;

                if (!rewrite)
                    writePost();
                else
                    correctPost(finalPostKey, finalCommentCount);
            }
        });


        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setGridColumnCount(4)
                        .setPreviewEnabled(false)
                        .start(BoardWriteActivity.this, PhotoPicker.REQUEST_CODE);

            }
        });


    }

    private boolean isTextInputError() {
        if (TextUtils.isEmpty(titleEditText.getText().toString())) {
            titleEditText.requestFocus();
            titleEditText.setError("제목을 입력해주세요\n");
            return true;
        } else if (TextUtils.isEmpty(contentsEditText.getText().toString())) {
            contentsEditText.setError("내용을 입력해주세요\n");
            contentsEditText.requestFocus();
            return true;
        }

        return false;
    }

    private void writePost() {
        final String title = titleEditText.getText().toString();
        final String contents = contentsEditText.getText().toString();
        final String postType = getDatabaseKey(boardSpinner.getSelectedItemPosition());


        if (selectedPhotos.size() != 0) {
            Snackbar.make(contentsEditText, "이미지 업로드 중 . .", Snackbar.LENGTH_LONG).show();
            boardModel.uploadImages(getInputStreamFromUri(selectedPhotos), new OnUploadImageListener() {
                @Override
                public void onSuccess(List<String> urlList) {
                    boardModel.writeBoardWithImage(postType, getPostAuthorName(), title, contents, urlList);
                    finish();
                }
            });

        } else {
            boardModel.writeBoard(postType, getPostAuthorName(), title, contents);
            finish();
        }
    }

    private void correctPost(final String postKey, final int commentCount) {
        final String title = titleEditText.getText().toString();
        final String contents = contentsEditText.getText().toString();
        final String postType = getDatabaseKey(boardSpinner.getSelectedItemPosition());

        if (selectedPhotos.size() != 0) {

            System.out.println(selectedPhotos);
            System.out.println(deletePhotos);
            System.out.println(selectedPhotos.equals(deletePhotos));

            if (!selectedPhotos.equals(deletePhotos)) {

                boardModel.removePhotoFromStorage(deletePhotos);

                Snackbar.make(contentsEditText, "이미지 업로드 중 . .", Snackbar.LENGTH_LONG).show();
                boardModel.uploadImages(getInputStreamFromUri(selectedPhotos), new OnUploadImageListener() {

                    @Override
                    public void onSuccess(List<String> urlList) {
                        boardModel.correctBoardWithImages(postType, getPostAuthorName(), title, contents, postKey, commentCount, urlList);
                        finish();
                    }
                });
            } else {
                boardModel.correctBoardWithImages(postType, getPostAuthorName(), title, contents, postKey, commentCount, selectedPhotos);
                finish();
            }


        } else {

            if (deletePhotos.size() != 0)
                boardModel.removePhotoFromStorage(deletePhotos);

            boardModel.correctBoard(postType, getPostAuthorName(), title, contents, postKey, commentCount);
            finish();
        }

    }

    private String getPostAuthorName() {
        return anonymousToggleButton.isChecked() ? "익명" : FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
    }

    private String getDatabaseKey(int currentPosition) {
        String databaseKey = null;

        switch (currentPosition) {
            case 0:
                databaseKey = "Board_Tip";
                break;
            case 1:
                databaseKey = "Board_Accompanied";
                break;
            case 2:
                databaseKey = "Board_Recommendation";
                break;
        }

        return databaseKey;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK &&
                (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {

            List<String> photos = null;
            if (data != null)
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);


            selectedPhotos.clear();

            if (photos != null)
                selectedPhotos.addAll(photos);

            photoAdapter.notifyDataSetChanged();
        }
    }

    private ArrayList<InputStream> getInputStreamFromUri(List<String> photoInputStreamList) {
        ArrayList<InputStream> inputStreamList = new ArrayList<>();
        InputStream is;
        ContentResolver resolver;
        try {

            for (String str : photoInputStreamList) {
                resolver = getContentResolver();
                is = resolver.openInputStream(Uri.fromFile(new File(str)));
                inputStreamList.add(is);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStreamList;
    }


}

