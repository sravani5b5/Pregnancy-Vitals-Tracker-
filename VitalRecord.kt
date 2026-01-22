package com.example.vitals.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vitals_table")
data class VitalRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val systolic: Int,
    val diastolic: Int,
    val heartRate: Int,
    val weight: Double,
    val babyKicks: Int,
    val timestamp: Long = System.currentTimeMillis()
)
