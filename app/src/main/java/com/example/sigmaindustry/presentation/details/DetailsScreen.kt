package com.example.sigmaindustry.presentation.details

import androidx.compose.runtime.Composable
import com.example.sigmaindustry.data.remote.dto.SearchResult
import com.example.sigmaindustry.util.UIComponent

@Composable
fun DetailsScreen(
    article: SearchResult,
    event: (DetailsEvent) -> Unit,
    sideEffect: UIComponent?,
    navigateUp: () -> Unit
) {
//    val context = LocalContext.current
//
//    LaunchedEffect(key1 = sideEffect) {
//        sideEffect?.let {
//            when(sideEffect){
//                is UIComponent.Toast ->{
//                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
//                    event(DetailsEvent.RemoveSideEffect)
//                }
//                else -> Unit
//            }
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .statusBarsPadding()
//    ) {
//        DetailsTopBar(
//            onBrowsingClick = {
//                Intent(Intent.ACTION_VIEW).also {
//                    it.data = Uri.parse(article.url)
//                    if (it.resolveActivity(context.packageManager) != null) {
//                        context.startActivity(it)
//                    }
//                }
//            },
//            onShareClick = {
//                Intent(Intent.ACTION_SEND).also {
//                    it.putExtra(Intent.EXTRA_TEXT, article.url)
//                    it.type = "text/plain"
//                    if (it.resolveActivity(context.packageManager) != null) {
//                        context.startActivity(it)
//                    }
//                }
//            },
//            onBookMarkClick = {
//                event(DetailsEvent.UpsertDeleteArticle(article))
//            },
//            onBackClick = navigateUp
//        )
//
//        LazyColumn(
//            modifier = Modifier.fillMaxWidth(),
//            contentPadding = PaddingValues(
//                start = MediumPadding1,
//                end = MediumPadding1,
//                top = MediumPadding1
//            )
//        ) {
//            item {
//                AsyncImage(
//                    model = ImageRequest.Builder(context = context).data(article.urlToImage)
//                        .build(),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(ServiceImageHeight)
//                        .clip(MaterialTheme.shapes.medium),
//                    contentScale = ContentScale.Crop
//                )
//                Spacer(modifier = Modifier.height(MediumPadding1))
//                Text(
//                    text = article.title,
//                    style = MaterialTheme.typography.displaySmall,
//                    color = colorResource(
//                        id = R.color.black
//                    )
//                )
//                Text(
//                    text = article.content,
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = colorResource(
//                        id = R.color.black
//                    )
//                )
//            }
//        }
//    }
}

