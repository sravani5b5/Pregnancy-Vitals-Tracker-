package com.example.vitals.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitals.data.VitalRecord
import com.example.vitals.data.VitalsDao
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class VitalsViewModel(private val dao: VitalsDao) : ViewModel() {

    val vitalsList: StateFlow<List<VitalRecord>> = dao.getAllVitals()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addVital(sys: Int, dia: Int, hr: Int, weight: Double, kicks: Int) {
        viewModelScope.launch {
            dao.insertVital(VitalRecord(
                systolic = sys, diastolic = dia, 
                heartRate = hr, weight = weight, babyKicks = kicks
            ))
        }
    }
}
