package space.reul.ticketmasterchallenge.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import dagger.hilt.android.AndroidEntryPoint
import space.reul.ticketmasterchallenge.app.ui.composables.Events
import space.reul.ticketmasterchallenge.app.ui.theme.TicketmasterChallengeTheme
import space.reul.ticketmasterchallenge.app.ui.uistate.ScreenWrapper

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(viewModel)

        setContent {
            TicketmasterChallengeTheme {

                val scrollBehavior =
                    TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
                val eventsFlow = viewModel.eventsFlow.collectAsState()
                val isConnectedFlow = viewModel.networkAvailableFlow.collectAsState()

                Scaffold(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        TopAppBar(
                            title = { Text(text = stringResource(id = R.string.app_name)) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            scrollBehavior = scrollBehavior
                        )
                    },

                    ) { innerPadding ->

                    Surface(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .fillMaxSize()
                    ) {
                        // A surface container using the 'background' color from the theme
                        ScreenWrapper(
                            uiState = eventsFlow.value,
                            showDisconnectedMessage = !isConnectedFlow.value
                        ) { modifier, data ->
                            Events(modifier, data)
                        }
                    }
                }
            }
        }
    }
}
