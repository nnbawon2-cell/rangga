package com.example.myapplication

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Color Palette
val PrimaryColor = Color(0xFF4F46E5)
val PrimaryLightColor = Color(0xFFEEF2FF)
val AccentColor = Color(0xFF06B6D4)
val BackgroundColor = Color(0xFFF8FAFC)
val SurfaceColor = Color(0xFFFFFFFF)
val TextPrimary = Color(0xFF0F172A)
val TextSecondary = Color(0xFF64748B)

data class CategoryItem(
    val id: Int,
    val title: String,
    val icon: ImageVector,
    val color: Color,
)

data class TransactionItem(
    val id: Int,
    val title: String,
    val category: String,
    val amount: String,
    val date: String,
    val icon: ImageVector,
)

@Composable
fun HomeScreen(
    onNavigateToDashboard: () -> Unit = {},
    onLogout: () -> Unit = {},
) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            HomeTopBar(
                userName = "Pengguna Demo",
                onNotificationClick = {
                    Toast.makeText(context, "Tidak ada notifikasi baru", Toast.LENGTH_SHORT).show()
                },
                onProfileClick = onNavigateToDashboard
            )
        },
        bottomBar = {
            HomeBottomNavigation(
                selectedTab = selectedTab,
                onTabSelected = { index ->
                    selectedTab = index
                    if (index == 3) {
                        onNavigateToDashboard()
                    }
                }
            )
        },
        containerColor = BackgroundColor
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            // Search Bar
            SearchBarSection(
                query = searchQuery,
                onQueryChange = { searchQuery = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Balance / Wallet Card Banner
            WalletBannerSection(
                balance = "Rp 12.500.000",
                points = "1.250 Poin",
                onTopUpClick = {
                    Toast.makeText(context, "Fitur Top Up dipilih", Toast.LENGTH_SHORT).show()
                },
                onTransferClick = {
                    Toast.makeText(context, "Fitur Transfer dipilih", Toast.LENGTH_SHORT).show()
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Categories Section
            SectionHeader(title = "Kategori Layanan", actionText = "Lihat Semua") {
                Toast.makeText(context, "Lihat semua kategori", Toast.LENGTH_SHORT).show()
            }

            Spacer(modifier = Modifier.height(12.dp))

            CategoryGridSection(
                onCategoryClick = { (id, title) ->
                    Toast.makeText(context, "Kategori ($id): $title", Toast.LENGTH_SHORT).show()
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Promo Banner Section
            PromoBannerSection(
                onPromoClick = {
                    Toast.makeText(context, "Promo Cashback 50% diklaim!", Toast.LENGTH_SHORT).show()
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Recent Transactions / Activities
            SectionHeader(title = "Transaksi Terakhir", actionText = "Riwayat") {
                Toast.makeText(context, "Buka riwayat transaksi", Toast.LENGTH_SHORT).show()
            }

            Spacer(modifier = Modifier.height(12.dp))

            RecentTransactionsSection(
                onTransactionClick = { item ->
                    Toast.makeText(context, "Detail: ${item.title}", Toast.LENGTH_SHORT).show()
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Quick Logout Row
            TextButton(
                onClick = onLogout,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Keluar dari Akun",
                    color = Color(0xFFEF4444),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun HomeTopBar(
    userName: String,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceColor)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Selamat Datang 👋",
                fontSize = 12.sp,
                color = TextSecondary
            )
            Text(
                text = userName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = onNotificationClick,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(PrimaryLightColor)
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifikasi",
                    tint = PrimaryColor
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            IconButton(
                onClick = onProfileClick,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(PrimaryColor)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profil",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun SearchBarSection(
    query: String,
    onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        placeholder = {
            Text(text = "Cari layanan, transaksi...", color = TextSecondary, fontSize = 14.sp)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = TextSecondary
            )
        },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = SurfaceColor,
            unfocusedContainerColor = SurfaceColor,
            focusedBorderColor = PrimaryColor,
            unfocusedBorderColor = Color(0xFFE2E8F0)
        ),
        singleLine = true
    )
}

@Composable
fun WalletBannerSection(
    balance: String,
    points: String,
    onTopUpClick: () -> Unit,
    onTransferClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(PrimaryColor, Color(0xFF3730A3))
                    )
                )
                .padding(20.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Total Saldo Anda",
                            color = Color(0xFFE0E7FF),
                            fontSize = 12.sp
                        )
                        Text(
                            text = balance,
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Badge(
                        containerColor = Color(0xFF312E81),
                        contentColor = Color.White
                    ) {
                        Text(
                            text = points,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = onTopUpClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = PrimaryColor
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Top Up",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Isi Saldo", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }

                    Button(
                        onClick = onTransferClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0x33FFFFFF),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Transfer",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Kirim", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    actionText: String,
    onActionClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        Text(
            text = actionText,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = PrimaryColor,
            modifier = Modifier.clickable { onActionClick() }
        )
    }
}

@Composable
fun CategoryGridSection(
    onCategoryClick: (CategoryItem) -> Unit
) {
    val categories = listOf(
        CategoryItem(1, "Transfer", Icons.AutoMirrored.Filled.Send, Color(0xFF4F46E5)),
        CategoryItem(2, "Tagihan", Icons.Default.DateRange, Color(0xFF06B6D4)),
        CategoryItem(3, "Top Up", Icons.Default.Add, Color(0xFF10B981)),
        CategoryItem(4, "Belanja", Icons.Default.ShoppingCart, Color(0xFFF59E0B)),
        CategoryItem(5, "Promo", Icons.Default.Star, Color(0xFFEC4899)),
        CategoryItem(6, "Lainnya", Icons.Default.MoreVert, Color(0xFF64748B))
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        categories.take(4).forEach { category ->
            CategoryCardItem(category = category, onClick = { onCategoryClick(category) })
        }
    }

    Spacer(modifier = Modifier.height(12.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        categories.drop(4).forEach { category ->
            CategoryCardItem(category = category, onClick = { onCategoryClick(category) })
        }
    }
}

@Composable
fun CategoryCardItem(
    category: CategoryItem,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(72.dp)
            .clickable { onClick() }
            .padding(vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(category.color.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = category.icon,
                contentDescription = category.title,
                tint = category.color,
                modifier = Modifier.size(26.dp)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = category.title,
            fontSize = 12.sp,
            color = TextPrimary,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun PromoBannerSection(
    onPromoClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clickable { onPromoClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(0xFFEF4444)
                ) {
                    Text(
                        text = "PROMO SPESIAL",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Cashback s/d 50%",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Khusus transaksi pertama hari ini!",
                    color = Color(0xFF94A3B8),
                    fontSize = 12.sp
                )
            }

            Button(
                onClick = onPromoClick,
                colors = ButtonDefaults.buttonColors(containerColor = AccentColor),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(text = "Klaim", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun RecentTransactionsSection(
    onTransactionClick: (TransactionItem) -> Unit
) {
    val items = listOf(
        TransactionItem(1, "Pembelian Token PLN", "Tagihan & Listrik", "- Rp 100.000", "Hari ini, 14:20", Icons.Default.ShoppingCart),
        TransactionItem(2, "Transfer ke Budi", "Transfer Bank", "- Rp 250.000", "Kemarin, 09:15", Icons.AutoMirrored.Filled.Send),
        TransactionItem(3, "Top Up Saldo E-Wallet", "Pemasukan", "+ Rp 500.000", "12 Mei 2025", Icons.Default.Add)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onTransactionClick(item) },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(PrimaryLightColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = PrimaryColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = item.title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary
                        )
                        Text(
                            text = item.date,
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                    }

                    Text(
                        text = item.amount,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (item.amount.startsWith("+")) Color(0xFF10B981) else TextPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun HomeBottomNavigation(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = SurfaceColor,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Beranda") },
            label = { Text("Beranda", fontSize = 11.sp) }
        )
        NavigationBarItem(
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Transaksi") },
            label = { Text("Transaksi", fontSize = 11.sp) }
        )
        NavigationBarItem(
            selected = selectedTab == 2,
            onClick = { onTabSelected(2) },
            icon = { Icon(Icons.Default.Star, contentDescription = "Promo") },
            label = { Text("Promo", fontSize = 11.sp) }
        )
        NavigationBarItem(
            selected = selectedTab == 3,
            onClick = { onTabSelected(3) },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profil") },
            label = { Text("Profil", fontSize = 11.sp) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen()
    }
}
