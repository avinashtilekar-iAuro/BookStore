package com.example.iauro.bookstore

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.iauro.bookstore.ui.home.HomeUI
import com.example.iauro.bookstore.ui.theme.BookStoreTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeUI_displaysHeaderAndSearchAndCategories() {
        composeTestRule.setContent {
            BookStoreTheme {
                HomeUI()
            }
        }

//        composeTestRule.onNodeWithText("BookStore").assertIsDisplayed()
        composeTestRule.onNodeWithText("Search for books, authors, categories...").assertIsDisplayed()
//        composeTestRule.onNodeWithText("Good Books\nBuild Better Tomorrows").assertIsDisplayed()
        composeTestRule.onNodeWithText("Categories").assertIsDisplayed()
        composeTestRule.onNodeWithText("Fiction").assertIsDisplayed()
        composeTestRule.onNodeWithText("Non-Fiction").assertIsDisplayed()
        composeTestRule.onNodeWithText("Featured Books").assertIsDisplayed()
        composeTestRule.onNodeWithText("Atomic Habits").assertIsDisplayed()
        composeTestRule.onNodeWithText("Home").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cart").assertIsDisplayed()
    }

    @Test
    fun homeUI_triggersCategoryAndBookSelectionCallbacks() {
        var clickedCategoryName = ""
        var clickedBookTitle = ""

        composeTestRule.setContent {
            BookStoreTheme {
                HomeUI(
                    onCategoryClick = { category -> clickedCategoryName = category.name },
                    onBookClick = { book -> clickedBookTitle = book.title }
                )
            }
        }

        composeTestRule.onNodeWithText("Fiction").performClick()
        assertTrue(clickedCategoryName == "Fiction")

        composeTestRule.onNodeWithText("Atomic Habits").performClick()
        assertTrue(clickedBookTitle == "Atomic Habits")
    }

    @Test
    fun homeUI_triggersViewAllFeaturedCallback() {
        var viewAllClicked = false

        composeTestRule.setContent {
            BookStoreTheme {
                HomeUI(
                    onViewAllFeaturedClick = { viewAllClicked = true }
                )
            }
        }

        composeTestRule.onAllNodesWithText("View All").onLast().performClick()
        assertTrue(viewAllClicked)
    }
}
