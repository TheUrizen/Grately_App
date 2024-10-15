package com.Stackhouse.android.gratitude

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.Stackhouse.android.gratitude.ui.theme.GratitudeTheme
import com.Stackhouse.android.gratitude.userinterface.AppTheme
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.Stackhouse.android.gratitude.userinterface.DrawerContent
import kotlinx.coroutines.launch
import java.time.LocalDateTime


val Context.dataStore by preferencesDataStore(name = "GratBlocks")
val TEXT_LIST_KEY = stringPreferencesKey("text_list_key")

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                    Greeting()
                }
            }
        }
    } 



@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val backgroundColor = MaterialTheme.colorScheme.background
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    var textList by remember { mutableStateOf(listOf<GratEntries>()) }
    var gratEntry by remember { mutableStateOf(GratEntries("", LocalDateTime.now())) }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController)
        },
        content = { 
            Scaffold(

                topBar = {
                    TopAppBar(
                        title = { Text("") },
                        navigationIcon = {
                            IconButton(onClick = {
                                if (drawerState.isOpen) {
                                    scope.launch { drawerState.close() }
                                } else {
                                    scope.launch { drawerState.open() }
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Menu",
                                    tint = Color.Black,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .border(2.dp, Color.Black)
                                )
                            }
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = backgroundColor
                        )
                    )
                },

                content = { paddingValues ->

                    NavHost(
                        navController = navController,
                        startDestination = "home", 
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable("home") { HomeScreen(context = context, onTextChange = { newText ->
                            gratEntry = gratEntry.copy(text = newText)
                        },
                            onTextListChange = { textList = it}, textList = textList, gratEntry = gratEntry) }
                        composable("record") { RecordedList(context = context, textList) }
                    }

                }
            )
        }
    )
}










