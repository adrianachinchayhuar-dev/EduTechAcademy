package com.tecsup.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecsup.components.CategoryFilter
import com.tecsup.components.CourseCard
import com.tecsup.data.CourseData
import com.tecsup.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursesScreen(navController: NavController) {
    var selectedCategory by remember { mutableStateOf("Todos") }
    val categories = listOf("Todos", "Programación", "Diseño", "Negocios")

    val filteredCourses = if (selectedCategory == "Todos") {
        CourseData.courseList
    } else {
        CourseData.courseList.filter { it.category == selectedCategory }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Explorar Cursos") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            CategoryFilter(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredCourses) { course ->
                    CourseCard(
                        course = course,
                        onClick = {
                            navController.navigate(Screen.CourseDetail.createRoute(course.id))
                        }
                    )
                }
            }
        }
    }
}