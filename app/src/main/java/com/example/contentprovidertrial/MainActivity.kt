package com.example.contentprovidertrial

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.contentprovidertrial.ui.theme.ContentProviderTrialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentProviderTrialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting() {
    val context = LocalContext.current
    Column {
        var input by remember {
            mutableStateOf("")
        }
        Text(text = "Send to Slave:")
        TextField(value = input, onValueChange = {
            input = it
        })
        Button(onClick = { 
            val extras = Bundle().apply { 
                putString("string", input)
            }
            val mUri = "com.slaveapp.provider"
            val uri = Uri.parse("content://" + mUri + "/data")
            context.contentResolver.call(
                uri,
                "toSlave",
                null,
                extras
            )
            input = ""
        }) {
            Text(text = "Send")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContentProviderTrialTheme {
        Greeting()
    }
}