package com.example.vitals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vitals.ui.VitalsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: VitalsViewModel = // Initialize via your preferred Factory/DI
            MainScreen(viewModel)
        }
    }
}

@Composable
fun MainScreen(viewModel: VitalsViewModel) {
    val vitals by viewModel.vitalsList.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Pregnancy Vitals") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(vitals) { item ->
                Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("BP: ${item.systolic}/${item.diastolic} mmHg")
                        Text("HR: ${item.heartRate} bpm")
                        Text("Weight: ${item.weight} kg")
                        Text("Baby Kicks: ${item.babyKicks}")
                    }
                }
            }
        }
    }

    if (showDialog) {
        AddVitalsDialog(
            onDismiss = { showDialog = false },
            onSave = { s, d, h, w, k -> viewModel.addVital(s, d, h, w, k) }
        )
    }
}
