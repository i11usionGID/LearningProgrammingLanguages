package com.example.learningprogramminglanguages.dataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.learningprogramminglanguages.model.Lesson;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface LessonDao {

    @Query("SELECT COUNT(*) FROM complete_lessons WHERE language = :language")
    LiveData<Integer> getCompletedLessonsCount(String language);
    @Query("SELECT * FROM complete_lessons WHERE language = :language")
    LiveData<List<Lesson>> getAllCompletedLessons(String language);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertLesson(Lesson lesson);
}
