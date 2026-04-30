package com.tecsup.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsup.data.CourseData
import com.tecsup.model.Course

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
    onExploreClick: () -> Unit
) {
    // Simulamos cursos inscritos
    val enrolledCourses = remember { CourseData.courseList.filter { it.progress > 0 } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Atrás")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            // MEJORA 1 & 2: Header con foto e Info usuario
            item {
                Spacer(modifier = Modifier.height(16.dp))
                ProfileHeaderSection()
                Spacer(modifier = Modifier.height(24.dp))
            }

            // MEJORA 3: Estadísticas (Tarjetas horizontales)
            item {
                StatsSection()
                Spacer(modifier = Modifier.height(32.dp))
            }

            // MEJORA 4: Mis Cursos
            item {
                Text(
                    text = "Mis Cursos",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (enrolledCourses.isEmpty()) {
                // MEJORA 5: Estado vacío
                item {
                    EmptyProfileState(onExploreClick)
                }
            } else {
                items(enrolledCourses) { course ->
                    EnrolledCourseItem(course)
                }
            }

            // MEJORA 6: Sección "Logros/Insignias"
            item {
                Spacer(modifier = Modifier.height(32.dp))
                BadgesSection()
            }

            // MEJORA 7: Botón "Cerrar sesión"
            item {
                Spacer(modifier = Modifier.height(40.dp))
                OutlinedButton(
                    onClick = { /* Logout */ },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.error),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ExitToApp, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cerrar sesión")
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun ProfileHeaderSection() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // MEJORA 1: Foto/Avatar circular
        Surface(
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("👤", fontSize = 40.sp)
            }
        }
        Spacer(modifier = Modifier.width(20.dp))
        Column {
            // MEJORA 2: Info usuario
            Text(
                text = "Adriana Chinchayhuara",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(text = "estudiante@edutech.com", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Miembro desde Enero 2024",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun StatsSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard("3", "Cursos", Modifier.weight(1f))
        StatCard("45h", "Estudio", Modifier.weight(1f))
        StatCard("1", "Certificado", Modifier.weight(1f))
    }
}

@Composable
fun StatCard(value: String, label: String, modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Text(text = label, style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
fun EnrolledCourseItem(course: Course) {
    // MEJORA 4: Barra de progreso animada
    val animatedProgress by animateFloatAsState(
        targetValue = course.progress / 100f,
        animationSpec = tween(1000),
        label = "progress"
    )

    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            // Imagen pequeña
            Box(
                modifier = Modifier.size(60.dp).background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("📚")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = course.title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                LinearProgressIndicator(
                    progress = { animatedProgress },
                    modifier = Modifier.fillMaxWidth().height(6.dp).clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${course.progress}% completado", style = MaterialTheme.typography.labelSmall)
            }
            IconButton(onClick = { /* Navegar */ }) {
                Icon(Icons.Default.PlayArrow, null, tint = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Composable
fun BadgesSection() {
    Column {
        Text(text = "Insignias", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            BadgeItem("🎯", "Iniciado")
            BadgeItem("🔥", "Racha 5")
            BadgeItem("💎", "Premium")
        }
    }
}

@Composable
fun BadgeItem(emoji: String, name: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(modifier = Modifier.size(56.dp), shape = CircleShape, color = Color(0xFFF5F5F5)) {
            Box(contentAlignment = Alignment.Center) { Text(emoji, fontSize = 24.sp) }
        }
        Text(text = name, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun EmptyProfileState(onExploreClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("📭", fontSize = 64.sp)
        Text("Aún no tienes cursos inscritos", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onExploreClick) { Text("Explorar cursos") }
    }
}