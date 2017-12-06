package kr.ac.ajou.railroproject.Board;


import com.google.firebase.database.DatabaseReference;

public class BoardAccompaniedFragment extends BaseBoardFragment {

    private static final String BOARD_REFERENCE_NAME = "Board_Accompanied";

    @Override
    public String getPostType() {
        return BOARD_REFERENCE_NAME;
    }
}
