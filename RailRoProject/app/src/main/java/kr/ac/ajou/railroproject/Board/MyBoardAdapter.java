package kr.ac.ajou.railroproject.Board;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.ac.ajou.railroproject.R;

public class MyBoardAdapter extends RecyclerView.Adapter<BoardViewHolder> {
    private List<Board> mediaNoticeList;

    public MyBoardAdapter() {
        mediaNoticeList = new ArrayList<>();
    }

    public void setDataList(List<Board> noticeList) {
        mediaNoticeList.clear();
        mediaNoticeList = noticeList;
        notifyDataSetChanged();
    }

    @Override
    public BoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.list_item_board, parent, false);

        return new BoardViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final BoardViewHolder holder, int position) {
        Board board = mediaNoticeList.get(position);
        holder.bindPost(board, "abcd", "aaaa");
    }

    @Override
    public int getItemCount() {
        return mediaNoticeList.size();
    }
}
