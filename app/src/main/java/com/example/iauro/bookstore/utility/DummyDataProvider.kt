package com.example.iauro.bookstore.utility

import androidx.compose.ui.graphics.Color
import com.example.iauro.bookstore.R
import com.example.iauro.bookstore.data.BookItem
import com.example.iauro.bookstore.data.CategoryItem

object DummyDataProvider {

    val DummyProductList = listOf(
        BookItem(
            id = "1",
            title = "Atomic Habits",
            author = "James Clear",
            price = "₹ 399",
            rating = "4.6",
            coverRes = R.drawable.img_book_atomic_habits
        ),
        BookItem(
            id = "2",
            title = "The 5 AM Club",
            author = "Robin Sharma",
            price = "₹ 349",
            rating = "4.5",
            coverRes = R.drawable.img_book_5am_club
        ),
        BookItem(
            id = "3",
            title = "Rich Dad Poor Dad",
            author = "Robert T. Kiyosaki",
            price = "₹ 399",
            rating = "4.4",
            coverRes = R.drawable.img_book_rich_dad
        ),
        BookItem(
            id = "4",
            title = "Deep Work",
            author = "Cal Newport",
            price = "₹ 420",
            rating = "4.7",
            coverRes = R.drawable.img_book_atomic_habits
        ),
        BookItem(
            id = "5",
            title = "The Psychology of Money",
            author = "Morgan Housel",
            price = "₹ 299",
            rating = "4.8",
            coverRes = R.drawable.img_book_5am_club
        ),
        BookItem(
            id = "6",
            title = "Think and Grow Rich",
            author = "Napoleon Hill",
            price = "₹ 250",
            rating = "4.3",
            coverRes = R.drawable.img_book_rich_dad
        ),
        BookItem(
            id = "7",
            title = "Man's Search for Meaning",
            author = "Viktor E. Frankl",
            price = "₹ 280",
            rating = "4.9",
            coverRes = R.drawable.img_book_atomic_habits
        ),
        BookItem(
            id = "8",
            title = "Can't Hurt Me",
            author = "David Goggins",
            price = "₹ 450",
            rating = "4.7",
            coverRes = R.drawable.img_book_5am_club
        )
    )

    val DummyProductCategories = listOf("All", "Fiction", "Non-Fiction", "Kids", "Self Help", "Business", "Education")

    val DummyFeaturedBooks = listOf(
        BookItem(
            "1",
            "Atomic Habits",
            "James Clear",
            "₹ 399",
            "4.6",
            R.drawable.img_book_atomic_habits
        ),
        BookItem("2", "The 5 Am Club", "Robin Sharma", "₹ 349", "4.5", R.drawable.img_book_5am_club),
        BookItem(
            "3",
            "Rich Dad Poor Dad",
            "Robert T. Kiyosaki",
            "₹ 399",
            "4.4",
            R.drawable.img_book_rich_dad
        ),
        BookItem(
            "1",
            "Atomic Habits",
            "James Clear",
            "₹ 399",
            "4.6",
            R.drawable.img_book_atomic_habits
        ),
        BookItem("2", "The 5 Am Club", "Robin Sharma", "₹ 349", "4.5", R.drawable.img_book_5am_club),
        BookItem(
            "3",
            "Rich Dad Poor Dad",
            "Robert T. Kiyosaki",
            "₹ 399",
            "4.4",
            R.drawable.img_book_rich_dad
        )

    )

    val DummyCategoriesWithIcon = listOf(
        CategoryItem("1", "Fiction", Color(0xFFE2F2FF), R.drawable.ic_bookstore_symbol),
        CategoryItem("2", "Non-Fiction", Color(0xFFE4F7F2), R.drawable.ic_grid),
        CategoryItem("3", "Kids", Color(0xFFFFEBF2), R.drawable.ic_user),
        CategoryItem("4", "Education", Color(0xFFF1EDFF), R.drawable.ic_bookstore_symbol),
        CategoryItem("5", "Self Help", Color(0xFFFFF5DE), R.drawable.ic_grid),
        CategoryItem("6", "Fiction", Color(0xFFE2F2FF), R.drawable.ic_bookstore_symbol),
        CategoryItem("6", "Non-Fiction", Color(0xFFE4F7F2), R.drawable.ic_grid),
        CategoryItem("8", "Kids", Color(0xFFFFEBF2), R.drawable.ic_user),
        CategoryItem("9", "Education", Color(0xFFF1EDFF), R.drawable.ic_bookstore_symbol),
        CategoryItem("10", "Self Help", Color(0xFFFFF5DE), R.drawable.ic_grid)
    )
}