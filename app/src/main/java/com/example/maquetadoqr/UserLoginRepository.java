package com.example.maquetadoqr;

import android.app.Application;

import com.example.maquetadoqr.DAOs.EventConfigDAO;
import com.example.maquetadoqr.DAOs.UserLoginDAO;
import com.example.maquetadoqr.POJOs.POJOEventConfig;
import com.example.maquetadoqr.POJOs.POJOUserLogin;

public class UserLoginRepository {
    private UserLoginDAO userLoginDAO;
    private EventConfigDAO eventConfigDAO;

    public UserLoginRepository(Application application) {
        QRRoomDatabase db = QRRoomDatabase.getDatabase(application);
        userLoginDAO = db.userLoginDAO();
        eventConfigDAO = db.eventConfigDAO();
    }

    public void insertUserLogin(final POJOUserLogin userLogin) {
        QRRoomDatabase.databaseWriteExecutor.execute(() -> {
            userLoginDAO.insert(userLogin);
        });
    }

    public void insertEventConfig(final POJOEventConfig eventConfig) {
        QRRoomDatabase.databaseWriteExecutor.execute(() -> {
            eventConfigDAO.insert(eventConfig);
        });
    }
}
