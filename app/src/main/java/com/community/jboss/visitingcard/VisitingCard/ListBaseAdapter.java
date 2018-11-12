package com.community.jboss.visitingcard.VisitingCard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.community.jboss.visitingcard.R;

import java.util.ArrayList;

public class ListBaseAdapter extends BaseAdapter{

    private ArrayList<ListDetail> listDetailArrayList;
    private LayoutInflater inflater;

    public ListBaseAdapter(Context context, ArrayList<ListDetail> results) {
        listDetailArrayList = results;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listDetailArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return listDetailArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = inflater.inflate(R.layout.list_item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.text_name);
            viewHolder.description = (TextView) view.findViewById(R.id.text_description);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.name.setText(listDetailArrayList.get(i).getName());
        viewHolder.description.setText(listDetailArrayList.get(i).getDescription());

        return view;
    }

    static class ViewHolder {
        public TextView name;
        public TextView description;
    }
}
