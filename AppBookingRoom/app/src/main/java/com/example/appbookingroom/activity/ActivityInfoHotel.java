package com.example.appbookingroom.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbookingroom.R;

public class ActivityInfoHotel extends AppCompatActivity {
    private TextView description,txtReadMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_hotel);
        init();
        event();
    }
    private void init(){
        description= findViewById(R.id.description);
//        description.setMovementMethod(new ScrollingMovementMethod());
        txtReadMore = findViewById(R.id.readMore);
    }
    private void event(){
        txtReadMore.setOnClickListener(v->{
            description.setMaxLines(Integer.MAX_VALUE);
        });
    }
}