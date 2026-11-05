package com.example.iauro.bookstore

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.iauro.bookstore.ui.product.ProductListUI
import com.example.iauro.bookstore.ui.theme.BookStoreTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductListUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun productListUI_displaysTitleAndSearchAndBooks() {
        composeTestRule.setContent {
            BookStoreTheme {
                ProductListUI()
            }
        }

        composeTestRule.onNodeWithText("Featured Books").assertIsDisplayed()
        composeTestRule.onNodeWithText("Search in featured books...").assertIsDisplayed()
        composeTestRule.onNodeWithText("Atomic Habits").assertIsDisplayed()
        composeTestRule.onNodeWithText("The 5 AM Club").assertIsDisplayed()
        composeTestRule.onNodeWithText("Rich Dad Poor Dad").assertIsDisplayed()
    }

    @Test
    fun productListUI_triggersBackClickAndBookClick() {
        var backClicked = false
        var selectedBookTitle = ""

        composeTestRule.setContent {
            BookStoreTheme {
                ProductListUI(
                    onBackClick = { backClicked = true },
                    onBookClick = { book -> selectedBookTitle = book.title }
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("Back").performClick()
        assertTrue(backClicked)

        composeTestRule.onNodeWithText("Atomic Habits").performClick()
        assertTrue(selectedBookTitle == "Atomic Habits")
    }

    @Test
    fun productListUI_displaysCustomTitleAndCategory() {
        composeTestRule.setContent {
            BookStoreTheme {
                ProductListUI(
                    title = "Fiction",
                    initialCategory = "Fiction"
                )
            }
        }

        composeTestRule.onNodeWithText("Fiction").assertIsDisplayed()
    }
}
