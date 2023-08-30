package com.example.viewmodeljetpack07

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodeljetpack07.ViewModel.QuoteViewModel
import com.example.viewmodeljetpack07.ViewModel.QuotesViewModelFactory
import com.example.viewmodeljetpack07.ui.theme.ViewModelJetpack07Theme

lateinit var mainViewModel: QuoteViewModel
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(
            this,
            QuotesViewModelFactory(application)
        ).get(QuoteViewModel::class.java)
        setContent {
            ViewModelJetpack07Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    card(modifier = Modifier, mainViewModel = mainViewModel)
                    //  Greeting("Android")

                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ViewModelJetpack07Theme {
        val Mcontext = LocalContext.current

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            card(modifier = Modifier, mainViewModel = QuoteViewModel(Mcontext))
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(
                        text = "Previous",
                        style = TextStyle(fontSize = 15.sp)
                    )
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(
                        text = "Next",
                        style = TextStyle(fontSize = 15.sp)
                    )
                }
            }
        }


    }
}


@Composable
fun card(modifier: Modifier, mainViewModel: QuoteViewModel) {
    val Mcontext = LocalContext.current

    val text: MutableState<String> =
        remember { mutableStateOf(mainViewModel.getRandomQuote().text) }
    val author: MutableState<String> =
        remember { mutableStateOf(mainViewModel.getRandomQuote().author) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(modifier = Modifier.padding(5.dp)) {
            // var textState by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .padding(5.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_format_quote_24),
                    contentDescription = "",
                    modifier
                        .clip(RoundedCornerShape(10.dp))
                        .size(48.dp)

                )
                Text(
                    text = text.value + "",
                    maxLines = 2,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Cursive
                )
                Text(
                    text = author.value + "",
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.SansSerif
                )

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()

                        .padding(end = 50.dp)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_share_24),
                        contentDescription = "",
                        modifier
                            .clip(RoundedCornerShape(10.dp))
                            .size(48.dp)
                            .clickable {
                                mainViewModel.onShare(Mcontext,text.value+"Authur"+author.value)
                            }

                    )
                }


            }


        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        val context = LocalContext.current
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(onClick = {
                var quot = mainViewModel.previousQuote()
                text.value = quot.text
                author.value = quot.author
            }) {
                Text(
                    text = "Previous",
                    style = TextStyle(fontSize = 15.sp)
                )
            }
            Button(onClick = {
                var quot = mainViewModel.nextQuote()
                text.value = quot.text
                author.value = quot.author

            }) {
                Text(
                    text = "Next",
                    style = TextStyle(fontSize = 15.sp)
                )
            }
        }
    }
}

