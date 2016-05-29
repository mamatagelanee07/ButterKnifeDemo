package com.andyland.butterknifedemo.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andyland.butterknifedemo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Andy on 29-May-16.
 * Attempts to show how to use ButterKnife while using RecyclerView or ListView
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<String> titleList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title)
        public TextView title;

        public ViewHolder(View view) {
            super(view);
            // Bind current object to its view
            ButterKnife.bind(this, view);
        }
    }


    public ListAdapter(List<String> titleList) {
        this.titleList = titleList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_item_string, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Now directly use title TextView
        String title = titleList.get(position);
        holder.title.setText(title);
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }
}
