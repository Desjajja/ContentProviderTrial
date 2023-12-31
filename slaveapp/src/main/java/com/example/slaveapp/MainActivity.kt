package com.example.slaveapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.example.slaveapp.ui.ScreenViewModel
import com.example.slaveapp.ui.theme.ContentProviderTrialTheme

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var screenViewModel: ScreenViewModel
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            screenViewModel = ScreenViewModel()
            ContentProviderTrialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SlaveApp(screenViewModel)
                }
            }
        }
    }
}

@Composable
fun SlaveApp(screenViewModel: ScreenViewModel = ScreenViewModel()) {
    val uiState by screenViewModel.uiState.collectAsState()
    Column {
        Text(text = uiState.text ?: "Nothing received yet")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContentProviderTrialTheme {
        Greeting("Android")
    }
}