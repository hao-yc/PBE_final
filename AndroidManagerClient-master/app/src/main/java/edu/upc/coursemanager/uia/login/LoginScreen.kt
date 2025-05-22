package edu.upc.coursemanager.uia.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    onSuccess: (String, String) -> Unit,
    vm: LoginViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var uid  by remember { mutableStateOf("") }

    val enabled = name.isNotBlank() && uid.isNotBlank()      // ← fórmula arriba

    Column(Modifier.padding(24.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = uid,
            onValueChange = { uid = it },
            label = { Text("student_id") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = { vm.onLogin(name, uid) },
            enabled = enabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }
    }

    when (val st = vm.state) {
        is LoginState.Loading -> CircularProgressIndicator()
        is LoginState.Error   -> Snackbar { Text(st.msg) }
        else -> Unit
    }

    /** Si el ViewModel pasa a Idle otra vez pero con éxito,
    navega a Home (aquí simplificado): */
    if (vm.state === LoginState.Idle && !enabled) {
        onSuccess(name, uid)
    }
}
