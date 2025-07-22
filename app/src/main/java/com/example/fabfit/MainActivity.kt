package com.example.fabfit

import android.app.Activity

import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.postDelayed
import com.example.fabfit.presentation.Navigation.App
import com.example.fabfit.ui.theme.FabFitTheme
import com.google.firebase.auth.FirebaseAuth
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import java.util.logging.Handler
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity(), PaymentResultWithDataListener {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FabFitTheme {
                MainScreen(firebaseAuth = firebaseAuth, { startPayment() })

            }
        }
    }


    @Composable
    fun MainScreen(firebaseAuth: FirebaseAuth, onstartPayment: () -> Unit) {
        val showSplash = remember {
            mutableStateOf(true)
        }
        LaunchedEffect(Unit) {
            android.os.Handler(Looper.getMainLooper()).postDelayed(
                {
                    showSplash.value = false
                }, 3000
            )
        }
        if (showSplash.value) {
            SplashScreen()
        } else {
            App(firebaseAuth = firebaseAuth, { onstartPayment() })
        }

    }


    @Composable
    fun SplashScreen() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = "App Icon",
                    modifier = Modifier.size(300.dp)
                )
                BasicText(
                    text = "Welcome to FabFit",
                    style= MaterialTheme.typography.labelLarge.copy(
                        color = Color.White,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                )

            }
        }
    }

    private fun startPayment() {

        val activity: Activity = this
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name", "Razorpay Corp")
            options.put("description", "Demoing Charges")
            //You can omit the image option to fetch the image from the Dashboard
            options.put("image", "http://example.com/image/rzp.jpg")
            options.put("theme.color", "#3399cc");
            options.put("currency", "<currency>");
            options.put("order_id", "order_DBJOWzybf0sJbb");
            options.put("amount", "50000")//pass amount in currency subunits

            val retryObj = JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email", "<email>")
            prefill.put("contact", "<phone>")

            options.put("prefill", prefill)
            co.open(activity, options)
        } catch (e: Exception) {
            Toast.makeText(activity, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }


    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {

    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {

    }
}

