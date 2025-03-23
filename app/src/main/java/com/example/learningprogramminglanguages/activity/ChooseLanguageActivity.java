package com.example.learningprogramminglanguages.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.learningprogramminglanguages.R;
import com.example.learningprogramminglanguages.dataBase.LessonDao;
import com.example.learningprogramminglanguages.viewModel.ChooseLanguageViewModel;
import com.example.learningprogramminglanguages.viewModel.ChooseLessonViewModel;

public class ChooseLanguageActivity extends AppCompatActivity {

    private static final String PYTHON = "Python";
    private static final String JAVA = "Java";
    private static final String C_PLUS_PLUS = "CPlusplus";
    private CardView cardViewPython;
    private CardView cardViewJava;
    private CardView cardViewCPlusPlus;
    private ProgressBar progressBarPython;
    private ProgressBar progressBarJava;
    private ProgressBar progressBarCPlusPlus;
    private TextView textViewCompletedPython;
    private TextView textViewCompletedJava;
    private TextView textViewCompletedCPlusPlus;
    private ChooseLanguageViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language_activivty);
        viewModel = new ViewModelProvider(this).get(ChooseLanguageViewModel.class);
        initViews();
        progressBarPython.setMax(29);

        viewModel.getCompletedLessonsCount(PYTHON).observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer completedLessons) {
                String text = String.format("Пройдено: %d из 29 уроков", (completedLessons + 1));
                textViewCompletedPython.setText(text);
                progressBarPython.setProgress(completedLessons + 1);
            }
        });
        cardViewPython.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ChooseLessonActivity.newIntent(
                        ChooseLanguageActivity.this,
                        "Python"
                );
                startActivity(intent);
            }
        });
        cardViewJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ChooseLessonActivity.newIntent(
                        ChooseLanguageActivity.this,
                        "Java"
                );
                startActivity(intent);
            }
        });
        cardViewCPlusPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ChooseLessonActivity.newIntent(
                        ChooseLanguageActivity.this,
                        "CPlusPlus"
                );
                startActivity(intent);
            }
        });
    }

    public void initViews() {
        cardViewPython = findViewById(R.id.cardViewPython);
        cardViewJava = findViewById(R.id.cardViewJava);
        cardViewCPlusPlus = findViewById(R.id.cardViewCPlusPlus);
        textViewCompletedPython = findViewById(R.id.textViewCompletedPython);
        textViewCompletedJava = findViewById(R.id.textViewCompletedJava);
        textViewCompletedCPlusPlus = findViewById(R.id.textViewCompletedCPlusPlus);
        progressBarPython = findViewById(R.id.progressBarPython);
        progressBarJava = findViewById(R.id.progressBarJava);
        progressBarCPlusPlus = findViewById(R.id.progressBarCPlusPlus);
    }
    public static Intent newIntent(Context context) {
        return new Intent(context, ChooseLanguageActivity.class);
    }
}