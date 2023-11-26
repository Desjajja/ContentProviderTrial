package com.example.slaveapp.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ScreenState(
    val text : String?
)

class ScreenViewModel: ViewModel() {
    private var _uiState = MutableStateFlow(ScreenState(null))
    val uiState = _uiState.asStateFlow()
    fun updateText(receivedText: String?) {
        _uiState.update { currentState ->
            currentState.copy(
                text = receivedText
            )
        }
    }
}