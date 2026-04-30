package com.tecsup.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import com.tecsup.model.Course

@Composable
fun HomeScreen(
    onCoursesClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        // Header: Saludo y Avatar (Estilo Imagen)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "¡Hola, Estudiante! 👋",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )
                )
                Text(
                    text = "Estudiante Premium",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.Gray
                    )
                )
            }
            // Avatar Circular "AC"
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF7B85A1).copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "AC",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Banner Motivacional (Azul Oscuro)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2D4379)),
            shape = RoundedCornerShape(24.dp)
        ) {
            Column(modifier = Modifier.padding(28.dp)) {
                Text(
                    text = "¡Continúa aprendiendo!",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Llevas 3 cursos activos esta semana. ¡Sigue así!",
                    color = Color.White.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Sección Accesos Rápidos
        Text(
            text = "Accesos rápidos",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Grid 2x2 de Accesos Rápidos
        Row(modifier = Modifier.fillMaxWidth()) {
            QuickAccessCard(
                title = "Cursos",
                icon = "📚",
                backgroundColor = Color(0xFF5E7CB9),
                modifier = Modifier.weight(1f),
                onClick = onCoursesClick
            )
            Spacer(modifier = Modifier.width(16.dp))
            QuickAccessCard(
                title = "Mi Avance",
                icon = "📊",
                backgroundColor = Color(0xFF7B85A1),
                modifier = Modifier.weight(1f),
                onClick = onProfileClick
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            QuickAccessCard(
                title = "Certificados",
                icon = "🏆",
                backgroundColor = Color(0xFF8C7191),
                modifier = Modifier.weight(1f),
                onClick = {}
            )
            Spacer(modifier = Modifier.width(16.dp))
            QuickAccessCard(
                title = "Ajustes",
                icon = "⚙️",
                backgroundColor = Color(0xFFE5E7EE),
                textColor = Color.DarkGray,
                modifier = Modifier.weight(1f),
                onClick = {}
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Sección Cursos Recomendados
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Cursos recomendados",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = Color.Black
            )
            Text(
                text = "Ver todos",
                color = Color(0xFF2D4379),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onCoursesClick() }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Lista Horizontal de Cursos
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(CourseData.courseList.filter { it.isPopular }) { course ->
                HomeCourseCard(course, onClick = onCoursesClick)
            }
        }
        
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun QuickAccessCard(
    title: String,
    icon: String,
    backgroundColor: Color,
    textColor: Color = Color.White,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(120.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = icon, fontSize = 32.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                color = textColor,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun HomeCourseCard(course: Course, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(180.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF7B85A1).copy(alpha = 0.8f)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "🎓", fontSize = 48.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = course.title,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            maxLines = 1
        )
        Text(
            text = course.instructor,
            color = Color.Gray,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
