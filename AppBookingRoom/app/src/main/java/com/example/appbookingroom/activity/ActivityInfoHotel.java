package com.example.appbookingroom.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbookingroom.R;
import com.example.appbookingroom.common.CommonUtils;
import com.example.appbookingroom.common.Constants;

public class ActivityInfoHotel extends AppCompatActivity {
    private TextView description, txtReadMore, txtAddressForHotel, icHeart, txtBack;
    private LinearLayout optionHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_info_hotel);
        init();
        event();
    }

    private void init() {
        description = findViewById(R.id.description);
        txtAddressForHotel = findViewById(R.id.txtAddressForHotel);
        txtReadMore = findViewById(R.id.readMore);
        icHeart = findViewById(R.id.icHeart);
        txtBack = findViewById(R.id.txtBack);
        optionHotel = findViewById(R.id.option_hotel);
        configOptionHotel();
    }

    @SuppressLint("ResourceAsColor")
    private void configOptionHotel() {
        optionHotel.setWeightSum(4);
        boolean flagFirst = true;
        for (int i = 0; i < 4; i++) {
            LinearLayout parent = new LinearLayout(getApplicationContext());
            parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200, 1));
            parent.setOrientation(LinearLayout.VERTICAL);
            TextView ic = CommonUtils.createdView(getApplicationContext(),R.drawable.ic_location_black);
            TextView txtView = CommonUtils.createdView(getApplicationContext(),R.string.welcome, Constants.ACTION.CREATED_TEXT);
            if(flagFirst){
                txtView.setTextColor(R.color.black);
                parent.setBackgroundResource(R.drawable.style_option_hotel);
                flagFirst = false;
            }else {
                txtView.setTextColor(R.color.white);
            }

            parent.addView(ic);
            parent.addView(txtView);
            parent.setGravity(Gravity.CENTER);
            parent.setPadding(0,0,0,0);
            optionHotel.addView(parent);
        }
    }

    private void event() {
        txtReadMore.setOnClickListener(v -> {
            description.setMaxLines(Integer.MAX_VALUE);
        });
        txtAddressForHotel.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MapsActivity.class));
        });
        icHeart.setOnClickListener(v -> {
            icHeart.setBackgroundResource(R.drawable.ic_heart_red);
        });
        txtBack.setOnClickListener(v -> {
            finish();
        });
    }
}