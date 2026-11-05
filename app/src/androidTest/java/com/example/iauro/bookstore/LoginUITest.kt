package com.example.iauro.bookstore

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.iauro.bookstore.ui.login.LoginUI
import com.example.iauro.bookstore.ui.theme.BookStoreTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginUI_displaysAllKeyElements() {
        composeTestRule.setContent {
            BookStoreTheme {
                LoginUI()
            }
        }

        composeTestRule.onNodeWithText("Welcome Back!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sign in to continue your book journey").assertIsDisplayed()
        composeTestRule.onNodeWithText("Email or Mobile Number").assertIsDisplayed()
        composeTestRule.onNodeWithText("Password").assertIsDisplayed()
        composeTestRule.onNodeWithText("Login").assertIsDisplayed()
        composeTestRule.onNodeWithText("OR").assertIsDisplayed()
        composeTestRule.onNodeWithText("Continue with Google").assertIsDisplayed()
    }

    @Test
    fun loginUI_allowsEnteringCredentialsAndSubmitting() {
        var submittedEmail = ""
        var submittedPassword = ""

        composeTestRule.setContent {
            BookStoreTheme {
                LoginUI(
                    onLoginClick = { email, pass ->
                        submittedEmail = email
                        submittedPassword = pass
                    }
                )
            }
        }

        composeTestRule.onNodeWithText("Email or Mobile Number")
            .performTextInput("user@example.com")
        composeTestRule.onNodeWithText("Password")
            .performTextInput("secret123")

        composeTestRule.onNodeWithText("Login").performClick()

        assertEquals("user@example.com", submittedEmail)
        assertEquals("secret123", submittedPassword)
    }

    @Test
    fun loginUI_triggersGoogleSignInAndSignUpCallbacks() {
        var googleClicked = false
        var signUpClicked = false

        composeTestRule.setContent {
            BookStoreTheme {
                LoginUI(
                    onGoogleSignInClick = { googleClicked = true },
                    onSignUpClick = { signUpClicked = true }
                )
            }
        }

        composeTestRule.onNodeWithText("Continue with Google").performClick()
        assertTrue(googleClicked)

        composeTestRule.onNodeWithText("Don’t have an account? Sign Up", substring = true).performClick()
        assertTrue(signUpClicked)
    }
}
