package com.example.fabfit.presentation.viewModels

import android.net.Uri
import com.example.fabfit.domain.models.UserData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fabfit.common.HomeScreenState
import com.example.fabfit.common.ResultState
import com.example.fabfit.domain.models.CartDataModels
import com.example.fabfit.domain.models.CategoryDataModels
import com.example.fabfit.domain.models.ProductDataModels
import com.example.fabfit.domain.models.UserDataParent
import com.example.fabfit.domain.useCases.AddToFavUseCase
import com.example.fabfit.domain.useCases.AddtoCardUseCase
import com.example.fabfit.domain.useCases.CreateUserUseCase
import com.example.fabfit.domain.useCases.GetAllCategoryUseCase
import com.example.fabfit.domain.useCases.GetAllFavUseCases
import com.example.fabfit.domain.useCases.GetAllProductUseCase
import com.example.fabfit.domain.useCases.GetAllSuggestedProductsUseCase
import com.example.fabfit.domain.useCases.GetBannerUseCase
import com.example.fabfit.domain.useCases.GetCartUseCase
import com.example.fabfit.domain.useCases.GetCategoryInLimitUseCase
import com.example.fabfit.domain.useCases.GetCheckOutUseCase
import com.example.fabfit.domain.useCases.GetProductByIdUseCase
import com.example.fabfit.domain.useCases.GetProductsInLimitsUseCase
import com.example.fabfit.domain.useCases.GetSpecificCategoryItemsUseCase
import com.example.fabfit.domain.useCases.GetUserUseCase
import com.example.fabfit.domain.useCases.LoginUserUseCase
import com.example.fabfit.domain.useCases.UpdateUserDataUseCase
import com.example.fabfit.domain.useCases.UserProfileImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ShoppingAppViewModel @Inject constructor(
    private val AddToCardUseCase: AddtoCardUseCase,
    private val AddToFavUseCase: AddToFavUseCase,
    private val CreateUserUseCase: CreateUserUseCase,
    private val GetAllCategoryUseCase: GetAllCategoryUseCase,
    private val GetAllFavUseCase: GetAllFavUseCases,
    private val GetAllProductUseCase: GetAllProductUseCase,
    private val GetAllSuggestedProductsUseCase: GetAllSuggestedProductsUseCase,
    private val GetBannerUseCase: GetBannerUseCase,
    private val GetCartUseCase: GetCartUseCase,
    private val GetCategoryInLimitUseCase: GetCategoryInLimitUseCase,
    private val GetCheckoutUseCase: GetCheckOutUseCase,
    private val GetProductByIdUseCase: GetProductByIdUseCase,
    private val GetProductsInLimitUseCase: GetProductsInLimitsUseCase,
    private val GetSpecificCategoryItemsUseCase: GetSpecificCategoryItemsUseCase,
    private val GetUserUseCase: GetUserUseCase,
    private val LoginUserUseCase: LoginUserUseCase,
    private val UpdateUserDataUseCase: UpdateUserDataUseCase,
    private val UserProfileImageUseCase: UserProfileImageUseCase
) : ViewModel() {
    private val _profileScreenState = MutableStateFlow(ProfileScreenState())
    val profileScreenState = _profileScreenState.asStateFlow()

    private val _signUpScreenState = MutableStateFlow(SignUpScreenState())
    val signUpScreenState = _signUpScreenState.asStateFlow()

    private val _loginScreenState = MutableStateFlow(LoginScreenState())
    val loginScreenState = _loginScreenState.asStateFlow()

    private val _updateScreenState = MutableStateFlow(UpdateScreenState())
    val updateScreenState = _updateScreenState.asStateFlow()

    private val _uploadUserProfileImageState = MutableStateFlow(UploadUserProfileImageState())
    val uploadUserProfileImageState = _uploadUserProfileImageState.asStateFlow()

    private val _addtoCartState = MutableStateFlow(AddtoCartState())
    val addtoCartState = _addtoCartState.asStateFlow()

    private val _getProductByIDState = MutableStateFlow(GetProductByIDState())
    val getProductByIDState = _getProductByIDState.asStateFlow()

    private val _addToFavState = MutableStateFlow(AddToFavState())
    val addToFavState = _addToFavState.asStateFlow()

    private val _getAllFavState = MutableStateFlow(GetAllFavState())
    val getAllFavState = _getAllFavState.asStateFlow()

    private val _getAllProductsState = MutableStateFlow(GetAllProductsState())
    val getAllProductsState = _getAllProductsState.asStateFlow()

    private val _getCartState = MutableStateFlow(GetCartState())
    val getCartState = _getCartState.asStateFlow()

    private val _getAllCategoriesState = MutableStateFlow(GetAllCategoriesState())
    val getAllCategoriesState = _getAllCategoriesState.asStateFlow()

    private val _getCheckoutState = MutableStateFlow(GetCheckoutState())
    val getCheckoutState = _getCheckoutState.asStateFlow()

    private val _getSpecificCategoryItemsState = MutableStateFlow(GetSpecificCategoryItemsState())
    val getSpecificCategoryItemsState = _getSpecificCategoryItemsState.asStateFlow()

    private val _getAllSuggestedProductsState = MutableStateFlow(GetAllSuggestedProductsState())
    val getAllSuggestedProductsState = _getAllSuggestedProductsState.asStateFlow()

    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState = _homeScreenState.asStateFlow()


    fun getSpecificCategoryItems(categoryName: String) {
        viewModelScope.launch {
            GetSpecificCategoryItemsUseCase.getSpecificCategoryItemsUseCase(categoryName).collect {
                when (it) {
                    is ResultState.Error -> {
                        _getSpecificCategoryItemsState.value =
                            _getSpecificCategoryItemsState.value.copy(
                                isLoading = false,
                                errorMessage = it.message

                            )
                    }

                    is ResultState.Loading -> {
                        _getSpecificCategoryItemsState.value =
                            _getSpecificCategoryItemsState.value.copy(
                                isLoading = true
                            )
                    }

                    is ResultState.Success -> {
                        _getSpecificCategoryItemsState.value =
                            _getSpecificCategoryItemsState.value.copy(
                                isLoading = false,
                                userData = it.data
                            )
                    }

                }
            }

        }


    }

    fun getCheckOut(productId: String) {
        viewModelScope.launch {
            GetCheckoutUseCase.getCheckOutUseCase(productId).collect {
                when (it) {
                    is ResultState.Error -> {
                        _getCheckoutState.value = _getCheckoutState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _getCheckoutState.value = _getCheckoutState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _getCheckoutState.value = _getCheckoutState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }

                }
            }
        }
    }

    fun getAllCategories() {
        viewModelScope.launch {
            GetAllCategoryUseCase.getAllCategoriesUseCase().collect {
                when (it) {
                    is ResultState.Error -> {
                        _getAllCategoriesState.value = _getAllCategoriesState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _getAllCategoriesState.value = _getAllCategoriesState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _getAllCategoriesState.value = _getAllCategoriesState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }

                }
            }
        }
    }

    fun getCart() {
        viewModelScope.launch {
            GetCartUseCase.getCartUseCase().collect {
                when (it) {
                    is ResultState.Error -> {
                        _getCartState.value = _getCartState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _getCartState.value = _getCartState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _getCartState.value = _getCartState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }

                }
            }
        }
    }

    fun getAllProducts() {
        viewModelScope.launch {
            GetAllProductUseCase.getAllProductsUseCase().collect {
                when (it) {
                    is ResultState.Error -> {
                        _getAllProductsState.value = _getAllProductsState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _getAllProductsState.value = _getAllProductsState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _getAllProductsState.value = _getAllProductsState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }
            }
        }
    }

    fun getAllFav() {
        viewModelScope.launch {
            GetAllFavUseCase.getAllFavUseCases().collect {
                when (it) {
                    is ResultState.Error -> {
                        _getAllFavState.value = _getAllFavState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _getAllFavState.value = _getAllFavState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _getAllFavState.value = _getAllFavState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }


                }
            }
        }

    }

    fun addToFav(productDataModels: ProductDataModels) {
        viewModelScope.launch {
            AddToFavUseCase.addToFav(productDataModels).collect {
                when (it) {
                    is ResultState.Error -> {
                        _addToFavState.value = _addToFavState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _addToFavState.value = _addToFavState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _addToFavState.value = _addToFavState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )

                    }

                }

            }
        }


    }

    fun getProductByID(productId: String) {
        viewModelScope.launch {
            GetProductByIdUseCase.getProductByIdUseCase(productId).collect {
                when (it) {
                    is ResultState.Error -> {
                        _getProductByIDState.value = _getProductByIDState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _getProductByIDState.value = _getProductByIDState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        if (it.data != null) {
                            _getProductByIDState.value = _getProductByIDState.value.copy(
                                isLoading = false,
                                userData = it.data
                            )
                        } else {
                            _getProductByIDState.value = _getProductByIDState.value.copy(
                                isLoading = false,
                                errorMessage = "Product data not found"
                            )
                        }
                    }

                }


            }
        }

    }

    fun addToCart(cartDataModels: CartDataModels) {
        viewModelScope.launch {
            AddToCardUseCase.addtoCard(cartDataModels).collect {
                when (it) {
                    is ResultState.Error -> {
                        _addtoCartState.value = _addtoCartState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _addtoCartState.value = _addtoCartState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _addtoCartState.value = _addtoCartState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }

            }
        }

    }

    init {
        loadHomeScreenData()
    }

    fun loadHomeScreenData() {
        viewModelScope.launch {
            combine(
                GetCategoryInLimitUseCase.getCategoryInLimitUseCase(),
                GetProductsInLimitUseCase.getProductsInLimitsUseCase(),
                GetBannerUseCase.getBannerUseCase(),
            ) { categoriesResult, productResult, bannerResult ->
                when {
                    categoriesResult is ResultState.Error -> {
                        HomeScreenState(isLoading = false, errorMessage = categoriesResult.message)
                    }

                    productResult is ResultState.Error -> {
                        HomeScreenState(isLoading = false, errorMessage = productResult.message)
                    }

                    bannerResult is ResultState.Error -> {
                        HomeScreenState(isLoading = false, errorMessage = bannerResult.message)
                    }

                    categoriesResult is ResultState.Success && productResult is ResultState.Success && bannerResult is ResultState.Success -> {
                        HomeScreenState(
                            isLoading = false,
                            categories = categoriesResult.data,
                            products = productResult.data,
                            banners = bannerResult.data
                        )
                    }

                    else -> {
                        HomeScreenState(isLoading = true)
                    }

                }

            }.collect { state ->
                _homeScreenState.value = state
            }
        }

    }

    fun upLoadUserProfileImage(uri: Uri) {
        viewModelScope.launch {
            UserProfileImageUseCase.getUserProfileImageUseCase(uri).collect {
                when (it) {
                    is ResultState.Error -> {
                        _uploadUserProfileImageState.value =
                            _uploadUserProfileImageState.value.copy(
                                isLoading = false,
                                errorMessage = it.message
                            )
                    }

                    is ResultState.Loading -> {
                        _uploadUserProfileImageState.value =
                            _uploadUserProfileImageState.value.copy(
                                isLoading = true
                            )
                    }

                    is ResultState.Success -> {
                        _uploadUserProfileImageState.value =
                            _uploadUserProfileImageState.value.copy(
                                isLoading = false,
                                userData = it.data
                            )
                    }
                }
            }

        }
    }

    fun updateUserData(userDataParent: UserDataParent) {
        viewModelScope.launch {
            UpdateUserDataUseCase.updateUserData(userDataParent).collect {
                when (it) {
                    is ResultState.Error -> {
                        _updateScreenState.value = _updateScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _updateScreenState.value = _updateScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _updateScreenState.value = _updateScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }

                }

            }
        }

    }

    fun createUser(userData: UserData) {
        viewModelScope.launch {
            CreateUserUseCase.createUserUseCase(userData).collect {
                when (it) {
                    is ResultState.Error -> {
                        _signUpScreenState.value = _signUpScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _signUpScreenState.value = _signUpScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _signUpScreenState.value = _signUpScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }

            }

        }
    }

    fun loginUser(userData: UserData) {
        viewModelScope.launch {
            LoginUserUseCase.loginUserUseCase(userData).collect {
                when (it) {
                    is ResultState.Error -> {
                        _loginScreenState.value = _loginScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _loginScreenState.value = _loginScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _loginScreenState.value = _loginScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }
            }
        }
    }

    fun getUserById(uid: String) {
        viewModelScope.launch {
            GetUserUseCase.getUserUseCase(uid).collect {
                when (it) {
                    is ResultState.Error -> {
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }

            }
        }
    }

    fun getAllSuggestedProduct() {
        viewModelScope.launch {
            GetAllSuggestedProductsUseCase.getAllSuggestedProductsUseCase().collect {
                when (it) {
                    is ResultState.Error -> {
                        _getAllSuggestedProductsState.value =
                            _getAllSuggestedProductsState.value.copy(
                                isLoading = false,
                                errorMessage = it.message
                            )
                    }

                    is ResultState.Loading -> {
                        _getAllSuggestedProductsState.value =
                            _getAllSuggestedProductsState.value.copy(
                                isLoading = true
                            )
                    }

                    is ResultState.Success -> {
                        _getAllSuggestedProductsState.value =
                            _getAllSuggestedProductsState.value.copy(
                                isLoading = false,
                                userData = it.data
                            )
                    }

                }

            }
        }
    }

}

data class ProfileScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: UserDataParent? = null
)

data class SignUpScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null
)

data class LoginScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null
)

data class UpdateScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null
)

data class UploadUserProfileImageState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null
)

data class AddtoCartState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null
)

data class GetProductByIDState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: ProductDataModels? = null
)

data class AddToFavState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null
)

data class GetAllFavState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<ProductDataModels>? = emptyList()
)

data class GetAllProductsState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<ProductDataModels>? = emptyList()
)

data class GetCartState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<CartDataModels>? = emptyList()
)

data class GetAllCategoriesState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<CategoryDataModels>? = emptyList()
)

data class GetCheckoutState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: ProductDataModels? = null
)

data class GetSpecificCategoryItemsState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<ProductDataModels>? = emptyList()
)

data class GetAllSuggestedProductsState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<ProductDataModels>? = emptyList()
)


