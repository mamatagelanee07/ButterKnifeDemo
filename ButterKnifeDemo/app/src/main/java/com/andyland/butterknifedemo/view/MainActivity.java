package com.andyland.butterknifedemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.andyland.butterknifedemo.R;

import butterknife.BindBool;
import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

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
    @BindView(R.id.check_me)
    CheckBox checkMe;

    //Here we are setting string resources as a label text to buttons.
    @BindString(R.string.btn_title_view_binding)
    String titleViewBinding;
    @BindString(R.string.btn_title_resource_binding)
    String titleResourceBinding;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

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

//        We have bound string resource to our string reference here.
//        So we can easily use direct this string to set text in button label
        btnViewBinding.setText(titleViewBinding);
        btnResourceBinding.setText(titleResourceBinding);
        checkMe.setText(titleCheckMe);
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

//        Here is an example of how to bind boolean resources
        if (checkMe.isChecked()) {
            checkMe.setChecked(boolFalse);
        } else {
            checkMe.setChecked(boolTrue);
        }

//        Here is an example of how to bind dimen resources
        if (checkMe.getTextSize() == textSmall) {// getTextSize() gives value in Pixel format
            checkMe.setTextSize(TypedValue.COMPLEX_UNIT_PX, textLarge);
            Log.i(TAG, "Large text size : " + textLarge);
        } else {
            checkMe.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSmall);
            Log.i(TAG, "Small text size :" + textSmall);
        }
    }
}
