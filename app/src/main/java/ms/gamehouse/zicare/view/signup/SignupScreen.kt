package ms.gamehouse.zicare.view.signup

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ms.gamehouse.zicare.R
import ms.gamehouse.zicare.data.model.RegistrationEntity
import ms.gamehouse.zicare.utils.Resource
import ms.gamehouse.zicare.view.component.MSButtonComponent
import ms.gamehouse.zicare.view.component.MSEmailFieldComponent
import ms.gamehouse.zicare.view.component.MSHeadingTextComponent
import ms.gamehouse.zicare.view.component.MSNormalTextComponent
import ms.gamehouse.zicare.view.component.MSPasswordFieldComponent
import ms.gamehouse.zicare.view.component.MSTextFieldComponent


@Composable
fun SignupRoute(
    viewModel: SignupViewModel = hiltViewModel(),
    navController: NavHostController,
    navigateToLogin: () -> Unit
){
    val signupState by viewModel.signup.observeAsState(initial = Resource.Loading())
    val onCreateAccountButtonClicked = {data: RegistrationEntity ->
        viewModel.doSignUp(data)
    }

    SignupScreen(
        navController = navController,
        onCreateAccountClicked = onCreateAccountButtonClicked,
        navigateToLogin = navigateToLogin)

    when(signupState){
        is Resource.Loading -> {}
        is Resource.Error -> {

        }
        is Resource.Success -> {
            navController.popBackStack()
            navigateToLogin()
        }
    }
}


@Composable
fun SignupContent(onCreateAccountClicked: (RegistrationEntity) -> Unit){
    val fullName : MutableState<String> = remember {
        mutableStateOf("")
    }
    val userName : MutableState<String> = remember {
        mutableStateOf("")
    }
    val email : MutableState<String> = remember {
        mutableStateOf("")
    }
    val password : MutableState<String> = remember {
        mutableStateOf("")
    }
    val confirmPassword : MutableState<String> = remember {
        mutableStateOf("")
    }
    val mContext = LocalContext.current

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
                .padding(top = 20.dp, start = 16.dp, end = 16.dp)
            ) {
                MSNormalTextComponent(value = "Hi, there", colorResource(id = R.color.white))
                MSHeadingTextComponent(value = "Create an Account", colorResource(id = R.color.white))
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
                    .padding(horizontal = 16.dp, vertical = 20.dp)){

                    MSTextFieldComponent(strLabel = "Full Name",
                        painterResource = painterResource(id = R.drawable.baseline_perm_identity_24),
                        fullName)
                    MSTextFieldComponent(strLabel = "User Name",
                        painterResource = painterResource(id = R.drawable.baseline_person_24),
                        userName)
                    MSEmailFieldComponent(strLabel = "Email",
                        painterResource = painterResource(id = R.drawable.baseline_email_24),
                        email)
                    MSPasswordFieldComponent(strLabel = "Password",
                        painterResource = painterResource(id = R.drawable.baseline_lock_24),
                        password)
                    MSPasswordFieldComponent(strLabel = "Confirm Password",
                        painterResource = painterResource(id = R.drawable.baseline_lock_24),
                        confirmPassword)
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    Divider(modifier = Modifier
                        .fillMaxWidth()
                        .size(1.dp)
                        .padding(horizontal = 16.dp))
                    Spacer(modifier = Modifier.padding(top = 30.dp))
                    MSButtonComponent(value = "SIGNUP"){
                        if(validateRegistrationData(mContext,
                                fullName.value,
                                userName.value,
                                email.value,
                                password.value,
                                confirmPassword.value)){
                            onCreateAccountClicked(RegistrationEntity(
                                fullName.value,
                                userName.value,
                                email.value,
                                password.value))
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    navController: NavHostController,
    onCreateAccountClicked: (RegistrationEntity) -> Unit,
    navigateToLogin: () -> Unit
){
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = colorResource(id = R.color.white)
                ),
                title = { },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
//                        navigateToLogin()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back",
                            tint = Color.White)
                    }
                })
        },
        content = {
            SignupContent(onCreateAccountClicked)
        }
    )
}

fun validateRegistrationData(
    mContext: Context,
    fullName: String,
    userName: String,
    email: String,
    password: String,
    confirmPassword: String
):Boolean{
    if(fullName.isBlank() || userName.isBlank() || email.isBlank() ||
        password.isBlank() || confirmPassword.isBlank()){
        Toast.makeText(mContext, "Fields cannot be blank",Toast.LENGTH_SHORT).show()
        return false
    }else{
        if (password != confirmPassword){
            Toast.makeText(mContext, "Password not match",Toast.LENGTH_SHORT).show()
            return false
        }

    }
    return true
}