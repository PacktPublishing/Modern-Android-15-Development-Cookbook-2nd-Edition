package com.madona.chapter_two.app_two

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AnimationViewModel : ViewModel() {
    private val _isRotated = MutableStateFlow(false)
    val isRotated: StateFlow<Boolean> = _isRotated

    // Function to toggle animation state
    fun toggleRotation() {
        _isRotated.value = !_isRotated.value
    }
}