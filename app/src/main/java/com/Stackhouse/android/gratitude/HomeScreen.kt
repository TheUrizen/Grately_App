package com.Stackhouse.android.gratitude

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    context: android.content.Context,
    onTextChange: (String) -> Unit,
    textList: List<GratEntries>,
    onTextListChange: (List<GratEntries>) -> Unit,
    gratEntry: GratEntries
)
{
    val dateFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)

    val scope = rememberCoroutineScope()

    LaunchedEffect(textList) {
         saveTextList(context, textList)
        }



    Box(
        modifier = Modifier
            .fillMaxSize(1f)
            .background((MaterialTheme.colorScheme.background))


    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.6f)
            //.background(Color.Blue)


        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = gratEntry.text,
                    onValueChange = onTextChange,
                    label = { Text("What are you grateful for?") },
                    modifier = Modifier
                        .border(2.dp, Color.DarkGray),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        containerColor = Color.Transparent,


                    )
                )



                Button(
                    onClick = {
                        if (gratEntry.text.isNotBlank()) {
                            val newEntry = GratEntries(gratEntry.text, LocalDateTime.now())
                            val updatedList = textList + newEntry
                            onTextListChange(updatedList)
                            scope.launch {
                                saveTextList(context, updatedList)
                            }
                            onTextChange("")
                        }
                    }

                ) {
                    Text("Add to List")
                }

                Text("Stored Texts: ")


                textList.forEach { storedEntry ->
                    val formattedDate = storedEntry.date.format(dateFormatter)
                    Text(text = "${storedEntry.text} - $formattedDate", color = Color.Black)

                }

            }


        }


    }





}
