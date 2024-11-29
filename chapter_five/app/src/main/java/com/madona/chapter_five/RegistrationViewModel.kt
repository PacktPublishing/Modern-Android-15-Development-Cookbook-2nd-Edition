package com.madona.chapter_five

import android.util.Patterns.EMAIL_ADDRESS
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message

    private val emailPattern = Regex("^[A-Za-z0-9+_.-]+@(.+)$")

    fun onUsernameChange(newUsername: String) {
        _username.value = newUsername
    }

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun register() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(2000) // Simulate network delay
            if (username.value.isNotBlank() && emailPattern.matches(email.value)) {
                _message.value = "Registration Successful"
            } else {
                _message.value = "Invalid input"
            }
            _isLoading.value = false
        }
    }
}
