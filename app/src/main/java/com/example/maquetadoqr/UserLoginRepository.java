package com.example.maquetadoqr;

import android.app.Application;

import com.example.maquetadoqr.DAOs.UserLoginDAO;
import com.example.maquetadoqr.POJOs.POJOUserLogin;

public class UserLoginRepository {
    private UserLoginDAO userLoginDAO;

    public UserLoginRepository(Application application) {
        QRRoomDatabase db = QRRoomDatabase.getDatabase(application);
        userLoginDAO = db.userLoginDAO();
    }

    public void insert(final POJOUserLogin userLogin) {
        QRRoomDatabase.databaseWriteExecutor.execute(() -> {
            userLoginDAO.insert(userLogin);
        });
    }
}
