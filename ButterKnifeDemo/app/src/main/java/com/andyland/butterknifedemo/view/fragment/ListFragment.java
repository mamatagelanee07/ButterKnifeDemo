package com.andyland.butterknifedemo.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andyland.butterknifedemo.R;
import com.andyland.butterknifedemo.common.adapter.ListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Andy on 29-May-16.
 * Attempt to show how to use ButterKnife in RecyclerView
 */
public class ListFragment extends Fragment {
    private static ListFragment listFragment;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    /**
     * To get single instance of fragment
     *
     * @return unique reference of Fragment
     */
    public static ListFragment newInstance() {
        if (listFragment == null) {
            listFragment = new ListFragment();
        }
        return listFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        initUI(view);
        return view;
    }

    /**
     * Initializes all UI elements
     *
     * @param view : A reference of view
     */
    private void initUI(View view) {
        ButterKnife.bind(this, view);

        // set an Adapter to RecyclerView
        ArrayList<String> titles = getTitles();
        ListAdapter mAdapter = new ListAdapter(titles);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * Get list of all titles
     *
     * @return : list of titles
     */
    private ArrayList<String> getTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            titles.add("Item : " + i);
        }
        return titles;
    }
}
