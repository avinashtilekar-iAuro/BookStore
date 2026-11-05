package com.example.iauro.bookstore

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.iauro.bookstore.ui.home.HomeUI
import com.example.iauro.bookstore.ui.login.LoginScreen
import com.example.iauro.bookstore.ui.product.ProductListUI
import com.example.iauro.bookstore.ui.theme.BookStoreTheme

enum class Screen {
    LOGIN,
    HOME,
    PRODUCT_LIST
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookStoreTheme {
                var currentScreen by remember { mutableStateOf(Screen.LOGIN) }
                var productListTitle by remember { mutableStateOf("Featured Books") }
                var productListCategory by remember { mutableStateOf("All") }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (currentScreen) {
                        Screen.LOGIN -> {
                            LoginScreen(
                                modifier = Modifier.padding(innerPadding),
                                onLoginClick = { _, _ ->
                                    Toast.makeText(
                                        this,
                                        "Welcome back!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    currentScreen = Screen.HOME
                                },
                                onGoogleSignInClick = {
                                    Toast.makeText(
                                        this,
                                        "Signed in with Google",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    currentScreen = Screen.HOME
                                },
                                onSignUpClick = {
                                    Toast.makeText(
                                        this,
                                        "Navigate to Sign Up",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            )
                        }
                        Screen.HOME -> {
                            HomeUI(
                                modifier = Modifier.padding(),
                                onCategoryClick = { category ->
                                    productListTitle = category.name
                                    productListCategory = category.name
                                    currentScreen = Screen.PRODUCT_LIST
                                },
                                onViewAllCategoriesClick = {
                                    productListTitle = "All Categories"
                                    productListCategory = "All"
                                    currentScreen = Screen.PRODUCT_LIST
                                },
                                onViewAllFeaturedClick = {
                                    productListTitle = "Featured Books"
                                    productListCategory = "All"
                                    currentScreen = Screen.PRODUCT_LIST
                                }
                            )
                        }
                        Screen.PRODUCT_LIST -> {
                            ProductListUI(
                                modifier = Modifier.padding(),
                                title = productListTitle,
                                initialCategory = productListCategory,
                                onBackClick = {
                                    currentScreen = Screen.HOME
                                },
                                onBookClick = { book ->
                                    Toast.makeText(
                                        this,
                                        "Selected: ${book.title}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                onAddToCartClick = { book ->
                                    Toast.makeText(
                                        this,
                                        "Added ${book.title} to Cart",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                onCartClick = {
                                    Toast.makeText(
                                        this,
                                        "Cart clicked",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
