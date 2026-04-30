package com.tecsup.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onCoursesClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
        ) {
            Text(
                text = "Hola, Adriana",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Continúa aprendiendo con EduTech Academy",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(32.dp))

            DashboardCard(
                title = "Explorar Cursos",
                description = "Encuentra cursos de programación, diseño y negocios.",
                icon = Icons.Default.School,
                onClick = onCoursesClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            DashboardCard(
                title = "Mis Cursos",
                description = "Revisa tus cursos inscritos y tu avance.",
                icon = Icons.Default.Star,
                onClick = onProfileClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            DashboardCard(
                title = "Perfil",
                description = "Gestiona tu información de estudiante.",
                icon = Icons.Default.Person,
                onClick = onProfileClick
            )
        }
    }
}

@Composable
fun DashboardCard(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}