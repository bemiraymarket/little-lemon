package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.littlelemon.ui.theme.GreyLittleLemon
import com.example.littlelemon.ui.theme.WhiteLittleLemon
import com.example.littlelemon.ui.theme.YellowLittleLemon

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("littleLemon", Context.MODE_PRIVATE)

    fun saveData(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getData(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }
    fun clearData(){
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
@Composable
fun Onboarding(navController: NavHostController) {
    val context = LocalContext.current
    val preferencesManager = remember {PreferencesManager(context)}


    Column (
            modifier= Modifier
                .fillMaxSize()
                .padding(5.dp)
                .background(WhiteLittleLemon),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            var firstName by remember { mutableStateOf("")}
            var lastName by remember { mutableStateOf("")}
            var emailAddress by remember { mutableStateOf("") }
            Row(
                Modifier.height(80.dp)
            ){
                Image(
                    painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier.size(200.dp),
                )}
                Text(
                    "Let's get to know you",
                    color= WhiteLittleLemon,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(GreyLittleLemon)
                        .fillMaxWidth()
                        .padding(30.dp)
                )
                Spacer(modifier = Modifier.padding(20.dp))
                Text("Personal information",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                    )
                Spacer(modifier = Modifier.padding(20.dp))
                Text("First Name",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, bottom = 0.dp)

                )
                OutlinedTextField(
                    value = firstName,
                    onValueChange ={value -> firstName = value},
                    placeholder = { Text("First name", fontSize = 15.sp)},
                    modifier= Modifier
                        .padding(start = 10.dp, top = 0.dp, bottom = 10.dp, end = 10.dp)
                        .fillMaxWidth()

                )
                Text("Last Name",

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, top = 15.dp, bottom = 0.dp)

                )
                OutlinedTextField(
                    value = lastName,
                    onValueChange ={value -> lastName = value},
                    placeholder = { Text("Last name")},
                    modifier= Modifier
                        .padding(start = 10.dp, top = 0.dp, bottom = 10.dp, end = 10.dp)
                        .fillMaxWidth()
                )
                Text("Email",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, top = 15.dp, bottom = 0.dp)

                )
                OutlinedTextField(
                    value = emailAddress,
                    onValueChange = {value -> emailAddress = value},
                    placeholder = { Text("Email address")},
                    modifier= Modifier
                        .padding(start = 10.dp, top = 0.dp, bottom = 10.dp, end = 10.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(30.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = YellowLittleLemon),
                    onClick = {if (firstName.isBlank() || lastName.isBlank() || emailAddress.isBlank())
                                    Toast.makeText(context,"Registration unsuccessful. Please enter all data",Toast.LENGTH_SHORT).show()
                                else
                                    navController.navigate(Home.route)
                                    preferencesManager.saveData("firstName",firstName)
                                    preferencesManager.saveData("lastName",lastName)
                                    preferencesManager.saveData("emailAddress",emailAddress)
                                    Toast.makeText(context,"Registration successful",Toast.LENGTH_SHORT).show()
                              },
                    shape = RoundedCornerShape(16),
                    modifier=Modifier.padding(10.dp)
                ) {
                    Text(
                        text = "Register",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color= BlackLittleLemon,
                        modifier=Modifier.fillMaxWidth()
                    )

                }

        }

    }


@Preview
@Composable
fun OnboardingPreview(){
    Onboarding(navController = rememberNavController())
}