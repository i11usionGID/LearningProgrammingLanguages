package com.example.learningprogramminglanguages.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningprogramminglanguages.R;

public class MainActivity extends AppCompatActivity {

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ChooseLanguageActivity.newIntent(MainActivity.this);
                startActivity(intent);
                finish();
            }
        });
    }

    public void initViews() {
        startButton = findViewById(R.id.buttonNextStep);
    }
}