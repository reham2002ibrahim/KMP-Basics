package com.example.articles.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.safe.args.generator.ErrorMessage
import coil.compose.AsyncImage
import com.example.articles.Articles.Article
import com.example.articles.Articles.ArticlesViewModel
import com.example.articles.Greeting
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val viewModel : ArticlesViewModel by viewModels()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "articles"
                    ) {
                        composable("articles") {
                            ArticlesScreen(
                                articlesViewModel = getViewModel<ArticlesViewModel>(),
                                onAboutButtonClick = {
                                    navController.navigate("greeting")
                                }
                            )
                        }
                        composable("greeting") {
                            GreetingView(Greeting().greet())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = text, fontSize = 36.sp)
    }
}

@Composable
fun ArticlesScreen(
    onAboutButtonClick: () -> Unit,
    articlesViewModel: ArticlesViewModel,
) {
    val articlesState by articlesViewModel.articalState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        AppBar(onAboutButtonClick)
        when {
            articlesState.loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            articlesState.error?.isNotEmpty() == true -> {
                ErrorMessage(
                    message = articlesState.error!!,
                    path = "",
                    line = 5,
                    column = 5
                )
            }
            articlesState.articles.isNotEmpty() -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(articlesState.articles) { article ->
                        ArticleItemView(article = article)
                    }
                }
            }
        }
    }
}


@Composable
fun ArticleItemView(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = article.urlToImage ?: " ",
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.description ?: "")
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.publishedAt,
            style = TextStyle(color = Color.Gray),
            textAlign = TextAlign.End,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(onAboutClick: () -> Unit) {
    TopAppBar(
        title = { Text("Articles") },
        actions = {
            IconButton(onClick = onAboutClick) {
                Icon(Icons.Default.Info, contentDescription = "About")
            }
        }
    )
}