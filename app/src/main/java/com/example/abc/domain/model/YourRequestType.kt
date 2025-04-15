package com.example.abc.domain.model

data class YourRequestType(
    val category: String,
    val items: List<String>,
    val imageUrls: List<String>? = null
)
