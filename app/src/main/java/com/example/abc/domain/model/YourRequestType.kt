package com.example.abc.domain.model

data class YourRequestType(
    val category: String,           // e.g., "fruits", "nuts", "vegetables"
    val items: List<String>,        // e.g., ["Apple", "Banana", "Cherry"]
    val imageUrls: List<String>? = null  // optional, can include image URLs if needed
)
