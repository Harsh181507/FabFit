# 🛍️ **FabFit – Fashion Shopping Android App**

<p align="center">
  <img src="https://img.icons8.com/color/96/000000/android-os.png" width="60" alt="Android" />
  <img src="https://img.icons8.com/color/96/000000/kotlin.png" width="60" alt="Kotlin" />
  <img src="https://img.icons8.com/color/96/000000/google-logo.png" width="60" alt="Google" />
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-1.9%2B-purple?logo=kotlin" alt="Kotlin" />
  <img src="https://img.shields.io/badge/Jetpack%20Compose-UI-blue?logo=android" alt="Jetpack Compose" />
  <img src="https://img.shields.io/badge/Hilt-DI-green?logo=dagger" alt="Hilt" />
  <img src="https://img.shields.io/badge/Firebase-Backend-orange?logo=firebase" alt="Firebase" />
  <img src="https://img.shields.io/badge/Coroutines-Async-lightblue?logo=kotlin" alt="Coroutines" />
  <img src="https://img.shields.io/badge/MVVM-Architecture-ff69b4?logo=android" alt="MVVM" />
  <img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="MIT License" />
</p>

---

> **FabFit** is a modern, modular Android shopping app for fashion and lifestyle.  
> Built with Jetpack Compose, MVVM, and Hilt, it lets users browse products, manage their cart and wishlist, checkout, and update their profile, all powered by Firebase.

---

## ✨ **Features**

- 📝 **User Registration & Login:** Secure sign-up and authentication via Firebase
- 🏠 **Home Screen:** Browse banners, categories, and featured products
- 🛒 **Product Browsing:** View all products, filter by category, and see details
- ❤️ **Wishlist:** Add/remove products to favorites
- 🛍️ **Cart:** Add products to cart, view and manage items
- 💳 **Checkout:** Enter shipping details and proceed to payment
- 👤 **Profile Management:** View and update user profile, including profile image
- 🔍 **Search:** Search products and categories
- 🏗️ **MVVM Architecture:** Clean separation of UI, business logic, and data
- 💉 **Dependency Injection:** Powered by Hilt for scalability and testability
- ⚡ **Coroutines:** Fast, async data operations
- 🎨 **Modern UI:** Jetpack Compose & Material 3

---

## 🖼️ **Screenshots**
<img width="280" height="614" alt="image 4" src="https://github.com/user-attachments/assets/0e2efe73-0fae-4989-8281-3c33ab93505e" />
<img width="280" height="614" alt="image 3" src="https://github.com/user-attachments/assets/e5ea962a-5515-430e-bd54-3aa871ade751" />
<img width="280" height="614" alt="image 5" src="https://github.com/user-attachments/assets/8ec17532-7382-4f6a-a821-d3194040ceee" />
<img width="280" height="614" alt="image 2" src="https://github.com/user-attachments/assets/a32efaf7-e56a-4d56-917b-4891468dca38" />
<img width="280" height="614" alt="image 1" src="https://github.com/user-attachments/assets/9b3eafb5-7993-4700-9b95-97fc225000f2" />
<img width="280" height="614" alt="Screenshot 2025-07-22 191355" src="https://github.com/user-attachments/assets/a1488d83-c9b4-4f99-97a0-ab0134d08a92" />
<img width="280" height="614" alt="Screenshot 2025-07-22 191319" src="https://github.com/user-attachments/assets/f5e8b5c4-76a3-4cb3-ac01-494adfc3a668" />
<img width="280" height="614" alt="image 6" src="https://github.com/user-attachments/assets/69d0af62-6c4d-4846-8120-c7fc7ee2ce39" />


## 🗂️ **Project Structure**

```plaintext
FabFit/
│
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/fabfit/
│           │   ├── common/        # Result wrappers, constants, states
│           │   ├── data/          # DI modules, repository implementation
│           │   ├── domain/
│           │   │   ├── di/        # Domain DI modules
│           │   │   ├── models/    # Data models (User, Product, Cart, etc.)
│           │   │   ├── repo/      # Repository interface
│           │   │   └── useCases/  # Use case classes
│           │   ├── presentation/
│           │   │   ├── Navigation/ # Navigation routes
│           │   │   ├── Screens/    # Compose UI screens
│           │   │   ├── Utils/      # Reusable UI components
│           │   │   └── viewModels/ # ViewModels (business logic)
│           └── res/                # Resources (themes, colors, etc.)
```

---

## 🚀 **Quick Start**

### 1. **Clone the Repository**

```sh
git clone https://github.com/yourusername/FabFit.git
cd FabFit
```

### 2. **Open in Android Studio**

- Open the project folder in **Android Studio** (Giraffe or newer recommended).

### 3. **Configure Firebase**

- Add your `google-services.json` to `app/` for Firebase integration.

### 4. **Build & Run**

- Connect an Android device or start an emulator.
- Click **Run** ▶️ or use `Shift+F10`.

---

## 🧭 **App Flow & Screens**

| Screen                | Route                          | Description                        |
|-----------------------|-------------------------------|------------------------------------|
| 📝 Sign Up            | `/signup_screen`               | Register a new user                |
| 🔑 Login              | `/login_screen`                | Authenticate and save session      |
| 🏠 Home               | `/home_screen`                 | Browse banners, categories, products|
| 🗂️ All Categories     | `/all_categories_screen`       | View all product categories        |
| 🛒 Cart               | `/cart_screen`                 | View and manage shopping cart      |
| ❤️ Wishlist           | `/wishlist_screen`             | View and manage favorite products  |
| 🛍️ Product Details    | `/product_detail_screen/{id}`  | View product details, add to cart  |
| 📦 Checkout           | `/checkout_screen/{id}`        | Enter shipping info, payment       |
| 👤 Profile            | `/profile_screen`              | View and update user profile       |

---

## 🔌 **Key Modules & Technologies**

| Module/Tech           | Purpose/Role                                      |
|-----------------------|---------------------------------------------------|
| **Kotlin**            | Modern, concise programming language              |
| **Jetpack Compose**   | Declarative UI toolkit for Android                |
| **Hilt**              | Dependency injection for Android                  |
| **Firebase**          | Auth, Firestore, Storage backend                  |
| **Coroutines**        | Asynchronous programming                          |
| **MVVM**              | Clean architecture for separation of concerns     |
| **Material 3**        | Latest Material Design for beautiful UI           |

---

## 🛡️ **Best Practices**

- **MVVM:** ViewModels manage UI state, repositories handle data.
- **Hilt:** All dependencies injected for testability.
- **Error Handling:** All data calls wrapped in `ResultState` sealed class.
- **Navigation:** Compose Navigation for screen transitions.
- **Material 3:** Consistent, modern UI/UX.

---

## 🤝 **Contributing**

Contributions are welcome!  
1. **Fork** the repository  
2. **Create a branch:** `git checkout -b feature/your-feature`  
3. **Commit your changes:** `git commit -am 'Add new feature'`  
4. **Push to branch:** `git push origin feature/your-feature`  
5. **Open a Pull Request**

---

## 📄 **License**

This project is licensed under the [MIT License](LICENSE).

---

## ℹ️ **About**

FabFit is a showcase of modern Android development with Compose, MVVM, Hilt, and Firebase, designed for fashion shopping.  
It demonstrates best practices in UI, architecture, and cloud integration.

---

## 📞 **Contact**

For questions, suggestions, or support, please open an issue or contact [harsh.sshr@gmail.com](mailto:harsh.sshr@gmail.com).

---

<p align="center">
  <img src="https://img.icons8.com/color/96/000000/android-os.png" width="60" alt="Android" />
  <img src="https://img.icons8.com/color/96/000000/kotlin.png" width="60" alt="Kotlin" />
  <img src="https://img.icons8.com/color/96/000000/google-logo.png" width="60" alt="Google" />
  <br/>
  <b>Made with ❤️ using Kotlin, Jetpack Compose & Firebase</b>
</p>
