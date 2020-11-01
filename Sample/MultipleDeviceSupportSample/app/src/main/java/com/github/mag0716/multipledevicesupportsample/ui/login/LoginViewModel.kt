package com.github.mag0716.multipledevicesupportsample.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _isProgressing = MutableLiveData<Boolean>()
    val isProgressing: LiveData<Boolean> = _isProgressing

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _isProgressing.value = true
            delay(3000)
            _isProgressing.value = false
            _loginResult.value = true
        }
    }
}