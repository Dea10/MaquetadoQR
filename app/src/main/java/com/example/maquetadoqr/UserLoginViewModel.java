package com.example.maquetadoqr;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.maquetadoqr.POJOs.POJOUserLogin;

public class UserLoginViewModel extends AndroidViewModel {
    private UserLoginRepository mRepository;

    public UserLoginViewModel(@NonNull Application application) {
        super(application);

        mRepository = new UserLoginRepository(application);
    }

    public void insert(POJOUserLogin userLogin) {
        mRepository.insert(userLogin);
    }
}
