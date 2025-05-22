package edu.upc.coursemanager.uia.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase    // ← lo definiremos en domain
) : ViewModel() {

    var state: LoginState = LoginState.Idle
        private set

    fun onLogin(name: String, uid: String) = viewModelScope.launch {
        state = LoginState.Loading
        state = loginUseCase(name, uid)
    }
}
