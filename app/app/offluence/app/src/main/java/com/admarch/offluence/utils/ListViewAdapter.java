package com.admarch.offluence.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.admarch.offluence.R;
import com.admarch.offluence.model.LeaderBoard;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    public ArrayList<LeaderBoard> rankList;
    Context context;
    SessionManager sessionManager;
    int currItem = -1;
    public ListViewAdapter(Context context, ArrayList<LeaderBoard> rankList) {
        super();
        this.context = context;
        this.rankList = rankList;
    }

    public void setCurrItem(int currItem) {
        this.currItem = currItem;
    }

    @Override
    public int getCount() {
        return rankList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

    }

    private class ViewHolder {
        TextView mSNo;
        TextView mProduct;
        TextView mCategory;
        TextView mPrice;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        sessionManager = new SessionManager(context);
        String regNum = sessionManager.getUserDetails().get(SessionManager.KEY_NAME).toString();
//        LayoutInflater inflater = context.getApplicationContext().getLayoutInflater();

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_row, null);
//            convertView = inflater.inflate(R.layout.listview_row, null);
            holder = new ViewHolder();
            holder.mSNo = (TextView) convertView.findViewById(R.id.sNo);
            holder.mProduct = (TextView) convertView.findViewById(R.id.product);
            holder.mCategory = (TextView) convertView.findViewById(R.id.category);
            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }


        LeaderBoard item = rankList.get(position);

//        if(position == currItem){
//            convertView.setBackgroundColor(convertView.getResources().getColor(R.color.teal_700));
//        }
        if(item.getRegisterNumber().equalsIgnoreCase("test2nik")){
//            holder.mSNo.setTextColor(convertView.getResources().getColor(R.color.teal_700));
//            holder.mProduct.setTextColor(convertView.getResources().getColor(R.color.teal_700));
//            holder.mCategory.setTextColor(convertView.getResources().getColor(R.color.teal_700));
//            convertView.setBackgroundColor(convertView.getResources().getColor(R.color.teal_700));


        }
        holder.mSNo.setText(item.getRank().toString());
        holder.mProduct.setText(item.getRegisterNumber().toString());
        holder.mCategory.setText(item.getActionEarning().toString());




        return convertView;    }


}
