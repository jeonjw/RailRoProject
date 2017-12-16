package kr.ac.ajou.railroproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class StationInputAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    private int stationCount;
    private List<Station> stationList = new ArrayList<Station>();
    private List<StationInputViewHolder> holderList = new ArrayList<>();

    public StationInputAdapter(List<Station> stationList){
        this.stationList = stationList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;

        if(viewType == TYPE_ITEM){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_station_add, parent, false);
            StationInputViewHolder viewHolder =  new StationInputViewHolder(itemView);
            holderList.add(viewHolder);
            return viewHolder;
        }else if (viewType == TYPE_FOOTER){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_station_add_footer, parent, false);

            return new StationInputFooterViewHolder(itemView);
        }

        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StationInputViewHolder) {
            //뷰 타입이 아이템일 경우
            //StationInputViewHolder item_holder = (StationInputViewHolder) holder;
        } else if (holder instanceof StationInputFooterViewHolder) {
            //뷰타입이 푸터 생성하기 버튼일 경우

        }
    }

    @Override
    public int getItemCount() {
        return stationList.size()+1;
    }

    public void addInputView(){
        stationCount++;
    }

    //뷰타입 정하기
    @Override
    public int getItemViewType(int position) {
        if (isPositionFooter(position)) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    public List<Station> getStationList(){
        return stationList;
    }

    public ArrayList<Station> finish() {
        ArrayList<Station> tempStationList = new ArrayList<>();
        for(StationInputViewHolder holder : holderList){
            tempStationList.add(holder.getStation());
        }

        return tempStationList;
    }

    //true가 반환되면 리스트의 끝임을 알수있다.
    private boolean isPositionFooter(int position) {
        return position == stationList.size();
    }
}
