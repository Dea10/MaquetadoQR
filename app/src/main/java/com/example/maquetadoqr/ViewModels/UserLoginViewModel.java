package com.example.maquetadoqr.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.maquetadoqr.POJOs.POJOEventConfig;
import com.example.maquetadoqr.POJOs.POJOField;
import com.example.maquetadoqr.POJOs.POJOForm;
import com.example.maquetadoqr.POJOs.POJOUserLogin;
import com.example.maquetadoqr.Repositories.UserLoginRepository;

public class UserLoginViewModel extends AndroidViewModel {
    private UserLoginRepository mRepository;

    public UserLoginViewModel(@NonNull Application application) {
        super(application);

        mRepository = new UserLoginRepository(application);
    }

    public void insertUserLogin(POJOUserLogin userLogin) {
        mRepository.insertUserLogin(userLogin);
    }

    public void insertEventConfig(POJOEventConfig eventConfig) {
        mRepository.insertEventConfig(eventConfig);
    }

    public void insertForm(POJOForm form) {
        mRepository.insertForm(form);
    }

    public void insertField(POJOField field) {
        mRepository.insertField(field);
    }

    public Integer getLastFormId() {
        return mRepository.getLastFormId();
    }
}
