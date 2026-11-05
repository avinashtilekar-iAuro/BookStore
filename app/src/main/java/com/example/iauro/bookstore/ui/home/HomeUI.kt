package com.example.iauro.bookstore.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.iauro.bookstore.R
import com.example.iauro.bookstore.data.BookItem
import com.example.iauro.bookstore.data.CategoryItem
import com.example.iauro.bookstore.ui.theme.BlackColor
import com.example.iauro.bookstore.ui.theme.BorderColor
import com.example.iauro.bookstore.ui.theme.BookStoreTheme
import com.example.iauro.bookstore.ui.theme.NavyTitle
import com.example.iauro.bookstore.ui.theme.PrimaryBlue
import com.example.iauro.bookstore.ui.theme.SecondarySlate
import com.example.iauro.bookstore.utility.DummyDataProvider.DummyCategoriesWithIcon
import com.example.iauro.bookstore.utility.DummyDataProvider.DummyFeaturedBooks


@Composable
fun HomeUI(
    modifier: Modifier = Modifier,
    categories: List<CategoryItem> = DummyCategoriesWithIcon,
    featuredBooks: List<BookItem> = DummyFeaturedBooks,
    onSearchClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onCategoryClick: (CategoryItem) -> Unit = {},
    onBookClick: (BookItem) -> Unit = {},
    onShopNowClick: () -> Unit = {},
    onViewAllCategoriesClick: () -> Unit = {},
    onViewAllFeaturedClick: () -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedNavIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            HomeBottomNavigation(
                selectedIndex = selectedNavIndex,
                onItemSelected = { selectedNavIndex = it }
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // Store Top Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Brand Title & Symbol
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookstore_symbol),
                        contentDescription = "BookStore Symbol",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    SpanStyle(
                                        color = NavyTitle,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp
                                    )
                                ) {
                                    append("Book")
                                }
                                withStyle(
                                    SpanStyle(
                                        color = PrimaryBlue,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp
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

                }

                // Header Action Icons
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Box(modifier = Modifier.clickable { onCartClick() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cart),
                            contentDescription = "Cart",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(26.dp)
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(16.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFF3036)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "2",
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                placeholder = {
                    Text(
                        text = "Search for books, authors, categories...",
                        color = SecondarySlate,
                        fontSize = 14.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search Icon",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp)
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(13.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    unfocusedBorderColor = BorderColor,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = NavyTitle,
                    unfocusedTextColor = NavyTitle
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Horizontal Banner List (5 items)
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(end = 16.dp)
            ) {
                items(5) {
                    BannerView(
                        modifier = Modifier.width(340.dp),
                        onShopNowClick = onShopNowClick
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Categories Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Categories",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyTitle
                )
                Text(
                    text = "View All",
                    fontSize = 14.sp,
                    color = PrimaryBlue,
                    modifier = Modifier.clickable { onViewAllCategoriesClick() }
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(end = 16.dp)
            ) {
                items(categories) { category ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(64.dp)
                            .clickable { onCategoryClick(category) }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(category.backgroundColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = category.iconRes),
                                contentDescription = category.name,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = category.name,
                            fontSize = 12.sp,
                            color = NavyTitle,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Featured Books Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Featured Books",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyTitle
                )
                Text(
                    text = "View All",
                    fontSize = 14.sp,
                    color = PrimaryBlue,
                    modifier = Modifier.clickable { onViewAllFeaturedClick() }
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                contentPadding = PaddingValues(end = 16.dp)
            ) {
                items(featuredBooks) { book ->
                    Card(
                        modifier = Modifier
                            .width(130.dp)
                            .clickable { onBookClick(book) },
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFCFEFF)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = book.coverRes),
                                contentDescription = book.title,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(140.dp)
                                    .clip(RoundedCornerShape(6.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = book.title,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = NavyTitle,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                text = book.author,
                                fontSize = 11.sp,
                                color = SecondarySlate,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = book.price,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = NavyTitle
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_star),
                                    contentDescription = "Rating Star",
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "(${book.rating})",
                                    fontSize = 11.sp,
                                    color = SecondarySlate
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun BannerView(
    modifier: Modifier = Modifier,
    onShopNowClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF051C45), Color(0xFF004064))
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1.2f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp
                            )
                        ) {
                            append("Good Books\nBuild Better ")
                        }
                        withStyle(
                            SpanStyle(
                                color = Color(0xFFFFD000),
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp
                            )
                        ) {
                            append("Tomorrows")
                        }
                    },
                    lineHeight = 26.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Explore. Read. Be Inspired.",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .clickable { onShopNowClick() }
                        .padding(horizontal = 18.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Shop Now",
                        color = NavyTitle,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Image(
                painter = painterResource(id = R.drawable.img_banner_books),
                contentDescription = "Banner Artwork",
                modifier = Modifier
                    .weight(0.9f)
                    .height(160.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun HomeBottomNavigation(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val navItems = listOf(
        Triple("Home", R.drawable.ic_home, false),
        Triple("Categories", R.drawable.ic_grid, false),
        Triple("Cart", R.drawable.ic_cart, true),
        Triple("Profile", R.drawable.ic_user, false)
    )

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        border = BorderStroke(width = (0.5).dp, color = Color(0xFFE4EDF6))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .height(64.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navItems.forEachIndexed { index, (label, iconRes, hasBadge) ->
                val isSelected = selectedIndex == index
                val tintColor = if (isSelected) PrimaryBlue else SecondarySlate

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onItemSelected(index) }
                ) {
                    Box {
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = label,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                        if (hasBadge) {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFFF3036)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "2",
                                    color = Color.White,
                                    fontSize = 8.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = label,
                        fontSize = 11.sp,
                        color = tintColor,
                        fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeUIPreview() {
    BookStoreTheme {
        HomeUI()
    }
}
