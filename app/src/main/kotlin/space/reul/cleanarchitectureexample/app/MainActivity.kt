package space.reul.cleanarchitectureexample.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import space.reul.cleanarchitectureexample.app.ui.composables.Events
import space.reul.cleanarchitectureexample.app.ui.theme.CleanArchitectureExampleTheme
import space.reul.cleanarchitectureexample.app.ui.uistate.ScreenWrapper

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CleanArchitectureExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    val eventsFlow = viewModel.eventsFlow.collectAsState()
                    val isConnectedFlow = viewModel.networkAvailableFlow.collectAsState()

                    ScreenWrapper(
                        modifier=Modifier.fillMaxSize(),
                        uiState=eventsFlow.value,
                        showDisconnectedMessage = !isConnectedFlow.value
                        ) { modifier, data ->
                        Events(modifier, data)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }
}
