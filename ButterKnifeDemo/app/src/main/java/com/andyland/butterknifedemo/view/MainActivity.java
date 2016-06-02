package com.andyland.butterknifedemo.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.andyland.butterknifedemo.R;
import com.crashlytics.android.Crashlytics;

import butterknife.BindBool;
import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;

/**
 * Created by Andy on 29-May-16.
 * Attempt to show how to use ButterKnife
 */

public class MainActivity extends AppCompatActivity {

    // TAG used for logging
    private static final String TAG = MainActivity.class.getSimpleName();


    //You can not declare view as private if you want to use ButterKnife.
    //The reason that Butter Knife requires views not be private is that is actually generates code
    //which sets the fields. The code that it generates lives in the same package as your class which
    //is why the field must be package-private, protected, or public.
    //If the field was private the generated code would fail to compile since it cannot access
    //the private field.
    //
    //http://stackoverflow.com/a/27245018/4018207
    @BindView(R.id.btn_view_binding)
    Button btnViewBinding;
    @BindView(R.id.btn_resource_binding)
    Button btnResourceBinding;
    @BindView(R.id.btn_non_activity_binding)
    Button btnNonActivityBinding;
    @BindView(R.id.btn_action_binding)
    Button btnActionBinding;
    @BindView(R.id.txt_drawable_binding)
    TextView txtDrawableBinding;
    @BindView(R.id.check_me)
    CheckBox checkMe;
    @BindView(R.id.bar)
    View barView;

    //Here we are setting string resources as a label text to buttons.
    @BindString(R.string.btn_title_view_binding)
    String titleViewBinding;
    @BindString(R.string.btn_title_resource_binding)
    String titleResourceBinding;
    @BindString(R.string.btn_title_non_activity_binding)
    String titleNonActivityBinding;
    @BindString(R.string.btn_title_action_binding)
    String titleActionBinding;
    @BindString(R.string.txt_title_drawable_binding)
    String titleDrawableBinding;
    @BindString(R.string.check_title_me)
    String titleCheckMe;

    @BindColor(R.color.colorButtonBG)
    int colorBackground;

    @BindBool(R.bool.boolTrue)
    boolean boolTrue;
    @BindBool(R.bool.boolFalse)
    boolean boolFalse;

    //    If defined as int then it will return pixel value.
    //    If defined as float it will return value exact as defined in resource folder
    @BindDimen(R.dimen.check_text_size_small)
    int textSmall;
    @BindDimen(R.dimen.check_text_size_large)
    int textLarge;

    @BindDrawable(R.drawable.ic_action_lock_open)
    Drawable lockOpen;
    @BindDrawable(R.drawable.ic_action_lock_outline)
    Drawable lockClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Added Crashnalytic to track crashes in demo
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        initUI();
    }

    /**
     * Attempts to initialize all UI Resources
     */
    private void initUI() {

        // Call 'bind' method to bind all views using ButterKnife

//          Below code will get converted into :
//            public void bind(ExampleActivity activity) {
//                activity.btnViewBinding = (android.widget.Button) activity.findViewById(2130968578);
//            }
        ButterKnife.bind(this);

//        We have bound color resource to int here.
//        So we can direct set this color to background on button here.
        btnViewBinding.setBackgroundColor(colorBackground);
        btnResourceBinding.setBackgroundColor(colorBackground);
        btnNonActivityBinding.setBackgroundColor(colorBackground);
        btnActionBinding.setBackgroundColor(colorBackground);
        txtDrawableBinding.setBackgroundColor(colorBackground);

//        We have bound string resource to our string reference here.
//        So we can easily use direct this string to set text in button label
        btnViewBinding.setText(titleViewBinding);
        btnResourceBinding.setText(titleResourceBinding);
        btnNonActivityBinding.setText(titleNonActivityBinding);
        btnActionBinding.setText(titleActionBinding);
        txtDrawableBinding.setText(titleDrawableBinding);
        checkMe.setText(titleCheckMe);

        txtDrawableBinding.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSmall);
    }

    /**
     * Attempts to call below method when 'View Binding Button' get clicked.
     */
    @OnClick(R.id.btn_view_binding)
    public void OnViewBindingClicked() {
        Log.i(TAG, "View Binding button clicked..!!");
        Toast.makeText(MainActivity.this, "View Binding button clicked..!!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Attempts to call below method when 'Resource Binding Button' get clicked.
     */
    @OnClick(R.id.btn_resource_binding)
    public void OnResourceBindingClicked() {
        Log.i(TAG, "Resource Binding button clicked..!!");
        Toast.makeText(MainActivity.this, "Resource Binding button clicked..!!", Toast.LENGTH_SHORT).show();
        bindBooleanResources();
        bindDimenResources();
    }

    /**
     * Attempts to call below method when 'Non Activity Binding Button' get clicked.
     */
    @OnClick(R.id.btn_non_activity_binding)
    public void onNonActivityBindingClicked() {
        Log.i(TAG, "Non Activity Binding Button clicked..!!");
        Toast.makeText(MainActivity.this, "Non Activity Binding Button clicked..!!", Toast.LENGTH_SHORT).show();

        // Open new activity NonActivityBinding
        openActivity();
    }


    /**
     * Attempts to call below method when 'Action Binding Button' get clicked.
     */
    @OnClick(R.id.btn_action_binding)
    public void onActionBindingClicked() {
        Log.i(TAG, "Action Binding Button clicked..!!");
        Toast.makeText(MainActivity.this, "Action Binding Button clicked..!!", Toast.LENGTH_SHORT).show();

        // Enable/Disable bar view
        enableBarView(!barView.isEnabled());
    }

    /**
     * Attempts to call below method when 'Drawable Resource Binding Button' get clicked.
     */
    @OnClick(R.id.txt_drawable_binding)
    public void OnDrawableResourceBindingClicked() {
        Log.i(TAG, "Drawable Resource Binding button clicked..!!");
        Toast.makeText(MainActivity.this, "Drawable Resource Binding button clicked..!!", Toast.LENGTH_SHORT).show();
        bindDrawableResources();
    }

    /**
     * An Example of how to bind 'boolean' resources
     */
    private void bindBooleanResources() {
        if (checkMe.isChecked()) {
            checkMe.setChecked(boolFalse);
        } else {
            checkMe.setChecked(boolTrue);
        }
    }

    /**
     * An Example of how to bind 'dimen' resources
     */
    private void bindDimenResources() {
        if (checkMe.getTextSize() == textSmall) {// getTextSize() gives value in Pixel format
            checkMe.setTextSize(TypedValue.COMPLEX_UNIT_PX, textLarge);
            Log.i(TAG, "Large text size : " + textLarge);
        } else {
            checkMe.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSmall);
            Log.i(TAG, "Small text size :" + textSmall);
        }
    }

    /**
     * An Example of how to bind 'drawable' resources
     */
    private void bindDrawableResources() {
        if (txtDrawableBinding.isSelected()) {
            txtDrawableBinding.setCompoundDrawablesWithIntrinsicBounds(null, null, lockOpen, null);
            txtDrawableBinding.setSelected(boolFalse);
        } else {
            txtDrawableBinding.setCompoundDrawablesWithIntrinsicBounds(null, null, lockClose, null);
            txtDrawableBinding.setSelected(boolTrue);
        }
    }

    /**
     * Attempts to open new activity.
     */
    private void openActivity() {
        Intent intent = new Intent(MainActivity.this, NonBindingActivity.class);
        startActivity(intent);
    }

    /**
     * Attempts to enable/disable bar view.
     */
    private void enableBarView(boolean enable) {
        if (enable) {
            ButterKnife.apply(barView, ENABLED);
        } else {
            ButterKnife.apply(barView, DISABLE);
        }
    }

    /**
     * Action to disable view
     */
    static final ButterKnife.Action<View> DISABLE = new ButterKnife.Action<View>() {
        @Override
        public void apply(@NonNull View view, int index) {
            view.setEnabled(false);
        }
    };

    /**
     * Action to enable view
     */
    static final ButterKnife.Action<View> ENABLED = new ButterKnife.Action<View>() {
        @Override
        public void apply(@NonNull View view, int index) {
            view.setEnabled(true);
        }
    };
}
