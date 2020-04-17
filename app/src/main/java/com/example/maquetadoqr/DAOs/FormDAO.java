package com.example.maquetadoqr.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.maquetadoqr.POJOs.POJOForm;

@Dao
public interface FormDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(POJOForm form);

    @Query("SELECT form_id FROM event_form ORDER BY form_id DESC LIMIT 1")
    public Integer getLastFormId();
}
