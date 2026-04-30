package com.tecsup.data

import com.tecsup.model.Course

object CourseData {
    val courseList = listOf(
        Course(
            id = 1,
            title = "Kotlin desde Cero",
            instructor = "Ana García",
            level = "Básico",
            category = "Programación",
            description = "Aprende Kotlin desde cero para desarrollar apps Android profesionales.",
            duration = "20 horas",
            imageRes = 0,
            isPopular = true
        ),
        Course(
            id = 2,
            title = "Jetpack Compose Avanzado",
            instructor = "Carlos López",
            level = "Avanzado",
            category = "Programación",
            description = "Domina Jetpack Compose y crea UI modernas y declarativas.",
            duration = "25 horas",
            imageRes = 0
        ),
        Course(
            id = 3,
            title = "Diseño UI/UX",
            instructor = "María Díaz",
            level = "Intermedio",
            category = "Diseño",
            description = "Aprende los principios del diseño de interfaces y experiencia de usuario.",
            duration = "15 horas",
            imageRes = 0,
            isNew = true
        ),
        Course(
            id = 4,
            title = "Marketing Digital",
            instructor = "Pedro Gómez",
            level = "Básico",
            category = "Negocios",
            description = "Estrategias de marketing digital para hacer crecer tu negocio.",
            duration = "18 horas",
            imageRes = 0
        ),
        Course(
            id = 5,
            title = "Figma para Principiantes",
            instructor = "Laura Sánchez",
            level = "Básico",
            category = "Diseño",
            description = "Diseña prototipos y interfaces profesionales con Figma.",
            duration = "12 horas",
            imageRes = 0
        ),
        Course(
            id = 6,
            title = "Gestión de Proyectos",
            instructor = "Roberto Ruiz",
            level = "Intermedio",
            category = "Negocios",
            description = "Metodologías ágiles y gestión de equipos de trabajo.",
            duration = "22 horas",
            imageRes = 0,
            isPopular = true
        )
    )
}