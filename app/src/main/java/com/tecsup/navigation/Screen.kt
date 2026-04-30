package com.tecsup.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")

    object Register : Screen("register")
    object Home : Screen("home")
    object Courses : Screen("courses")
    object Profile : Screen("profile")

    object CourseDetail : Screen("course_detail/{courseId}") {
        fun createRoute(courseId: Int): String {
            return "course_detail/$courseId"
        }
        fun passId(courseId: Int): String {
            return "course_detail/$courseId"
        }
    }
}