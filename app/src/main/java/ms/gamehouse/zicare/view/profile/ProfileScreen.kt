package ms.gamehouse.zicare.view.profile

import android.content.Context
import android.graphics.drawable.shapes.OvalShape
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ms.gamehouse.zicare.R
import ms.gamehouse.zicare.data.model.User
import ms.gamehouse.zicare.view.component.MSButtonComponent
import ms.gamehouse.zicare.view.component.MSHeadingTextComponent
import ms.gamehouse.zicare.view.component.MSNormalTextComponent
import ms.gamehouse.zicare.view.component.MSTextIconComponent
import java.util.UUID

@Composable
fun ProfileScreen(userData: User){
    val mContext = LocalContext.current

    Surface (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        Box (contentAlignment = Alignment.TopCenter) {
            Image(
                painter = painterResource(id = R.drawable.header),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp))
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)) {
                MSNormalTextComponent(value = "Hi,", textColor = Color.White)
                MSHeadingTextComponent(value = userData.userFullName, textColor = Color.White)

            }
            Image(painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 120.dp)
                    .size(80.dp)
                    .shadow(elevation = 3.dp, shape = CircleShape)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(10.dp)
            )
            Column (modifier = Modifier
                .padding(top = 220.dp)
                .fillMaxWidth()){
                MSTextIconComponent(
                    icon = painterResource(id = R.drawable.baseline_perm_identity_24),
                    text = userData.userFullName,
                    onClick = { ShowToast(mContext, "Full Name")})
                Divider()
                MSTextIconComponent(
                    icon = painterResource(id = R.drawable.baseline_person_24),
                    text = userData.userName,
                    onClick = { ShowToast(mContext, "User Name")})
                Divider()
                MSTextIconComponent(
                    icon = painterResource(id = R.drawable.baseline_email_24),
                    text = userData.userEmail,
                    onClick = { ShowToast(mContext, "Email")})
                Divider()
                MSTextIconComponent(
                    icon = painterResource(id = R.drawable.baseline_lock_24),
                    text = userData.userPassword) {
                    Toast.makeText(mContext, "Password", Toast.LENGTH_SHORT).show()
                }
                Divider()
                Spacer(modifier = Modifier.weight(1f))
                Box (modifier = Modifier.padding(16.dp)){
                    MSButtonComponent(value = "LOGOUT") {

                    }
                }
            }
        }
    }
}

fun ShowToast(mContext: Context, value: String){

    Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show()
}

@Composable
@Preview(showBackground = true)
fun ProfilePreview(){
    val newUser = User(
        "Muhammad Syarif",
        "muhammad.syarif11",
        "muhammad.syarif11@gmail.com",
        "********",
        true)
    ProfileScreen(newUser)
}