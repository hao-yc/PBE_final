package edu.upc.coursemanager.uia.login

import javax.inject.Inject

class LoginUseCase @Inject constructor() {
    operator fun invoke(name: String, uid: String): LoginState =
        if (name == "Juan Pérez" && uid == "0000") LoginState.Idle   // placeholder
        else LoginState.Error("Credenciales de prueba incorrectas")
}
