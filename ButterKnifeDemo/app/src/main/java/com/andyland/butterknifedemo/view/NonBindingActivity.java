package com.andyland.butterknifedemo.view;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.andyland.butterknifedemo.R;
import com.andyland.butterknifedemo.view.fragment.ListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Andy on 29-May-16.
 * Attempt to show how to use ButterKnife in non activity components
 */
public class NonBindingActivity extends AppCompatActivity {

    @BindView(R.id.btn_fragment_binding)
    Button btnFragmentBinding;
    @BindView(R.id.btn_list_binding)
    Button btnListBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_binding);
        initUI();
    }

    /**
     * Initializes all UI elements
     */
    private void initUI() {
        ButterKnife.bind(this);
    }


    /**
     * Attempts to call below method when 'Fragment Binding Button' get clicked.
     */
    @OnClick(R.id.btn_fragment_binding)
    void OnFragmentBindingClicked() {
        loadSampleFragment();
    }

    /**
     * Attempts to call below method when 'List Binding Button' get clicked.
     */
    @OnClick(R.id.btn_list_binding)
    void OnListBindingClicked() {
        Toast.makeText(NonBindingActivity.this, "List Binding button clicked", Toast.LENGTH_SHORT).show();
        loadListFragment();
    }

    /**
     * Attempts to load ListFragment
     */
    private void loadListFragment() {
        ListFragment listFragment = ListFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.frame_container, listFragment, ListFragment.class.getSimpleName())
                .commit();
    }

    /**
     * Attempts to load @SampleFragment
     */
    private void loadSampleFragment() {
        SampleFragment sampleFragment = SampleFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.frame_container, sampleFragment, SampleFragment.class.getSimpleName())
                .commit();
    }

    public static class SampleFragment extends Fragment {
        @BindView(R.id.btn_sample)
        Button btnSample;
        private static SampleFragment sampleFragment;

        public static SampleFragment newInstance() {
            if (sampleFragment == null) {
                sampleFragment = new SampleFragment();
            }
            return sampleFragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_sample, container, false);
            ButterKnife.bind(this, view);
            return view;
        }

        @OnClick(R.id.btn_sample)
        void onSampleButtonClicked() {
            Toast.makeText(SampleFragment.this.getActivity(), "Sample Button clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
