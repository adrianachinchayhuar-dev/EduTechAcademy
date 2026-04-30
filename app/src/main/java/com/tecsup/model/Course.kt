package com.tecsup.model

data class Course(
    val id: Int,
    val title: String,
    val instructor: String,
    val level: String,
    val category: String,
    val description: String,
    val duration: String,
    val imageRes: Int,
    val progress: Int = 0,
    val isPopular: Boolean = false,
    val isNew: Boolean = false
)