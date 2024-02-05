package ms.gamehouse.zicare.view.login

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ms.gamehouse.zicare.R
import ms.gamehouse.zicare.data.model.CredentialEntity
import ms.gamehouse.zicare.data.model.User
import ms.gamehouse.zicare.utils.Resource
import ms.gamehouse.zicare.view.Error
import ms.gamehouse.zicare.view.component.MSButtonComponent
import ms.gamehouse.zicare.view.component.MSHeadingTextComponent
import ms.gamehouse.zicare.view.component.MSNormalTextComponent
import ms.gamehouse.zicare.view.component.MSPasswordFieldComponent
import ms.gamehouse.zicare.view.component.MSTextFieldComponent
import ms.gamehouse.zicare.view.profile.ProfileActivity


@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    onSignUpButtonClicked: () -> Unit,
    navigateToProfileScreeen: () -> Unit
){
    val loginState by viewModel.loginState.observeAsState(initial = Resource.Loading())
    val onLoginButtonClicked = {credential: CredentialEntity ->
        viewModel.doLogin(credential)
    }

    LoginScreen(onLoginButtonClicked = onLoginButtonClicked,
        onSignUpButtonClicker = onSignUpButtonClicked)

    when(loginState){
        is Resource.Success -> {
            navigateToProfileScreeen()
        }

        is Resource.Error -> {
            Error(message = loginState.message.toString())
        }

        is Resource.Loading -> {
            println("Loading......................")
        }
    }
}


@Composable
fun LoginScreen(
    onLoginButtonClicked: (CredentialEntity) -> Unit,
    onSignUpButtonClicker:() ->Unit
){
    val mContext = LocalContext.current
    val userName : MutableState<String> = remember {
        mutableStateOf("")
    }
    val password : MutableState<String> = remember {
        mutableStateOf("")
    }

    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Box {
            Image(painter = painterResource(id = R.drawable.header),
                contentDescription = null,
                contentScale= ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp))
            )
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MSNormalTextComponent(value = "Welcome Back!", colorResource(id = R.color.white))
                MSHeadingTextComponent(value = "Sign In to Continue", colorResource(id = R.color.white))
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(25.dp)
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(25.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally){

                    Image(painter = painterResource(id = R.drawable.zicare_logo),
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(60.dp))
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    MSTextFieldComponent(strLabel = "User Name",
                        painterResource = painterResource(id = R.drawable.baseline_person_24),
                        userName)
                    MSPasswordFieldComponent(strLabel = "Password",
                        painterResource = painterResource(id = R.drawable.baseline_lock_24),
                        password)
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    Spacer(modifier = Modifier.padding(top = 30.dp))
                    MSButtonComponent(value = "LOGIN"){
                        if(userName.value.isNotEmpty() && password.value.isNotEmpty()){
//                            mContext.startActivity(Intent(mContext, ProfileActivity::class.java))
                            onLoginButtonClicked(CredentialEntity(
                                userName.value,
                                password.value))
                        }else{
                            Toast.makeText(mContext, "User Name and Password cannot be blank!",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                    GotoSignUpPage(onSignUpButtonClicker)
                }
            }
        }
    }
}

@Composable
fun GotoSignUpPage(onSignUpButtonClicker:() ->Unit){
    val initialText = "Don't have an account? "
    val signupText = "Sign Up"
    val mContext = LocalContext.current

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(colorResource(id = R.color.primary_color),
            fontWeight = FontWeight.Bold)){
            pushStringAnnotation(signupText, signupText)
            append(signupText)
        }
    }

    ClickableText(
        text = annotatedString,
        modifier = Modifier.padding(top = 20.dp),
        style = TextStyle(textAlign = TextAlign.Center),
        onClick = {offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    onSignUpButtonClicker()
                }
    })
}