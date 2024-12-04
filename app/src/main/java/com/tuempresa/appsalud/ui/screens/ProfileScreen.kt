package com.tuempresa.appsalud.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.tuempresa.appsalud.models.User

// Composable para mostrar la información personal del usuario
@Composable
fun ProfileScreen(user: User) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Título de la sección
        Text("Información Personal", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar los detalles del usuario
        ProfileInfoRow("Nombre:", user.name)
        ProfileInfoRow("Correo:", user.email)
        ProfileInfoRow("Edad:", user.age.toString())

        Spacer(modifier = Modifier.height(32.dp))

        // Botón para cerrar sesión (puedes agregar más funcionalidad)
        Button(
            onClick = { /* Acción para cerrar sesión */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Cerrar Sesión")
        }
    }
}

// Composable auxiliar para mostrar una fila de información del perfil
@Composable
fun ProfileInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyLarge)
        Text(value, style = MaterialTheme.typography.bodyMedium)
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    val sampleUser = User("Juan Pérez", "juan.perez@example.com", 30)
    ProfileScreen(user = sampleUser)
}
