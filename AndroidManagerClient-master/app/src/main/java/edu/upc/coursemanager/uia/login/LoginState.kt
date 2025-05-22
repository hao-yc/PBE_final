package edu.upc.coursemanager.uia.login

/** Estados posibles de la pantalla de login */
sealed interface LoginState {
    data object Idle : LoginState        // aún no se ha tocado “Entrar”
    data object Loading : LoginState     // esperando respuesta
    data class Error(val msg: String) : LoginState
}
