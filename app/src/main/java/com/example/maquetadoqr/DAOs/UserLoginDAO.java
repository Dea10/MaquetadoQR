package com.example.maquetadoqr.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.maquetadoqr.POJOs.POJOUserLogin;

@Dao
public interface UserLoginDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(POJOUserLogin userLogin);
}
