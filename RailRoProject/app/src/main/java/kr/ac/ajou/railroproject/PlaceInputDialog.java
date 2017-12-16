package kr.ac.ajou.railroproject;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

import static android.app.Activity.RESULT_OK;

public class PlaceInputDialog extends DialogFragment {

    private OnEnrollmentListener onEnrollmentListener;
    private RecyclerView imageRecyclerView;
    private static List<String> selectedPhotos;
    private static PhotoAdapter photoAdapter;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_place_input_dialog, null);
        Button photoButton = view.findViewById(R.id.photo_button);
        imageRecyclerView = view.findViewById(R.id.place_input_photo_recycler_view);
        imageRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));

        final EditText placeNameEditText = view.findViewById(R.id.place_name_edit_text);
        final EditText placeDetailEditText = view.findViewById(R.id.place_detail_edit_text);

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
                String placeName = placeNameEditText.getText().toString();
                String placeDetail = placeDetailEditText.getText().toString();
                Place place = new Place(placeName,placeDetail, selectedPhotos);

                onEnrollmentListener.onEnroll(place);
                dismiss();
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
}
