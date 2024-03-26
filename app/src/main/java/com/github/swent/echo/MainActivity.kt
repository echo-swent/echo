package com.github.swent.echo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.swent.echo.ui.theme.EchoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EchoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EchoTheme { ScaffoldAppBase() }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EchoTheme { Greeting("Android") }
}
/**
 * ScaffoldAppBase is the main composable that defines the layout of the screen. It uses the
 * [Scaffold] composable to define the layout structure of the screen. The [Scaffold] composable
 * provides a layout structure for the screen and also provides the top bar, bottom bar, and
 * floating action button slots to place the corresponding composables.
 */
@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldAppBase() {
    // Scroll behavior for the top app bar, makes it pinned
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            // Top app bar with title, navigation icon, and actions
            CenterAlignedTopAppBar(
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                title = {
                    Text(
                        "Echo",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis, // should not happen
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = { // hamburger menu
                    IconButton(onClick = { /* do something */}) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu to access all subpages"
                        )
                    }
                },
                actions = { // search icon
                    IconButton(onClick = { /* do something */}) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search icon to access the search screen"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding -> // the inner padding makes the content padded according to the top bar
        Content(innerPadding)
    }
}
/**
 * Content of the screen contains all the composables that are displayed on the screen (except the
 * top bar or floating buttons that go in the scaffold)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(innerPadding: PaddingValues) {
    Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
        val sheetState = rememberModalBottomSheetState()
        // set to false initially, will need to change it to true to show the bottom sheet (by
        // clicking events)
        var showBottomSheet by remember { mutableStateOf(true) }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                // Sheet content
                Box(
                    modifier =
                        Modifier.padding(start = 20.dp, end = 20.dp, top = 0.dp, bottom = 32.dp).width(360.dp)
                            .height(375.dp)
                ) {
                    Text(
                        text = "Bowling Tournament",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight(600),
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    )
                    Text(
                        modifier = Modifier.padding(top = 24.dp),
                        text = "Bowling club",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            color = MaterialTheme.colorScheme.tertiary,
                        )
                    )
                }
            }
        }
    }
}
