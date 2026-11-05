package com.example.iauro.bookstore.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.iauro.bookstore.ui.theme.BookStoreTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: (email: String, pass: String) -> Unit = { _, _ -> },
    onGoogleSignInClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    LoginUI(
        modifier = modifier,
        onLoginClick = onLoginClick,
        onGoogleSignInClick = onGoogleSignInClick,
        onSignUpClick = onSignUpClick
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    BookStoreTheme {
        LoginScreen()
    }
}
