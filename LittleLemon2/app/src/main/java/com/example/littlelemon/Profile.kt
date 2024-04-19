package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.BlackLittleLemon
import com.example.littlelemon.ui.theme.WhiteLittleLemon
import com.example.littlelemon.ui.theme.YellowLittleLemon
import com.example.littlelemon.ui.theme.karlaFontFamily

@Composable
fun Profile(navController: NavHostController) {
    val context = LocalContext.current
    val preferencesManager = remember {PreferencesManager(context)}
    val firstName = remember{ mutableStateOf(preferencesManager.getData("firstName","")) }
    val lastName = remember { mutableStateOf(preferencesManager.getData("lastName","")) }
    val emailAddress = remember { mutableStateOf(preferencesManager.getData("emailAddress","")) }

    Column(
        modifier= Modifier
            .fillMaxSize()
            .padding(5.dp)
            .background(WhiteLittleLemon),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.height(80.dp)
        ){
            Image(
                painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier.size(200.dp),
            )
        }
        Text("Personal information",
            fontFamily = karlaFontFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
        Text("First Name",
            fontFamily = karlaFontFamily,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, bottom = 0.dp)

        )
        OutlinedTextField(
            value = firstName.value,
            onValueChange ={},
            modifier= Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 0.dp, bottom = 10.dp, end = 10.dp)
                .height(40.dp)
        )
        Text("Last Name",
            fontFamily = karlaFontFamily,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, top = 15.dp, bottom = 0.dp)

        )
        OutlinedTextField(
            value = lastName.value,
            onValueChange ={},
            modifier= Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 0.dp, bottom = 10.dp, end = 10.dp)
                .height(40.dp)
        )
        Text("Email",
            fontFamily = karlaFontFamily,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, top = 15.dp, bottom = 0.dp)

        )
        OutlinedTextField(
            value = emailAddress.value,
            onValueChange ={},
            modifier= Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 0.dp, bottom = 10.dp, end = 10.dp)
                .height(40.dp)
        )
        Spacer(modifier = Modifier.padding(50.dp))
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = YellowLittleLemon),
            onClick = {
                navController.navigate(Onboarding.route)
                preferencesManager.clearData()

            },
            shape = RoundedCornerShape(16),
            modifier=Modifier.padding(10.dp)
        ) {
            Text(
                text = "Log out",
                textAlign = TextAlign.Center,
                fontFamily = karlaFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color= BlackLittleLemon,
                modifier=Modifier.fillMaxWidth()
            )

        }
    }
}
@Preview
@Composable
fun ProfilePreview(){
    Profile(navController = rememberNavController())
}
