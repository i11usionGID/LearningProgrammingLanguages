package com.example.learningprogramminglanguages.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.learningprogramminglanguages.dataBase.LessonDao;
import com.example.learningprogramminglanguages.dataBase.LessonDataBase;
import com.example.learningprogramminglanguages.model.Lesson;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ShowLessonViewModel extends AndroidViewModel {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private LessonDao lessonDao;

    public ShowLessonViewModel(@NonNull Application application) {
        super(application);
        lessonDao = LessonDataBase.getInstance(application).completeLessonDao();
    }

    public void insertLesson(Lesson lesson) {
        Disposable disposable = lessonDao.insertLesson(lesson)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
