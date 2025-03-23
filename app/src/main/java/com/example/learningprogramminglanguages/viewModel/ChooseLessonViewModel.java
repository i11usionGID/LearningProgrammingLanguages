package com.example.learningprogramminglanguages.viewModel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.learningprogramminglanguages.JsonHelper;
import com.example.learningprogramminglanguages.dataBase.LessonDao;
import com.example.learningprogramminglanguages.dataBase.LessonDataBase;
import com.example.learningprogramminglanguages.model.Lesson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class ChooseLessonViewModel extends AndroidViewModel {
    private static final String FILE_END = "Lessons.json";
    private final MutableLiveData<List<Lesson>> lessonsPython = new MutableLiveData<>();

    private final LessonDao lessonDao;

    public ChooseLessonViewModel(@NonNull Application application) {
        super(application);
        lessonDao = LessonDataBase.getInstance(application).completeLessonDao();
        loadLessons();
    }

    public LiveData<List<Lesson>> getLessonsPython() {
        return lessonsPython;
    }

    public List<Lesson> getLessonsPythonList() {
        return lessonsPython.getValue();
    }

    public LiveData<List<Lesson>> getAllCompletedLessons(String language) {
        return lessonDao.getAllCompletedLessons(language);
    }

    public void loadLessons() {
        Context context = getApplication().getApplicationContext();
        String fileName = "Python" + FILE_END;
        String jsonString = JsonHelper.loadJSONFromAssets(context, fileName);
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Lesson>>() {}.getType();
        List<Lesson> lessonsFromJson = gson.fromJson(jsonString, listType);
        lessonsPython.setValue(lessonsFromJson);
        Log.d("ChooseLessonViewModel", lessonsFromJson.toString());
    }
}
