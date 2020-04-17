package com.example.maquetadoqr.Repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.maquetadoqr.DAOs.EventConfigDAO;
import com.example.maquetadoqr.DAOs.FieldDAO;
import com.example.maquetadoqr.DAOs.FormDAO;
import com.example.maquetadoqr.DAOs.UserLoginDAO;
import com.example.maquetadoqr.Databases.QRRoomDatabase;
import com.example.maquetadoqr.POJOs.POJOEventConfig;
import com.example.maquetadoqr.POJOs.POJOField;
import com.example.maquetadoqr.POJOs.POJOForm;
import com.example.maquetadoqr.POJOs.POJOUserLogin;

public class UserLoginRepository {
    private UserLoginDAO userLoginDAO;
    private EventConfigDAO eventConfigDAO;
    private FormDAO formDAO;
    private FieldDAO fieldDAO;

    public UserLoginRepository(Application application) {
        QRRoomDatabase db = QRRoomDatabase.getDatabase(application);
        userLoginDAO = db.userLoginDAO();
        eventConfigDAO = db.eventConfigDAO();
        formDAO = db.formDAO();
        fieldDAO = db.fieldDAO();
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

    public void insertForm(POJOForm form) {
        QRRoomDatabase.databaseWriteExecutor.execute(() -> {
            formDAO.insert(form);
        });
    }

    public Integer getLastFormId() {
        return formDAO.getLastFormId();
    }

    public void insertField(POJOField field) {
        QRRoomDatabase.databaseWriteExecutor.execute(() -> {
            fieldDAO.insert(field);
        });
    }
}
