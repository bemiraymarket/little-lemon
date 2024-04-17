package com.example.littlelemon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.ClayLittleLemon
import com.example.littlelemon.ui.theme.GreyLittleLemon
import com.example.littlelemon.ui.theme.WhiteLittleLemon
import com.example.littlelemon.ui.theme.YellowLittleLemon
import com.example.littlelemon.ui.theme.karlaFontFamily
import com.example.littlelemon.ui.theme.markazitextFontFamily
import java.text.DecimalFormat


@Composable
fun Home(navController: NavHostController) {
    val database = MainActivity.database.menuItemDao().getAll().observeAsState(emptyList())
    var categoryFiltered by remember {
        mutableStateOf("")
    }
    var menuItems = when ( categoryFiltered) {
        "mains" -> database.value.filter { it.category == "mains" }
        "starters" -> database.value.filter { it.category == "starters" }
        "desserts" -> database.value.filter { it.category == "desserts" }
        "drinks" -> database.value.filter { it.category == "drinks" }
        else -> database.value
    }
    Column(
        modifier= Modifier
            .fillMaxSize()
            .background(WhiteLittleLemon),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(navController)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(GreyLittleLemon),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = "Little Lemon",
                modifier = Modifier
                    .padding(start = 10.dp, top = 0.dp)
                    .height(50.dp),
                fontSize = 60.sp,
                fontFamily = markazitextFontFamily,
                fontWeight = FontWeight.Medium,
                color=  YellowLittleLemon,
            )
            Text(
                "Chicago",
                modifier = Modifier
                    .padding(start = 10.dp, top = 0.dp)
                    .height(45.dp),
                fontFamily = markazitextFontFamily,
                fontSize = 40.sp,
                color = WhiteLittleLemon,
            )
            Row (
                horizontalArrangement = Arrangement.SpaceAround,
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(start = 0.dp, top = 0.dp)
                    .height(130.dp),
                ){
                Text(text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                    fontFamily = karlaFontFamily,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 16.sp,
                    fontSize = 16.sp,
                    color = WhiteLittleLemon,
                    modifier = Modifier
                        .width(230.dp)
                        .padding(top = 10.dp)
                )
                Image(
                    painterResource(id = R.drawable.hero_image) ,
                    contentDescription = "hero image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
            }
            var searchPhrase by remember{
                mutableStateOf("")
            }
            TextField(
                value = searchPhrase,
                placeholder = { Text(text="Enter Search Phrase",  fontFamily= karlaFontFamily,fontSize = 16.sp) },
                leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 0.dp, bottom = 10.dp, end = 10.dp),
                onValueChange = {searchPhrase= it},
            )
            if (searchPhrase.isNotEmpty()){
                menuItems = menuItems.filter{it.title.contains(searchPhrase, ignoreCase = true)}
            }
        }
        Column(modifier = Modifier.fillMaxWidth()

        ){

            Text(text = "ORDER FOR DELIVERY!",
                fontFamily= karlaFontFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )
            Row(horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = YellowLittleLemon),
                    shape = RoundedCornerShape(50),
                    contentPadding = PaddingValues(10.dp),
                    modifier=Modifier,
                    onClick = { categoryFiltered = "starters" }
                ) {Text(
                    text = "Starters",
                    textAlign = TextAlign.Center,
                    fontFamily = karlaFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color= GreyLittleLemon,
                    modifier=Modifier
                )
                }
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = YellowLittleLemon),
                    shape = RoundedCornerShape(50),
                    contentPadding = PaddingValues(10.dp),
                    modifier=Modifier,
                    onClick = { categoryFiltered = "mains" }
                ) {Text(
                    text = "Mains",
                    textAlign = TextAlign.Center,
                    fontFamily = karlaFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color= GreyLittleLemon,
                    modifier=Modifier
                )


                }
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = YellowLittleLemon),
                    shape = RoundedCornerShape(50),
                    contentPadding = PaddingValues(10.dp),
                    modifier=Modifier,
                    onClick = { categoryFiltered = "desserts" }
                ) {Text(
                    text = "Desserts",
                    textAlign = TextAlign.Center,
                    fontFamily = karlaFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color= GreyLittleLemon,
                    modifier=Modifier
                )


                }
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = YellowLittleLemon),
                    shape = RoundedCornerShape(50),
                    contentPadding = PaddingValues(10.dp),
                    modifier=Modifier,
                    onClick = { categoryFiltered= "drinks" }
                ) {Text(
                    text = "Drinks",
                    textAlign = TextAlign.Center,
                    fontFamily = karlaFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color= GreyLittleLemon,
                    modifier=Modifier
                )


                }

            }
        }
        MenuItemsList(items = menuItems )
    }
}
@Composable
fun Header(navController: NavHostController){
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ){
        Spacer(modifier = Modifier.padding(40.dp))
        Image(
            painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .size(200.dp)
                .width(0.dp),
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Image(
            painterResource(id = R.drawable.profile),
            contentDescription = "profile",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(top = 10.dp)
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .border(2.dp, Color.White, CircleShape)
                .clickable { navController.navigate(Profile.route) }
        )
    }
}
@Composable
fun Hero(){
    Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(GreyLittleLemon),
            verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = "Little Lemon",
            modifier = Modifier
                .padding(start = 10.dp, top = 0.dp)
                .height(50.dp),
            fontSize = 60.sp,
            fontFamily = markazitextFontFamily,
            fontWeight = FontWeight.Medium,
            color=  YellowLittleLemon,
        )
        Text(
            "Chicago",
            modifier = Modifier
                .padding(start = 10.dp, top = 0.dp)
                .height(45.dp),
            fontFamily = markazitextFontFamily,
            fontSize = 40.sp,
            color = WhiteLittleLemon,
        )
        Row (
            horizontalArrangement = Arrangement.SpaceAround,
            modifier= Modifier
                .fillMaxWidth()
                .padding(start = 0.dp, top = 0.dp)
                .height(130.dp),
        ){
                Text(
                    text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                    fontFamily = karlaFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = WhiteLittleLemon,
                    modifier = Modifier
                        .width(230.dp)
                        .padding(top = 10.dp)
                )
                Image(
                    painterResource(id = R.drawable.hero_image) ,
                    contentDescription = "hero image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(20.dp))

                )
        }
        var searchPhrase by remember{
            mutableStateOf("")
        }
        TextField(
            value = searchPhrase,
            placeholder = { Text(text="Enter Search Phrase", fontFamily= karlaFontFamily,fontSize = 16.sp) },
            leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 0.dp, bottom = 10.dp, end = 10.dp),
            onValueChange = {searchPhrase= it},
        )


    }
}
@Composable
fun MenuBreakdown(){
    Column(modifier = Modifier.fillMaxWidth()

    ){
        Text(text = "ORDER FOR DELIVERY!",
            fontFamily= karlaFontFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )
        Row(horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = YellowLittleLemon),
                shape = RoundedCornerShape(50),
                contentPadding = PaddingValues(10.dp),
                modifier=Modifier,
                onClick = { }
                 ) {Text(
                                    text = "Starters",
                                    textAlign = TextAlign.Center,
                                    fontFamily = karlaFontFamily,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color= GreyLittleLemon,
                                    modifier=Modifier
                                    )
                }
            Button(
                    colors = ButtonDefaults.buttonColors(containerColor = ClayLittleLemon),
                    shape = RoundedCornerShape(50),
                    contentPadding = PaddingValues(10.dp),
                    modifier=Modifier,
                    onClick = { /*TODO*/ }
                    ) {Text(
                                    text = "Mains",
                                    textAlign = TextAlign.Center,
                                    fontFamily = karlaFontFamily,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color= GreyLittleLemon,
                                    modifier=Modifier
        )


        }
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = ClayLittleLemon),
                shape = RoundedCornerShape(50),
                contentPadding = PaddingValues(10.dp),
                modifier=Modifier,
                onClick = { /*TODO*/ }
            ) {Text(
                text = "Desserts",
                textAlign = TextAlign.Center,
                fontFamily = karlaFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color= GreyLittleLemon,
                modifier=Modifier
            )


            }
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = ClayLittleLemon),
                shape = RoundedCornerShape(40),
                contentPadding = PaddingValues(10.dp),
                modifier=Modifier,
                onClick = { /*TODO*/ }
            ) {Text(
                text = "Drinks",
                textAlign = TextAlign.Center,
                fontFamily = karlaFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color= GreyLittleLemon,
                modifier=Modifier
            )


            }

        }
    }
}
@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 10.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Card (
                    border = BorderStroke(1.dp, GreyLittleLemon),
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column{
                            Text(
                                menuItem.title,
                                fontFamily = karlaFontFamily,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                            Text(
                                menuItem.description,
                                fontFamily = karlaFontFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                lineHeight = 16.sp,
                                color = GreyLittleLemon,
                                modifier = Modifier
                                    .padding(top = 5.dp, bottom = 5.dp)
                                    .fillMaxWidth(.75f)
                            )
                            Text(
                                "$ ${DecimalFormat("#.00").format(menuItem.price.toDouble())}",
                                color = GreyLittleLemon,
                                fontFamily = karlaFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                        MyView(menuItem.image)

                    }
                }
            }
        )
    }
}
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MyView(menuItem:String){
    GlideImage(model = menuItem ,
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(90.dp)
        )
}
@Preview
@Composable
fun HomePreview(){
    Home(navController = rememberNavController())
}

@Preview
@Composable
fun HeaderPreview(){
    Header(navController = rememberNavController())
}
@Preview
@Composable
fun HeroPreview(){
    Hero()
}
@Preview
@Composable
fun MenuBreakdownPreview(){
    MenuBreakdown()
}
/*@Preview
@Composable
private fun MenuItemsListPreview(){

    val items = listOf(
        MenuItemRoom(
            id = 1,
        title = "Godrogodro",
        description= "koba bary sy coco andrahona anaty vilany asiana cannelle",
        price = "10",
        image = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/grilledFish.jpg?raw=true",
            category = "mofogasy"
                ),
        MenuItemRoom(
            id = 1,
            title = "godrogodro",
            description= "koba bary sy coco",
            price = "10",
            image = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/grilledFish.jpg?raw=true",
            category = "mofogasy"


        )

    )
    MenuItemsList(items = items)
}
*/
