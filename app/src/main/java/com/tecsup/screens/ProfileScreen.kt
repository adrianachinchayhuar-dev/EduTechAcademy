package com.tecsup.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tecsup.data.CourseData
import com.tecsup.components.ProgressBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onBackClick: () -> Unit
) {
    val enrolledCourses = CourseData.courseList.take(3)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfil y Mis Cursos") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            item {
                Text(
                    text = "Adriana Chinchayhuara",
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = "Estudiante de EduTech Academy",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Mis cursos inscritos",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            items(enrolledCourses) { course ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = course.title,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "Instructor: ${course.instructor}",
                            style = MaterialTheme.typography.bodySmall
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        ProgressBar(
                            progress = course.progress,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}