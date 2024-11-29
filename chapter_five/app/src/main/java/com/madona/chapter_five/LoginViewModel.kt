package com.madona.chapter_five

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// LoginViewModel.kt
class LoginViewModel : ViewModel() {
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message

    fun onUsernameChange(newUsername: String) {
        _username.value = newUsername
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun login() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(2000) // Simulate network delay
            if (username.value == "testuser" && password.value == "password") {
                _message.value = "Login Successful"
            } else {
                _message.value = "Invalid Credentials"
            }
            _isLoading.value = false
        }
    }
}



