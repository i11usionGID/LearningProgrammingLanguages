package com.example.learningprogramminglanguages.activity;

import static java.lang.System.in;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningprogramminglanguages.LessonAdapter;
import com.example.learningprogramminglanguages.R;
import com.example.learningprogramminglanguages.model.Lesson;
import com.example.learningprogramminglanguages.viewModel.ChooseLessonViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChooseLessonActivity extends AppCompatActivity {

    private static final String KEY = "languageName";
    private static final String PYTHON = "Python";
    private static final String JAVA = "Java";
    private ChooseLessonViewModel viewModel;

    private RecyclerView recyclerViewLessons;
    private LessonAdapter lessonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_lesson);

        recyclerViewLessons = findViewById(R.id.recyclerViewLesson);
        lessonAdapter = new LessonAdapter();

        recyclerViewLessons.setAdapter(lessonAdapter);
        recyclerViewLessons.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(ChooseLessonViewModel.class);

        Intent intent = getIntent();
        String language = intent.getStringExtra(KEY);
        switch (language) {
            case PYTHON:
                List<Lesson> pythonLesson = new ArrayList<>();//
                viewModel.getAllCompletedLessons(PYTHON).observe(this, new Observer<List<Lesson>>() {
                    @Override
                    public void onChanged(List<Lesson> lessons) {
                        for (Lesson lesson: pythonLesson) {
                            for (Lesson completeLesson: lessons) {
                                if (lesson.getId() == completeLesson.getId()) {
                                    lesson.setLock(false);
                                }
                            }
                        }
                        lessonAdapter.setLessons(pythonLesson);
                    }
                });
                viewModel.getLessonsPython().observe(this, new Observer<List<Lesson>>() {
                    @Override
                    public void onChanged(List<Lesson> lessons) {
                        pythonLesson.addAll(lessons);
                        lessonAdapter.setLessons(lessons);
                    }
                });
                lessonAdapter.setOnLessonClickListener(new LessonAdapter.OnLessonClickListener() {
                    @Override
                    public void onLessonClick(Lesson lesson) {
                        if (lesson.isLock()) {
                            Toast.makeText(
                                    ChooseLessonActivity.this,
                                    R.string.toast_message,
                                    Toast.LENGTH_SHORT
                            ).show();
                        } else {
                            Intent intent = ShowLessonActivity.newIntent(
                                    ChooseLessonActivity.this,
                                    viewModel.getLessonsPythonList(),
                                    lesson.getId()-1);
                            startActivity(intent);
                        }
                    }
                });
                break;
            case JAVA:
                break;
            default:
                break;
        }
    }

    public static Intent newIntent(Context context, String languageName) {
        Intent intent = new Intent(context, ChooseLessonActivity.class);
        intent.putExtra(KEY, languageName);
        return intent;
    }
}