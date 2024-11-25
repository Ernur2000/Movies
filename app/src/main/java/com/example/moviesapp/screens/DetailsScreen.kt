package com.example.moviesapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.LocalImageLoader
import com.example.moviesapp.MainViewModel

@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: MainViewModel,
    itemId: String) {
    val allMovies = viewModel.allMovies.observeAsState()
    val movieModel = allMovies.value?.find { it.id.toString() == itemId }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(model = movieModel?.image?.original, contentDescription = null)

        Text(
            text = "Title : ${movieModel?.name}",
            style = TextStyle.Default,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "Language : ${movieModel?.language}",
            style = TextStyle.Default,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "Genres : ${movieModel?.genres}",
            style = TextStyle.Default,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }

}