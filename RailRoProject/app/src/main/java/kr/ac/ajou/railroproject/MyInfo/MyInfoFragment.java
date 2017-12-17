package kr.ac.ajou.railroproject.MyInfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.ac.ajou.railroproject.Board.BoardAccompaniedFragment;
import kr.ac.ajou.railroproject.Board.BoardRecommendationFragment;
import kr.ac.ajou.railroproject.Board.BoardTipFragment;
import kr.ac.ajou.railroproject.GlideApp;
import kr.ac.ajou.railroproject.R;


public class MyInfoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_info, container, false);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        GlideApp.with(getContext())
                .load(user.getPhotoUrl())
                .placeholder(R.drawable.image_not_found)
                .into((CircleImageView) view.findViewById(R.id.my_info_profile_image_view));

        EditText nameEditText = view.findViewById(R.id.my_info_name_edit_text);
        nameEditText.setText(user.getDisplayName());


        return view;
    }
}
