package com.example.fabfit.presentation.Navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorDirection
import com.example.bottombar.model.IndicatorStyle
import com.example.fabfit.R
import com.example.fabfit.presentation.LoginScreen
import com.example.fabfit.presentation.Screens.*
import com.example.fabfit.presentation.SignUpScreen
import com.google.firebase.auth.FirebaseAuth

data class BottomNavItem(val name: String, val icon: ImageVector, val unselectedIcon: ImageVector)

@Composable
fun App(firebaseAuth: FirebaseAuth, startPayment: ()->Unit) {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf(0) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    val shouldShowBottomBar = remember { mutableStateOf(false) }

    val startScreen: SubNavigation = if (firebaseAuth.currentUser == null) {
        SubNavigation.LoginSignUpScreen
    } else {
        SubNavigation.MainHomeScreen
    }

    val bottomNavItems = listOf(
        BottomNavItem("Home", Icons.Default.Home, Icons.Outlined.Home),
        BottomNavItem("WishList", Icons.Default.Favorite, Icons.Outlined.Favorite),
        BottomNavItem("Cart", Icons.Default.ShoppingCart, Icons.Outlined.ShoppingCart),
        BottomNavItem("Profile", Icons.Default.Person, Icons.Outlined.Person),
    )

    // Sync bottom bar visibility
    LaunchedEffect(currentDestination) {
        shouldShowBottomBar.value = currentDestination !in listOf(
            Routes.LoginScreen.route,
            Routes.SignUpScreen.route
        )

        selectedItem = when (currentDestination) {
            Routes.HomeScreen.route -> 0
            Routes.WishListScreen.route -> 1
            Routes.CartScreen.route -> 2
            Routes.ProfileScreen.route -> 3
            else -> selectedItem
        }
    }

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar.value) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding())
                ) {
                    AnimatedBottomBar(
                        selectedItem = selectedItem,
                        itemSize = bottomNavItems.size,
                        containerColor = Color.Transparent,
                        indicatorColor = colorResource(id = R.color.orange),
                        indicatorDirection = IndicatorDirection.BOTTOM,
                        indicatorStyle = IndicatorStyle.FILLED
                    ) {
                        bottomNavItems.forEachIndexed { index, item ->
                            BottomBarItem(
                                selected = selectedItem == index,
                                onClick = {
                                    selectedItem = index
                                    val route = when (index) {
                                        0 -> Routes.HomeScreen.route
                                        1 -> Routes.WishListScreen.route
                                        2 -> Routes.CartScreen.route
                                        3 -> Routes.ProfileScreen.route
                                        else -> ""
                                    }
                                    navController.navigate(route) {
                                        popUpTo(Routes.HomeScreen.route) { inclusive = false }
                                        launchSingleTop = true
                                    }
                                },
                                imageVector = item.icon,
                                label = item.name,
                                containerColor = Color.Transparent
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = if (shouldShowBottomBar.value) 60.dp else 0.dp)
        ) {
            NavHost(
                navController = navController,
                startDestination = startScreen.route
            ) {
                // Login/Signup Graph
                navigation(
                    startDestination = Routes.LoginScreen.route,
                    route = SubNavigation.LoginSignUpScreen.route
                ) {
                    composable(Routes.LoginScreen.route) {
                        LoginScreen(navController = navController)
                    }
                    composable(Routes.SignUpScreen.route) {
                        SignUpScreen(navController = navController)
                    }
                }

                // Main Graph
                navigation(
                    startDestination = Routes.HomeScreen.route,
                    route = SubNavigation.MainHomeScreen.route
                ) {
                    composable(Routes.HomeScreen.route) {
                        HomeScreenUi(navController = navController)
                    }
                    composable(Routes.ProfileScreen.route) {
                        ProfileScreenUi(navController = navController, firebaseAuth = firebaseAuth)
                    }
                    composable(Routes.WishListScreen.route) {
                        GetAllFav(navController = navController)
                    }
                    composable(Routes.CartScreen.route) {
                        CartScreen(navController = navController)
                    }
                    composable(Routes.SeeAllProductsScreen.route) {
                        GetAllProducts(navController = navController)
                    }
                    composable(Routes.AllCategoriesScreen.route) {
                        AllCategoriesScreen(navController = navController)
                    }
                }

                // Screens with arguments
                composable(
                    route = Routes.EachProductDetailScreen.route,
                    arguments = listOf(navArgument("productId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val productId = backStackEntry.arguments?.getString("productId") ?: ""
                    EachProductDetailScreenUi(navController = navController, productId = productId)
                }

                composable(
                    route = Routes.EachCategoryItemScreen.route,
                    arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
                ) { backStackEntry ->
                    val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
                    EachCategoryProductScreenUi(navController = navController, categoryName = categoryName)
                }

                composable(
                    route = Routes.CheckoutScreen.route,
                    arguments = listOf(navArgument("productId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val productId = backStackEntry.arguments?.getString("productId") ?: ""
                    CheckOutScreenUi(navController = navController, productID = productId, startPayment = startPayment  )
                }
            }
        }
    }
}
