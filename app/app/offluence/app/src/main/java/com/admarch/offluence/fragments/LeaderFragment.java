package com.admarch.offluence.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.admarch.offluence.R;
import com.admarch.offluence.model.Earnings;
import com.admarch.offluence.model.LeaderBoard;
import com.admarch.offluence.rest.APIClient;
import com.admarch.offluence.utils.ListViewAdapter;
import com.admarch.offluence.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderFragment extends Fragment {
    private ArrayList<LeaderBoard> rankList;
    SessionManager sessionManager;

    public LeaderFragment() {
        super(R.layout.tab2_fragment);
    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        rankList = new ArrayList<>();
//        View rootView = inflater.inflate(R.layout.tab2_fragment,
//                container, false);
//
//        ListView lview = (ListView) rootView.findViewById(R.id.list_view);
//        ListViewAdapter adapter = new ListViewAdapter(getContext(), rankList);
////        setListAdapter(adapter);
//        lview.setAdapter(adapter);
//        populateList();
//
//        return rootView;
//    }
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        sessionManager = new SessionManager(view.getContext());
        String regNumber = sessionManager.getUserDetails().get(SessionManager.KEY_NAME);
        rankList = new ArrayList<>();
        ListView lview = (ListView) view.findViewById(R.id.list_view);

        ListViewAdapter adapter = new ListViewAdapter(getContext(), rankList);
        lview.setAdapter(adapter);

        fillEarningsData(adapter, regNumber, lview);
        adapter.notifyDataSetChanged();
//        lview.setOnTouchListener();
//        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////view.setBackgroundColor(Color.RED);
//
//                for(int i=0; i<parent.getChildCount(); i++)
//                {
//                    if(i == position)
//                    {
//                        parent.getChildAt(i).setBackgroundColor(Color.GREEN);
//                    }else{
//                        parent.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
//                    }
//                }}});


    }


    private void populateList() {

//        LeaderBoard item1, item2, item3, item4, item5;
//
//        item1 = new LeaderBoard("1", "Apple (Northern Spy)", "200");
//        rankList.add(item1);
//
//        item2 = new LeaderBoard("2", "Orange (Sunkist navel)", "100");
//        rankList.add(item2);
//
//        item3 = new LeaderBoard("3", "Tomato", "50");
//        rankList.add(item3);
//
//        item4 = new LeaderBoard("4", "Carrot", "80");
//        rankList.add(item4);
//
//        item5 = new LeaderBoard("5", "Banana (Cavendish)", "100");
//        rankList.add(item5);
//        fillEarningsData();
    }

    private void fillEarningsData(ListViewAdapter adapter, String regNumber, ListView listView) {
        try {
            Call<List<LeaderBoard>> call = APIClient.getInstance().getMyApi().getLeaderBoard();
            call.enqueue(new Callback<List<LeaderBoard>>() {
                @Override
                public void onResponse(Call<List<LeaderBoard>> call, Response<List<LeaderBoard>> response) {
                    int currItem = -1;
                    List<LeaderBoard> leaderBoard = response.body();
                    int rank = 1;
                    for(LeaderBoard leader : leaderBoard){
                        leader.setRank(String.valueOf(rank));
                        if(leader.getRegisterNumber().equalsIgnoreCase(regNumber)){
                            currItem = rank;
                        }
                        rank++;


                        rankList.add(leader);
                    }

                    adapter.notifyDataSetChanged();
                    if(currItem != -1){
                        adapter.setCurrItem(currItem-1);
                    }






                }

                @Override
                public void onFailure(Call<List<LeaderBoard>> call, Throwable t) {
                    call.cancel();
                }
            });

        } catch (Exception exception) {

        }
    }
}
