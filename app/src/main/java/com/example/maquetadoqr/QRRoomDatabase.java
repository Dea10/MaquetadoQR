package com.example.maquetadoqr;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.maquetadoqr.DAOs.UserLoginDAO;
import com.example.maquetadoqr.POJOs.POJOUserLogin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {POJOUserLogin.class}, version = 1, exportSchema = false)
public abstract class QRRoomDatabase extends RoomDatabase {
    public abstract UserLoginDAO userLoginDAO();

    private static volatile QRRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static QRRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QRRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QRRoomDatabase.class, "qr_database")
                            .addCallback(sRoomDatabaseCallback)
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
