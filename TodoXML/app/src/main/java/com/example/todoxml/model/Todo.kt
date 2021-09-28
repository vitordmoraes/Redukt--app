package com.example.todoxml.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Todo(
    @Id var id: Long = 0,
    val text: String,
    val completed: Boolean = false)

