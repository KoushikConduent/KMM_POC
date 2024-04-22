import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmm_poc.common.entity.LoginEntity
import com.kmm_poc.common.network.ApiClient
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import composedemo.composeapp.generated.resources.Res
import composedemo.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


import org.jetbrains.compose.resources.vectorResource
@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        var userName by remember { mutableStateOf("") }
        var passWord by remember { mutableStateOf("") }
        var hidePassWord by remember { mutableStateOf(false) }

        val themeColor =  Color(red = 21, green = 56, blue = 104)
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.25f)
                    .background(color = themeColor)
                    .padding(16.dp),
                contentAlignment = Alignment.BottomStart
            ){

                Text(
                    text = "Sign In",
                    modifier = Modifier.padding(16.dp),
                    color = Color.White,
                    style = MaterialTheme.typography.h4
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(32.dp)
            )
            TextField(
                value = userName,
                onValueChange = {
                    userName = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                label = {
                    Text("Email Address")
                },
                colors = TextFieldDefaults.textFieldColors()
            )
            Text(
                text = "This will be the same Email address you use to login on the website",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.fillMaxWidth(1f)
                    .padding(horizontal = 16.dp)
            )
            TextField(
                value = passWord,
                onValueChange = {
                    passWord = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                label = {
                    Text("Password")
                },
                colors = TextFieldDefaults.textFieldColors(),
                trailingIcon = {
                    IconButton(onClick = {
                        hidePassWord = !hidePassWord
                    }){
                        Icon(imageVector = Icons.Default.Lock, contentDescription = "Hide Password")
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(

                ),
                visualTransformation = if (hidePassWord) PasswordVisualTransformation() else VisualTransformation.None
            )

            TextButton(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .wrapContentWidth(Alignment.End)
                    .padding(horizontal = 16.dp),
                onClick = {
                }
            ) {
                Text(
                    "Forgot Password?",
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            )
            Button(
                onClick = {
                    performLoginRequestAndUpdateUI { responseData ->
                        // Update UI with response data here and navigate to next screen
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = themeColor)
            ) {
                Text(
                    text = "Sign In".uppercase(),
                    style = MaterialTheme.typography.button,
                    modifier = Modifier.padding(8.dp),
                    color = Color.White
                )
            }
        }
    }
}

fun performLoginRequestAndUpdateUI(updateResponseData: (LoginEntity) -> Unit) {
    CoroutineScope(Dispatchers.Main).launch {
        try {
            updateResponseData(ApiClient().processLoginReq())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun todaysDate(): String {
    fun LocalDateTime.format() = toString().substringBefore('T')
    val now = Clock.System.now()
    val zone = TimeZone.currentSystemDefault()
    return now.toLocalDateTime(zone).format()
}