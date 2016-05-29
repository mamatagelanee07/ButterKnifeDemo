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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NonBindingActivity extends AppCompatActivity {

    @BindView(R.id.btn_fragment_binding)
    Button btnFragmentBinding;
    @BindView(R.id.btn_dialog_binding)
    Button btnDialogBinding;

    private SampleFragment sampleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_binding);
        initUI();
    }

    private void initUI() {
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_fragment_binding)
    void OnFragmentBindingClicked() {
        loadFragment();
    }

    @OnClick(R.id.btn_dialog_binding)
    void OnDialogBindingClicked() {
    }

    private void loadFragment() {
        sampleFragment = SampleFragment.newInstance();
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
