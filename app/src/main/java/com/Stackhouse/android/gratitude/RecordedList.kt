package com.Stackhouse.android.gratitude

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun RecordedList(context: Context, textList: List<GratEntries>) {
    val dateFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(16.dp)
    ) {
        items(textList.size) { index ->
            val storedEntry = textList[index]
            var expanded by remember { mutableStateOf(false) }
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
                    .padding(20.dp)
                    .width(175.dp)
                    .height(120.dp)
                    .clickable { expanded = true },
                contentAlignment = Alignment.TopCenter
            ) {
                val formattedDate = storedEntry.date.format(dateFormatter)
                Text(
                    text = "$formattedDate \n ${storedEntry.text}",
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis

                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {

                    DropdownMenuItem(text = { Text(text = storedEntry.text) },
                        onClick = { expanded = false })


            }
        }
    }
}








}



