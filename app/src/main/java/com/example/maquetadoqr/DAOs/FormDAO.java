package com.example.maquetadoqr.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.maquetadoqr.POJOs.POJOForm;

@Dao
public interface FormDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(POJOForm form);
}
