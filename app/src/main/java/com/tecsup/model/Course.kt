package com.tecsup.model

data class Course(
    val id: Int,
    val title: String,
    val instructor: String,
    val level: String,
    val category: String,
    val description: String,
    val duration: String,
    val progress: Float,
    val image: Int,
    val isPopular: Boolean = false,
    val isNew: Boolean = false
)