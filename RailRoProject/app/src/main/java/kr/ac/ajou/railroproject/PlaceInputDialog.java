package kr.ac.ajou.railroproject;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import me.iwf.photopicker.PhotoPicker;

public class PlaceInputDialog extends DialogFragment {
    private OnEnrollmentListener onEnrollmentListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_place_input_dialog, null);
        Button photoButton = view.findViewById(R.id.photo_button);
        final EditText placeNameEditText = view.findViewById(R.id.place_name_edit_text);
        final EditText placeDetailEditText = view.findViewById(R.id.place_detail_edit_text);

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
                Place place = new Place(placeName,placeDetail);
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
}
