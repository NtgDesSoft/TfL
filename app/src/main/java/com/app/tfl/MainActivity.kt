package com.app.tfl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.tfl.data.LineStatus
import com.app.tfl.ui.theme.TfLTheme
import com.app.tfl.viewModel.APITask
import com.app.tfl.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainUI(hiltViewModel())
        }
    }
}

@Preview(showBackground = true, widthDp = 420, heightDp = 640)
@Composable
fun Preview() {
    MainUI(hiltViewModel())
}

@Composable
fun MainUI(viewModel: MainViewModel) {TfLTheme {

Column() {




        Text(
            text = "Transport for London",
            modifier = Modifier
                .padding(start = 10.dp),
            style = MaterialTheme.typography.titleMedium
        )

    val apiState by viewModel.APIState.collectAsState()

    when (apiState) {
        is APITask.Loading -> Text(text = "Loading")
        is APITask.Response.Ok -> (apiState as APITask.Response.Ok).payload.forEach {


            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(1.dp),
                modifier = Modifier.padding(5.dp)
            ) {
                item {

                    Line(it

                    )
                }
            }


        }
        is APITask.Response.Error -> Text(text = (apiState as APITask.Response.Error).error)
        else -> {}
    }







}

}}

@Composable
fun Line(status: LineStatus) {
    Column(
        Modifier
            .background(color = status.colour)
            .padding(start = 10.dp)
            .background(color = MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = status.name)
            Text(text = status.statuses.Severity.orEmpty())
        }

        status.statuses.reason?.let {
            Divider(color = status.colour)
            Text(
                it,
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                )
        }

    }
}