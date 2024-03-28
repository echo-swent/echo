package com.github.swent.echo.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventInfoSheet(
    showBottomSheet: MutableState<Boolean>,
    eventTitle: String,
    eventOrganization: String,
    eventDescription: String,
    eventDateTime: String,
    eventImage: Int,
    eventPeople: Int,
    eventPeopleMax: Int
) {
    val sheetState = rememberModalBottomSheetState()
    // set to false initially, will need to change it to true to show the bottom sheet (by
    // clicking events)
    if (showBottomSheet.value) {

        ModalBottomSheet(
            onDismissRequest = { showBottomSheet.value = false },
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) {
            // Sheet content
            Box(
                modifier =
                    Modifier.padding(start = 20.dp, end = 20.dp, top = 0.dp, bottom = 32.dp)
                        .width(360.dp)
                        .height(375.dp)
            ) {
                Text(
                    text = eventTitle,
                    style =
                        TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight(600),
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = eventOrganization,
                    style =
                        TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight(600),
                            color = MaterialTheme.colorScheme.tertiary,
                        )
                )
                Text(
                    modifier = Modifier.align(Alignment.TopEnd),
                    text = eventDateTime,
                    style =
                        TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight(600),
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                )
                Text(
                    modifier = Modifier.padding(top = 100.dp).width(185.dp),
                    text = eventDescription,
                    style =
                        TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                )
                // button to join the event
                Button(
                    onClick = { /*TODO*/},
                    modifier = Modifier.align(Alignment.BottomCenter).width(165.dp),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                ) {
                    Text(
                        text = "Join Event",
                        style =
                            TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight(600),
                                color = MaterialTheme.colorScheme.onPrimary,
                            )
                    )
                }
                // contains the image and the button to show people who have joined the event
                Box(modifier = Modifier.align(Alignment.CenterEnd).width(150.dp).height(200.dp)) {
                    // image of the event
                    Image(
                        painter = painterResource(id = eventImage), // replace with actual image
                        contentDescription = eventTitle,
                        modifier =
                            Modifier.width(135.dp)
                                .height(135.dp)
                                .align(Alignment.TopEnd)
                                .clip(RoundedCornerShape(8.dp))
                    )
                    // button to show people who joined the event
                    Button(
                        onClick = { /*TODO*/},
                        modifier = Modifier.align(Alignment.BottomEnd).width(135.dp),
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.onSecondary
                            )
                    ) {
                        // icon of a person
                        Icon(
                            imageVector = Icons.Filled.Face,
                            contentDescription = "Show people who joined the event"
                        )
                        // text to show the number of people who joined the event
                        Text(
                            text =
                                if (eventPeopleMax <= 0) {
                                    "$eventPeople"
                                } else {
                                    "$eventPeople/$eventPeopleMax"
                                },
                            style =
                                TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight(600),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                )
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun EventInfoSheetPreview() {
    EventInfoSheet(
        showBottomSheet = remember { mutableStateOf(true) },
        eventTitle = "Bowling Tournament",
        eventOrganization = "Bowling club",
        eventDescription =
            "Individual tournament with 16 participants. Winner and loser brackets will be played at the same time. Amateur level.",
        eventDateTime = "15/05\n18:30",
        eventImage = 0,
        eventPeople = 0,
        eventPeopleMax = 0
    )
}
