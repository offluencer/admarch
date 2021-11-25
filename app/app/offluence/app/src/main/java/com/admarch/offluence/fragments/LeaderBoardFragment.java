package com.admarch.offluence.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.admarch.offluence.R;
import com.admarch.offluence.model.LeaderBoard;
import com.admarch.offluence.utils.ListViewAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {
    private ArrayList<LeaderBoard> rankList;

//    public LeaderFragment() {
//        super(R.layout.tab2_fragment);
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rankList = new ArrayList<>();
        View rootView = inflater.inflate(R.layout.tab2_fragment,
                container, false);

        ListView lview = (ListView) rootView.findViewById(R.id.list);
        ListViewAdapter adapter = new ListViewAdapter(getActivity(), rankList);
        lview.setAdapter(adapter);
        populateList();

        return rootView;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        rankList = new ArrayList<>();
//        ListView lview = (ListView) view.findViewById(R.id.listview);
//        ListViewAdapter adapter = new ListViewAdapter(this.getActivity(), rankList);
//        lview.setAdapter(adapter);
//        populateList();
//
////        adapter.notifyDataSetChanged();
//
//    }


    private void populateList() {

        LeaderBoard item1, item2, item3, item4, item5;

        item1 = new LeaderBoard("1", "Apple (Northern Spy)", "200");
        rankList.add(item1);

        item2 = new LeaderBoard("2", "Orange (Sunkist navel)", "100");
        rankList.add(item2);

        item3 = new LeaderBoard("3", "Tomato", "50");
        rankList.add(item3);

        item4 = new LeaderBoard("4", "Carrot", "80");
        rankList.add(item4);

        item5 = new LeaderBoard("5", "Banana (Cavendish)", "100");
        rankList.add(item5);
    }
}
