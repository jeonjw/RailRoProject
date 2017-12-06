package kr.ac.ajou.railroproject.Board;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BoardTipFragment extends BaseBoardFragment {

    private static final String BOARD_REFERENCE_NAME = "Board_Tip";


    @Override
    public String getPostType() {
        return BOARD_REFERENCE_NAME;
    }
}
