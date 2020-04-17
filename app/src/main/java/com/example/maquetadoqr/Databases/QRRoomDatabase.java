package com.example.maquetadoqr.Databases;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.maquetadoqr.DAOs.EventConfigDAO;
import com.example.maquetadoqr.DAOs.FieldDAO;
import com.example.maquetadoqr.DAOs.FormDAO;
import com.example.maquetadoqr.DAOs.UserLoginDAO;
import com.example.maquetadoqr.POJOs.POJOEventConfig;
import com.example.maquetadoqr.POJOs.POJOField;
import com.example.maquetadoqr.POJOs.POJOForm;
import com.example.maquetadoqr.POJOs.POJOUserLogin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
            POJOUserLogin.class,
            POJOEventConfig.class,
            POJOForm.class,
            POJOField.class}, version = 1, exportSchema = false)
public abstract class QRRoomDatabase extends RoomDatabase {
    public abstract UserLoginDAO userLoginDAO();
    public abstract EventConfigDAO eventConfigDAO();
    public abstract FormDAO formDAO();
    public abstract FieldDAO fieldDAO();

    private static volatile QRRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static QRRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QRRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QRRoomDatabase.class, "qr_database")
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}
