package com.andyland.butterknifedemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.andyland.butterknifedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    /**
     * You can not declare view as private if you want to use ButterKnife.
     * The reason that Butter Knife requires views not be private is that is actually generates code
     * which sets the fields. The code that it generates lives in the same package as your class which
     * is why the field must be package-private, protected, or public.
     * If the field was private the generated code would fail to compile since it cannot access
     * the private field.
     * <p/>
     * http://stackoverflow.com/a/27245018/4018207
     */
    @BindView(R.id.btn_view_binding)
    Button btnViewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Call 'bind' method to bind all views using ButterKnife

         /* Below code will get converted into :
            public void bind(ExampleActivity activity) {
                activity.btnViewBinding = (android.widget.Button) activity.findViewById(2130968578);
            }
         */
        ButterKnife.bind(this);
    }


    /**
     * Attempts to call below method when 'View Binding Button' get clicked.
     */
    @OnClick(R.id.btn_view_binding)
    public void OnViewBindingClicked() {
        Log.i(TAG, "View Binding button clicked..!!");
        Toast.makeText(MainActivity.this, "View Binding button clicked..!!", Toast.LENGTH_SHORT).show();
    }
}
