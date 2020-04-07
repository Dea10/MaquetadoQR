package com.example.maquetadoqr.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.maquetadoqr.Entities.EventConfiguration;
@Dao
public interface EventConfigurationDAO {
    @Query("SELECT * FROM event_configuration WHERE feature_id = :id LIMIT 1")
    EventConfiguration findConfigurationById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertConfiguration(EventConfiguration eventConfiguration);


}
