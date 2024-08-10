package tj.motivation.hub.home.presantation.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import tj.motivation.hub.core.util.convertDpToPx
import tj.motivation.hub.home.presantation.view_model.HomeViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()
) {

    val localConfiguration = LocalConfiguration.current

    LaunchedEffect(key1 = true) {
        val width = convertDpToPx(localConfiguration.screenWidthDp.toFloat())
        val height = convertDpToPx(localConfiguration.screenHeightDp.toFloat())
        viewModel.getRandomQuoteAndPhoto(width, height)
    }

    val state = viewModel.randomPhotoAndQuoteState.value
    val pagerState = rememberPagerState {
        state.data.size
    }
    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.Green, modifier = Modifier.size(32.dp))
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            VerticalPager(
                modifier = Modifier.fillMaxSize(),
                state = pagerState,
            ) { page ->

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .defaultMinSize()
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            state.data[page].image
                        ),
                        contentDescription = "Author ${state.data[page].author}",
                        modifier = Modifier
                            .fillMaxSize()
                            .defaultMinSize(
                                localConfiguration.screenWidthDp.dp,
                                localConfiguration.screenHeightDp.dp
                            ),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Color.DarkGray,
                                        Color.Transparent,
                                    ), tileMode = TileMode.Mirror
                                )
                            )
                            .padding(20.dp), contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            buildAnnotatedString {
                                for (i in state.data[page].quote.indices) {
                                    if (state.data[page].yellowWordStart != null) {
                                        if (i >= state.data[page].yellowWordStart!! && i <= state.data[page].yellowWordEnd!!) {
                                            withStyle(style = SpanStyle(color = Color.Yellow)) {
                                                append(state.data[page].quote[i])
                                            }
                                        } else append(state.data[page].quote[i])
                                    } else append(state.data[page].quote[i])
                                }
                            },
                            style = TextStyle(
                                color = Color.White, fontSize = 24.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                    Box(
                        contentAlignment = Alignment.TopCenter,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 100.dp)
                    ) {
                        Icon(
                            Icons.Outlined.Star,
                            contentDescription = "",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            }
        }
    }

}