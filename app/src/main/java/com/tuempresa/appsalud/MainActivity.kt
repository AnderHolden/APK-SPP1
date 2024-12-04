package com.tuempresa.appsalud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.tuempresa.appsalud.ui.theme.AppSaludTheme
import androidx.compose.foundation.Image

// Clase User para la información del usuario
data class User(val name: String, val email: String, val age: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppSaludTheme {
                AppNavigation()  // Aquí es donde navegamos entre las pantallas
            }
        }
    }
}

@Composable
fun AppNavigation() {
    var showLogin by remember { mutableStateOf(true) }
    var isLoggedIn by remember { mutableStateOf(false) }

    if (isLoggedIn) {
        // Mostrar la pantalla del perfil cuando el usuario esté logueado
        ProfileScreen(user = User("Juan Pérez", "juan.perez@example.com", 30))
    } else {
        // Mostrar la pantalla de login o registro
        if (showLogin) {
            LoginScreen(
                onRegisterClick = { showLogin = false },
                onLoginSuccess = { isLoggedIn = true }  // Cuando el login es exitoso, se cambia el estado
            )
        } else {
            RegisterScreen(onRegisterSuccess = { showLogin = true })
        }
    }
}

@Composable
fun LoginScreen(onRegisterClick: () -> Unit, onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var loginMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.logo_disena_salud),
            contentDescription = "Logo Diseña Tu Salud",
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Campo de correo
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de contraseña
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de inicio de sesión
        Button(
            onClick = {
                loginMessage = if (email.text == "usuario@ejemplo.com" && password.text == "123456") {
                    onLoginSuccess()  // Llamamos al callback cuando el login es exitoso
                    "Inicio de sesión exitoso"
                } else {
                    "Correo o contraseña incorrectos"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mensaje de resultado
        if (loginMessage.isNotEmpty()) {
            Text(loginMessage)
        }

        // Botón para ir a Registro
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { onRegisterClick() }) {
            Text("¿No tienes cuenta? Regístrate aquí")
        }
    }
}

@Composable
fun RegisterScreen(onRegisterSuccess: () -> Unit) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    var registerMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top // Cambiado a Top para colocar el logo arriba
    ) {
        // Logo en la parte superior
        Spacer(modifier = Modifier.height(16.dp)) // Espaciado inicial
        Image(
            painter = painterResource(id = R.drawable.logo_disena_salud),
            contentDescription = "Logo Diseña Tu Salud",
            modifier = Modifier
                .size(150.dp) // Tamaño del logo
                .align(Alignment.CenterHorizontally) // Centrar horizontalmente
        )

        Spacer(modifier = Modifier.height(32.dp)) // Espaciado debajo del logo

        // Campo de nombre
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de correo
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de contraseña
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de confirmar contraseña
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de registro
        Button(
            onClick = {
                if (password.text == confirmPassword.text && password.text.isNotEmpty()) {
                    registerMessage = "Registro exitoso"
                    onRegisterSuccess()
                } else {
                    registerMessage = "Las contraseñas no coinciden o están vacías"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mensaje de resultado
        if (registerMessage.isNotEmpty()) {
            Text(registerMessage)
        }
    }
}

@Composable
fun ProfileScreen(user: User) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Nombre: ${user.name}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Correo: ${user.email}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Edad: ${user.age}", style = MaterialTheme.typography.bodyLarge)
    }
}
