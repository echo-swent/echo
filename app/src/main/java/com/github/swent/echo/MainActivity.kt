package com.github.swent.echo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.swent.echo.compose.components.EventInfoSheet
import com.github.swent.echo.compose.components.TopBar
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
@Composable
fun ScaffoldAppBase() {

    Scaffold(
        topBar = { TopBar({}, {}) },
    ) { innerPadding -> // the inner padding makes the content padded according to the top bar
        Content(innerPadding)
    }
}
/**
 * Content of the screen contains all the composables that are displayed on the screen (except the
 * top bar or floating buttons that go in the scaffold)
 */
@Composable
fun Content(innerPadding: PaddingValues) {
    Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
        // TODO replace with actual data from the database corresponding to the event clicked

        // title of the event
        val eventTitle by remember { mutableStateOf("Bowling Tournament") }
        // organization of the event
        val eventOrganization by remember { mutableStateOf("Bowling club") }
        // description of the event
        val eventDescription by remember {
            mutableStateOf(
                "Individual tournament with 16 participants. Winner and loser brackets will be played at the same time. Amateur level."
            )
        }
        // date and time of the event
        val eventDateTime by remember { mutableStateOf("15/05\n18:30") }
        // image of the event (not sure if that's how images work in the database)
        val eventImage by remember { mutableIntStateOf(R.drawable.ic_launcher_background) }
        // number of people who joined the event
        val eventPeople by remember { mutableIntStateOf(0) }
        // number of people who can join the event
        val eventPeopleMax by remember { mutableIntStateOf(0) }
        val showBottomSheet = remember { mutableStateOf(false) }

        Button(onClick = { showBottomSheet.value = true }) {
            Icon(imageVector = Icons.Filled.Face, contentDescription = "Join the event")
            Text("Join the event")
        }

        EventInfoSheet(
            showBottomSheet = showBottomSheet,
            eventTitle = eventTitle,
            eventOrganization = eventOrganization,
            eventDescription = eventDescription,
            eventDateTime = eventDateTime,
            eventImage = eventImage,
            eventPeople = eventPeople,
            eventPeopleMax = eventPeopleMax
        )
    }
}
