package com.example.learningprogramminglanguages.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.learningprogramminglanguages.dataBase.LessonDao;
import com.example.learningprogramminglanguages.dataBase.LessonDataBase;

public class ChooseLanguageViewModel extends AndroidViewModel {

    private final LessonDao lessonDao;

    public ChooseLanguageViewModel(@NonNull Application application) {
        super(application);
        lessonDao = LessonDataBase.getInstance(application).completeLessonDao();
    }

    public LiveData<Integer> getCompletedLessonsCount(String language) {
        return lessonDao.getCompletedLessonsCount(language);
    }
}
