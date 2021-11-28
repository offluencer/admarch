package com.admarch.offluence.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.admarch.offluence.R;
import com.admarch.offluence.model.LeaderBoard;
import com.admarch.offluence.utils.ListViewAdapter;
import com.admarch.offluence.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

public class LeaderBoardFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private ArrayList<LeaderBoard> rankList;
    SessionManager session;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        session = new SessionManager(getContext());

        Map userDetails = session.getUserDetails();

        String regNumber = (String) userDetails.get(session.KEY_NAME);

        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
//                R.array.Planets, android.R.layout.simple_list_item_1);
        rankList = new ArrayList<>();
        populateList();

        ListViewAdapter adapter = new ListViewAdapter(getContext(), rankList);
        setListAdapter(adapter);
//        setListAdapter(adapter);
//        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }

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

    private void fillRankingsData(String regNumber, View view) {

    }
}
