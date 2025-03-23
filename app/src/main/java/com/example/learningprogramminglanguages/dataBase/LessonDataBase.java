package com.example.learningprogramminglanguages.dataBase;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.learningprogramminglanguages.model.Lesson;

@Database(entities = {Lesson.class}, version = 2, exportSchema = false)
public abstract class LessonDataBase extends RoomDatabase {

    private static final String DB_NAME = "lessons.db";
    private static LessonDataBase instance = null;

    public static LessonDataBase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    application,
                    LessonDataBase.class,
                    DB_NAME
            ).fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract LessonDao completeLessonDao();
}
