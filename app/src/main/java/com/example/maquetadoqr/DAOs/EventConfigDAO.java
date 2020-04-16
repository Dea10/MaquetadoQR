package com.example.maquetadoqr.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.maquetadoqr.POJOs.POJOEventConfig;

@Dao
public interface EventConfigDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(POJOEventConfig eventConfig);
}
