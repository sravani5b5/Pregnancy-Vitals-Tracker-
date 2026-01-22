package com.example.vitals.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VitalsDao {
    @Query("SELECT * FROM vitals_table ORDER BY timestamp DESC")
    fun getAllVitals(): Flow<List<VitalRecord>>

    @Insert
    suspend fun insertVital(vital: VitalRecord)
}
