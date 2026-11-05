package com.example.iauro.bookstore.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.iauro.bookstore.R
import com.example.iauro.bookstore.ui.theme.BlackColor
import com.example.iauro.bookstore.ui.theme.BorderColor
import com.example.iauro.bookstore.ui.theme.BookStoreTheme
import com.example.iauro.bookstore.ui.theme.DividerColor
import com.example.iauro.bookstore.ui.theme.GoogleBorderColor
import com.example.iauro.bookstore.ui.theme.HeaderBackground
import com.example.iauro.bookstore.ui.theme.NavyTitle
import com.example.iauro.bookstore.ui.theme.PrimaryBlue
import com.example.iauro.bookstore.ui.theme.SecondarySlate

private val CurvedBottomShape = GenericShape { size, _ ->
    moveTo(0f, 0f)
    lineTo(size.width, 0f)
    lineTo(size.width, size.height - 40f)
    quadraticTo(
        size.width / 2f, size.height + 40f,
        0f, size.height - 40f
    )
    close()
}

private val CurvedRightShape = GenericShape { size, _ ->
    moveTo(0f, 0f)
    lineTo(size.width - 40f, 0f)
    quadraticTo(
        size.width + 40f, size.height / 2f,
        size.width - 40f, size.height
    )
    lineTo(0f, size.height)
    close()
}

@Composable
fun LoginUI(
    modifier: Modifier = Modifier,
    onLoginClick: (email: String, pass: String) -> Unit = { _, _ -> },
    onGoogleSignInClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    var emailOrPhone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    // Retrieve the WindowAdaptiveInfo to get current WindowSizeClass
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val windowSizeClass = adaptiveInfo.windowSizeClass
    val isExpanded = windowSizeClass.isWidthAtLeastBreakpoint(600)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        if (isExpanded) {
            // Adaptive Side-by-Side Layout for Medium / Expanded Screens (Tablets, Foldables, Landscape)
            Row(modifier = Modifier.fillMaxSize()) {
                // Left Pane: Brand Header & Illustration
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(CurvedRightShape)
                        .background(HeaderBackground),
                    contentAlignment = Alignment.Center
                ) {
                    HeaderContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                    )
                }

                // Right Pane: Login Form
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .widthIn(max = 440.dp)
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 32.dp, vertical = 24.dp)
                    ) {
                        LoginFormContent(
                            emailOrPhone = emailOrPhone,
                            onEmailChange = { emailOrPhone = it },
                            password = password,
                            onPasswordChange = { password = it },
                            isPasswordVisible = isPasswordVisible,
                            onTogglePasswordVisibility = { isPasswordVisible = !isPasswordVisible },
                            onLoginClick = { onLoginClick(emailOrPhone, password) },
                            onGoogleSignInClick = onGoogleSignInClick,
                            onSignUpClick = onSignUpClick
                        )
                    }
                }
            }
        } else {
            // Compact Layout for Mobile Phones in Portrait
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // Top Header Section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(340.dp)
                        .clip(CurvedBottomShape)
                        .background(HeaderBackground)
                ) {
                    HeaderContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 36.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Form Content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {
                    LoginFormContent(
                        emailOrPhone = emailOrPhone,
                        onEmailChange = { emailOrPhone = it },
                        password = password,
                        onPasswordChange = { password = it },
                        isPasswordVisible = isPasswordVisible,
                        onTogglePasswordVisibility = { isPasswordVisible = !isPasswordVisible },
                        onLoginClick = { onLoginClick(emailOrPhone, password) },
                        onGoogleSignInClick = onGoogleSignInClick,
                        onSignUpClick = onSignUpClick
                    )
                }
            }
        }
    }
}

@Composable
private fun HeaderContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Bookstore Symbol & Brand Title
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bookstore_symbol),
                contentDescription = "Bookstore Logo",
                tint = Color.Unspecified,
                modifier = Modifier.size(width = 46.dp, height = 36.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = NavyTitle,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                    ) {
                        append("Book")
                    }
                    withStyle(
                        SpanStyle(
                            color = PrimaryBlue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                    ) {
                        append("Store")
                    }
                    withStyle(
                        SpanStyle(
                            color = BlackColor,
                            fontSize = 12.sp
                        )
                    ) {
                        append("\nThe iAuro product")
                    }
                },
                lineHeight = 0.9.em
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Read More  ·  Grow More",
            color = SecondarySlate,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Reading Illustration
        Image(
            painter = painterResource(id = R.drawable.img_reading_illustration),
            contentDescription = "Reading Illustration",
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(180.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
private fun LoginFormContent(
    emailOrPhone: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    onLoginClick: () -> Unit,
    onGoogleSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    // Welcome Header
    Text(
        text = "Welcome Back!",
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color = NavyTitle
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
        text = "Sign in to continue your book journey",
        fontSize = 15.sp,
        color = SecondarySlate
    )

    Spacer(modifier = Modifier.height(24.dp))

    // Email or Mobile Field
    OutlinedTextField(
        value = emailOrPhone,
        onValueChange = onEmailChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        placeholder = {
            Text(
                text = "Email or Mobile Number",
                color = SecondarySlate,
                fontSize = 14.sp
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_mail),
                contentDescription = "Email Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryBlue,
            unfocusedBorderColor = BorderColor,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedTextColor = NavyTitle,
            unfocusedTextColor = NavyTitle
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Password Field
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        placeholder = {
            Text(
                text = "Password",
                color = SecondarySlate,
                fontSize = 14.sp
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_lock),
                contentDescription = "Password Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
        },
        trailingIcon = {
            IconButton(onClick = onTogglePasswordVisibility) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_eye),
                    contentDescription = if (isPasswordVisible) "Hide Password" else "Show Password",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        singleLine = true,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryBlue,
            unfocusedBorderColor = BorderColor,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedTextColor = NavyTitle,
            unfocusedTextColor = NavyTitle
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )
    )

    Spacer(modifier = Modifier.height(24.dp))

    // Login Button
    Button(
        onClick = onLoginClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryBlue,
            contentColor = Color.White
        )
    ) {
        Text(
            text = "Login",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Alternative Divider ("OR")
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = DividerColor,
            thickness = 1.dp
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 14.dp),
            color = SecondarySlate,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = DividerColor,
            thickness = 1.dp
        )
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Continue with Google Button
    OutlinedButton(
        onClick = onGoogleSignInClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, GoogleBorderColor)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google_logo),
                contentDescription = "Google Logo",
                tint = Color.Unspecified,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Continue with Google",
                color = NavyTitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }

    Spacer(modifier = Modifier.height(28.dp))

    // Create account prompt / Footer
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = SecondarySlate, fontSize = 14.sp)) {
                    append("Don’t have an account?  ")
                }
                withStyle(
                    style = SpanStyle(
                        color = PrimaryBlue,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                ) {
                    append("Sign Up")
                }
            },
            modifier = Modifier.clickable { onSignUpClick() }
        )
    }

    Spacer(modifier = Modifier.height(24.dp))
}

@Preview(name = "Phone Portrait", device = Devices.PHONE, showBackground = true)
@Preview(name = "Foldable Unfolded", device = Devices.FOLDABLE, showBackground = true)
@Preview(name = "Tablet Landscape", device = Devices.TABLET, showBackground = true)
@Composable
fun LoginUIAdaptivePreview() {
    BookStoreTheme {
        LoginUI()
    }
}
