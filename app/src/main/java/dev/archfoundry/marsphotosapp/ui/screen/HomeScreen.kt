package dev.archfoundry.marsphotosapp.ui.screen

import androidx.compose.foundation.Image
import dev.archfoundry.marsphotosapp.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.archfoundry.marsphotosapp.Network.MarsPhoto

@Composable
fun HomeScreen(marsUiState: MarsUiState, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (marsUiState) {
            is MarsUiState.Loading -> LoadingScreen(modifier=Modifier.fillMaxSize())
            is MarsUiState.Success -> PhotosGridScreen(marsUiState.photosList,modifier=Modifier.fillMaxSize())
            is MarsUiState.Error -> ErrorScreen()
        }

    }

}

@Composable
fun PhotosGridScreen(photosList: List<MarsPhoto>, modifier: Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        items(items = photosList, key = { photo -> photo.id }){
            photo->
            MarsPhotoCard(photo = photo,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f))
        }
    }
}

@Composable
fun MarsPhotoCard(photo: MarsPhoto,modifier: Modifier=Modifier) {
    Card(modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = photo.imgSrc,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentScale = ContentScale.Crop
        )
        Text(photo.imgSrc)
    }
}

@Composable
fun ErrorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter=painterResource(R.drawable.ic_connection_error),contentDescription=null)
        Text(text = "Error...",modifier=Modifier.padding(all=16.dp))
    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(){
            CircularProgressIndicator()
            Text(text = "Cargando")
        }
    }
}

@Composable
fun ResultScreen(text: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text)
    }
}