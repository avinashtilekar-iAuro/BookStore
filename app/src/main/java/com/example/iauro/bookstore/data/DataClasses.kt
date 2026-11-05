package com.example.iauro.bookstore.data

import androidx.compose.ui.graphics.Color

data class CategoryItem(
    val id: String,
    val name: String,
    val backgroundColor: Color,
    val iconRes: Int
)

data class BookItem(
    val id: String,
    val title: String,
    val author: String,
    val price: String,
    val rating: String,
    val coverRes: Int
)