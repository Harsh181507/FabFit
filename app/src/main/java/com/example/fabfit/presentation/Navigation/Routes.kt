package com.example.fabfit.presentation.Navigation

sealed class SubNavigation(val route: String) {
    object LoginSignUpScreen : SubNavigation("login_signup_graph")
    object MainHomeScreen : SubNavigation("main_home_graph")
}

sealed class Routes(val route: String) {

    object LoginScreen : Routes("login_screen")
    object SignUpScreen : Routes("signup_screen")
    object HomeScreen : Routes("home_screen")
    object ProfileScreen : Routes("profile_screen")
    object WishListScreen : Routes("wishlist_screen")
    object CartScreen : Routes("cart_screen")
    object PayScreen : Routes("pay_screen")
    object SeeAllProductsScreen : Routes("see_all_products_screen")
    object AllCategoriesScreen : Routes("all_categories_screen")

    // Screens with arguments
    object CheckoutScreen : Routes("checkout_screen/{productId}") {
        fun createRoute(productId: String) = "checkout_screen/$productId"
    }

    object EachProductDetailScreen : Routes("product_detail_screen/{productId}") {
        fun createRoute(productId: String) = "product_detail_screen/$productId"
    }

    object EachCategoryItemScreen : Routes("category_items_screen/{categoryName}") {
        fun createRoute(categoryName: String) = "category_items_screen/$categoryName"
    }
}
