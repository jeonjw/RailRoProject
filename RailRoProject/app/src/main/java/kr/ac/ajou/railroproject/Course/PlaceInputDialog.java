package kr.ac.ajou.railroproject.Course;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import kr.ac.ajou.railroproject.OnEnrollmentListener;
import kr.ac.ajou.railroproject.OnUploadImageListener;
import kr.ac.ajou.railroproject.PhotoAdapter;
import kr.ac.ajou.railroproject.R;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

import static android.app.Activity.RESULT_OK;

public class PlaceInputDialog extends DialogFragment {

    private OnEnrollmentListener onEnrollmentListener;
    private RecyclerView imageRecyclerView;
    private static List<String> selectedPhotos;
    private static PhotoAdapter photoAdapter;
    private static boolean created = false;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_place_input_dialog, null);
        Button photoButton = view.findViewById(R.id.photo_button);
        imageRecyclerView = view.findViewById(R.id.place_input_photo_recycler_view);
        imageRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));

        final EditText placeNameEditText = view.findViewById(R.id.place_name_edit_text);
        final EditText placeDetailEditText = view.findViewById(R.id.place_detail_edit_text);

        created = true;
        selectedPhotos = new ArrayList<String>();

        photoAdapter = new PhotoAdapter(selectedPhotos);
        imageRecyclerView.setAdapter(photoAdapter);

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setGridColumnCount(4)
                        .setPreviewEnabled(false)
                        .start(getActivity(), PhotoPicker.REQUEST_CODE);
            }
        });

        Button enrollButton = view.findViewById(R.id.enroll_button);
        enrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String placeName = placeNameEditText.getText().toString();
                final String placeDetail = placeDetailEditText.getText().toString();


                if (selectedPhotos.size() != 0) {
                    Toast.makeText(getContext(), "이미지 업로드 중...", Toast.LENGTH_SHORT).show();
                    uploadImages(getInputStreamFromUri(selectedPhotos), new OnUploadImageListener() {
                        @Override
                        public void onSuccess(List<String> urlList) {
                            Place place = new Place(placeName, placeDetail, urlList);
                            onEnrollmentListener.onEnroll(place);
                            dismiss();
                        }
                    });
                } else {
                    Place place = new Place(placeName, placeDetail);
                    onEnrollmentListener.onEnroll(place);
                    dismiss();
                }


            }
        });

        Dialog dialog = new AlertDialog.Builder(getActivity()).setView(view).create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        return dialog;
    }


    public static PlaceInputDialog newInstance() {
        PlaceInputDialog placeInputDialog = new PlaceInputDialog();

        return placeInputDialog;
    }

    public void setOnEnrollListener(OnEnrollmentListener onEnrollmentListener) {
        this.onEnrollmentListener = onEnrollmentListener;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    private ArrayList<InputStream> getInputStreamFromUri(List<String> photoInputStreamList) {
        ArrayList<InputStream> inputStreamList = new ArrayList<>();
        InputStream is;
        ContentResolver resolver;
        try {

            for (String str : photoInputStreamList) {
                resolver = getActivity().getContentResolver();
                is = resolver.openInputStream(Uri.fromFile(new File(str)));
                inputStreamList.add(is);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStreamList;
    }

    public static void changeData(int requestCode, int resultCode, Intent data) {
        if (!created)
            return;

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


    public void uploadImages(final List<InputStream> selectedPhotos, final OnUploadImageListener listener) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReferenceFromUrl("gs://railroproject.appspot.com").child("postImages");
        final List<String> urlList = new ArrayList<>();

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

    private String generateTempFilename() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        created = false;
    }
}
