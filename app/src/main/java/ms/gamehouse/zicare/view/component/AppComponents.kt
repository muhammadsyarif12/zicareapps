package ms.gamehouse.zicare.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ms.gamehouse.zicare.R
import ms.gamehouse.zicare.data.model.User

val componentShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(0.dp),
)

@Composable
fun MSNormalTextComponent(value: String, textColor: Color){
    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = textColor,
        textAlign = TextAlign.Center
    )
}


@Composable
fun MSHeadingTextComponent(value: String, textColor: Color){
    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = textColor,
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MSTextFieldComponent(
    strLabel: String, painterResource: Painter,
    fieldValue: MutableState<String>){

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = strLabel)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.base_color),
            focusedLabelColor = colorResource(id = R.color.base_color),
            cursorColor = colorResource(id = R.color.teal_200),
            containerColor = colorResource(id = R.color.white)
        ),
        keyboardOptions = KeyboardOptions.Default,
        value = fieldValue.value,
        onValueChange = {text ->
            fieldValue.value = text
        },
        leadingIcon = {
            Icon(painter = painterResource,
                contentDescription = null)
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MSEmailFieldComponent(strLabel: String,
                          painterResource: Painter,
                          emailValue: MutableState<String>){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = strLabel)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.base_color),
            focusedLabelColor = colorResource(id = R.color.base_color),
            cursorColor = colorResource(id = R.color.teal_200),
            containerColor = colorResource(id = R.color.white)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        value = emailValue.value,
        onValueChange = {text ->
            emailValue.value = text
        },
        leadingIcon = {
            Icon(painter = painterResource,
                contentDescription = null)
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MSPasswordFieldComponent(strLabel: String, painterResource: Painter,
                             passValue: MutableState<String>){
    var isVisible by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = { Text(text = strLabel)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.base_color),
            focusedLabelColor = colorResource(id = R.color.base_color),
            cursorColor = colorResource(id = R.color.teal_200),
            containerColor = colorResource(id = R.color.white)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        value = passValue.value,
        onValueChange = {text ->
            passValue.value = text
        },
        leadingIcon = {
            Icon(painter = painterResource,
                contentDescription = null)
        },
        trailingIcon = {
            val iconImage = if(isVisible){
                painterResource(id = R.drawable.baseline_visibility_24)
            }else{
                painterResource(id = R.drawable.baseline_visibility_off_24)
            }

            val desc = if(isVisible){
                stringResource(id = R.string.signup_hide_pass)
            }else{
                stringResource(id = R.string.signup_show_pass)
            }

            IconButton(onClick = {
                isVisible = !isVisible
            }) {
                Icon(painter = iconImage, contentDescription = desc)
            }
        },
        visualTransformation = if(isVisible) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}

@Composable
fun MSButtonComponent(value: String, onClick: () -> Unit){
    Button(onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box (modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        colorResource(id = R.color.base_color),
                        colorResource(id = R.color.primary_color)
                    )
                ),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center){
            Text(text = value,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun MSTextIconComponent(icon: Painter, text: String, onClick: () -> Unit){
    Row (modifier = Modifier
        .clickable(onClick = onClick)
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically){
        Image(painter = icon,
            contentDescription = null)
        Text(
            text = text, style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            ),
            color = colorResource(id = R.color.black),
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(start = 15.dp)
        )
    }
}
