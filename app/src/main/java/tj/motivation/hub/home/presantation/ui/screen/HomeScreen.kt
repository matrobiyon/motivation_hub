package tj.motivation.hub.home.presantation.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import tj.motivation.hub.R
import tj.motivation.hub.core.util.convertDpToPx
import tj.motivation.hub.home.presantation.view_model.HomeViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navHostController : NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val localConfiguration = LocalConfiguration.current

    LaunchedEffect(key1 = true) {
        val width = convertDpToPx(localConfiguration.screenWidthDp.toFloat())
        val height = convertDpToPx(localConfiguration.screenHeightDp.toFloat())
        viewModel.getRandomQuoteAndPhoto(width,height)
    }
    val state = viewModel.randomPhotoAndQuoteState.value
    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    } else {
        VerticalPager(
            pageCount = state.data.size,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .defaultMinSize()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        state.data[page].background, placeholder = painterResource(
                            id = R.drawable.ic_loading_placeholder
                        )
                    ),
                    contentDescription = "Author ${state.data[page].author}",
                    modifier = Modifier.fillMaxSize().defaultMinSize(localConfiguration.screenWidthDp.dp,localConfiguration.screenHeightDp.dp),
                    contentScale = ContentScale.Crop
                )
//                Box(modifier = Modifier
//                    .fillMaxSize()
//                    .background(Brush.linearGradient(listOf(Color.Black,Color.White))))
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = state.data[page].quote,
                        style = TextStyle(color = Color.Black, fontSize = 22.sp,fontWeight = FontWeight.ExtraBold),
                        modifier = Modifier.background(Brush.radialGradient(listOf(Color.Black,Color.White)))
                    )
                }
            }
        }
    }

}