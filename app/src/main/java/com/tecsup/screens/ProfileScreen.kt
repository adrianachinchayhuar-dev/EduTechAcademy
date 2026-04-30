// Pantalla: ProfileScreen.kt
// Mejoras: Estadísticas en tarjetas, barra de progreso animada, logros, diseño premium y navegación de regreso.
package com.tecsup.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsup.data.CourseData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onBackClick: () -> Unit) {
    val enrolledCourses = CourseData.courseList.take(3)

    Scaffold(
        containerColor = Color.White,
        topBar = {
            // MEJORA: Se añade TopAppBar para permitir el regreso al Home de forma estética
            TopAppBar(
                title = { Text("Mi Perfil", fontWeight = FontWeight.Bold, color = Color(0xFF2D4379)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color(0xFF2D4379)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            // Header Avatar e Info (Estilo Imagen)
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF7B85A1).copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("👤", fontSize = 48.sp)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Estudiante",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "estudiante@edutech.com",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    Surface(
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color(0xFFE5E7EE),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Miembro desde Enero 2024",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF2D4379)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(32.dp))

                // Estadísticas en tarjetas (Estilo Accesos Rápidos)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatCard("3", "Cursos", Modifier.weight(1f))
                    StatCard("45h", "Estudio", Modifier.weight(1f))
                    StatCard("1", "Logro", Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(32.dp))
                
                Text(
                    text = "Mis Cursos en Progreso",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Lista con progreso animado
            items(enrolledCourses) { course ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F9FD)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFF7B85A1).copy(alpha = 0.8f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("📚", fontSize = 24.sp)
                        }
                        
                        Spacer(modifier = Modifier.width(16.dp))
                        
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = course.title, 
                                fontWeight = FontWeight.Bold, 
                                style = MaterialTheme.typography.titleSmall,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            val animatedProgress = animateFloatAsState(
                                targetValue = course.progress,
                                label = "progressAnimation"
                            ).value
                            
                            LinearProgressIndicator(
                                progress = { animatedProgress },
                                modifier = Modifier.fillMaxWidth().height(8.dp),
                                color = Color(0xFF2D4379),
                                trackColor = Color(0xFFE5E7EE),
                                strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${(course.progress * 100).toInt()}% completado",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFF2D4379)
                            )
                        }
                    }
                }
            }

            // Logros / Insignias
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Tus Logros", 
                    style = MaterialTheme.typography.titleLarge, 
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    AchievementBadge("🥇", "Primero")
                    AchievementBadge("🔥", "Racha")
                    AchievementBadge("🎓", "Graduado")
                }

                Spacer(modifier = Modifier.height(40.dp))
                
                // Botón Cerrar Sesión Estilizado
                Button(
                    onClick = onBackClick,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2D4379).copy(alpha = 0.1f),
                        contentColor = Color(0xFF2D4379)
                    ),
                    elevation = null
                ) {
                    Icon(Icons.AutoMirrored.Filled.Logout, null)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Cerrar Sesión", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun StatCard(value: String, label: String, modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F9FD))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D4379)
            )
            Text(
                text = label, 
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun AchievementBadge(emoji: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color(0xFFE5E7EE)),
            contentAlignment = Alignment.Center
        ) {
            Text(emoji, fontSize = 32.sp)
        }
        Text(
            text = label, 
            style = MaterialTheme.typography.labelSmall, 
            modifier = Modifier.padding(top = 8.dp),
            color = Color.DarkGray
        )
    }
}
