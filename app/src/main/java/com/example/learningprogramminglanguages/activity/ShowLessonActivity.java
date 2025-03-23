package com.example.learningprogramminglanguages.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.learningprogramminglanguages.R;
import com.example.learningprogramminglanguages.model.Lesson;
import com.example.learningprogramminglanguages.viewModel.ShowLessonViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShowLessonActivity extends AppCompatActivity {

    private static final String LESSONS_EXTRA = "lessons";
    private static final String POSITION_EXTRA = "position";
    private TextView textViewLessonTitle;
    private TextView textViewLessonContent;
    private Button buttonBack;
    private Button buttonNext;
    private List<Lesson> lessons;
    private ScrollView scrollViewLesson;
    private int position;
    private ShowLessonViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lesson);
        viewModel = new ViewModelProvider(this).get(ShowLessonViewModel.class);
        initViews();
        lessons = (ArrayList<Lesson>) getIntent().getSerializableExtra(LESSONS_EXTRA);
        if (savedInstanceState != null) {
            position = savedInstanceState.getInt(POSITION_EXTRA, 0);
        } else {
            position = getIntent().getIntExtra(POSITION_EXTRA, 0);
        }

        showLesson(position);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                showLesson(position);
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                showLesson(position);
                viewModel.insertLesson(lessons.get(position));
            }
        });
    }

    public void showLesson(int position) {
        buttonNext.setVisibility(View.VISIBLE);
        buttonBack.setVisibility(View.VISIBLE);
        textViewLessonTitle.setText(lessons.get(position).getTitle());
        textViewLessonContent.setText(lessons.get(position).getText());

        scrollViewLesson.post(new Runnable() {
            @Override
            public void run() {
                scrollViewLesson.fullScroll(View.FOCUS_UP);
            }
        });

        if (lessons.get(position).getId() == 1) {
            buttonBack.setVisibility(View.GONE);
        }
        if (lessons.get(position).getId() == lessons.size()) {
            buttonNext.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION_EXTRA, position);
    }

    public static Intent newIntent(Context context, List<Lesson> lessons, int position) {
        Intent intent = new Intent(context, ShowLessonActivity.class);
        intent.putExtra(LESSONS_EXTRA,new ArrayList<>(lessons));
        intent.putExtra(POSITION_EXTRA, position);
        return intent;
    }

    private void initViews() {
        textViewLessonTitle = findViewById(R.id.textViewLessonTitle);
        textViewLessonContent = findViewById(R.id.textViewLessonContent);
        buttonBack = findViewById(R.id.buttonBack);
        buttonNext = findViewById(R.id.buttonNext);
        scrollViewLesson = findViewById(R.id.scrollViewLesson);
    }
}