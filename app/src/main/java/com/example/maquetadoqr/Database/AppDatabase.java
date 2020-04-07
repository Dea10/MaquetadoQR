package com.example.maquetadoqr.Database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.maquetadoqr.DAO.EventConfigurationDAO;
import com.example.maquetadoqr.Entities.EventConfiguration;

@Database(entities = {EventConfiguration.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {


    public abstract EventConfigurationDAO eventConfigurationDAO();

    private static AppDatabase mInstance;

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
