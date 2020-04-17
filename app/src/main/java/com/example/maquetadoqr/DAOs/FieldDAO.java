package com.example.maquetadoqr.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.maquetadoqr.POJOs.POJOField;

@Dao
public interface FieldDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(POJOField field);
}
