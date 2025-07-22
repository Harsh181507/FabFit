package com.example.fabfit.presentation

import android.R.attr.fontWeight
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.fabfit.presentation.Utils.CustomTextField
import java.nio.file.WatchEvent
import com.example.fabfit.R
import com.example.fabfit.domain.models.UserData
import com.example.fabfit.presentation.Navigation.Routes
import com.example.fabfit.presentation.Navigation.SubNavigation
import com.example.fabfit.presentation.Utils.SuccessAlertDialog
import com.example.fabfit.presentation.viewModels.ShoppingAppViewModel


@Composable
fun LoginScreen(modifier: Modifier = Modifier, navController: NavHostController, viewModel: ShoppingAppViewModel = hiltViewModel()) {

    val state = viewModel.loginScreenState.collectAsStateWithLifecycle()
    val showDialog = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current


    if(state.value.isLoading){
        Box(modifier= Modifier.fillMaxSize()){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }else if(state.value.errorMessage!=null){
        Box(modifier=Modifier.fillMaxSize()){
            Text(text = state.value.errorMessage!!)
        }
    }else if(state.value.userData!=null){
        SuccessAlertDialog(onClick = {
            navController.navigate(SubNavigation.MainHomeScreen.route){
                popUpTo(0){inclusive=true}
            }

        })
    }else{
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login",
                fontSize = 24.sp,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.Start)
            )
            CustomTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = "Email",
                leadingIcon = Icons.Default.Email,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                singleLine = true
            )
            Spacer(modifier = Modifier.padding(8.dp))
            CustomTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = "Password",
                leadingIcon = Icons.Default.Lock, // Replace with appropriate icon for password
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = "Forgot Password ?",
                modifier = Modifier.align(Alignment.End)
            )
            Spacer(modifier = Modifier.padding(16.dp))

            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {

                        val userData = UserData(
                            firstName = "",
                            lastName = "",
                            email = email,
                            password = password,
                            phoneNumber = ""
                        )
                        viewModel.loginUser(userData)
                        //Verify the User's credentials here
                        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange)),
                border = BorderStroke(1.dp, colorResource(id = R.color.orange))
            ) {
                Text(text = "Login",
                    color = colorResource(id = R.color.white),
                    fontSize = 16.sp
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ){
                Text("Don't have an account?")
                TextButton(onClick = {
                    navController.navigate(Routes.SignUpScreen.route)

                }) {
                    Text("SignUp", color = colorResource(id = R.color.orange))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f))
                Text(
                    text = "Or",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
                HorizontalDivider(modifier = Modifier.weight(1f))

            }
            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(8.dp)
            )   {
                Image(painter = painterResource(id = R.drawable.google), contentDescription = "Google Icon",
                    modifier = Modifier.size(24.dp))

                Spacer(modifier = Modifier.size(8.dp))
                Text(text = "Login with Google", color = colorResource(id = R.color.black))
            }
        }

    }

}