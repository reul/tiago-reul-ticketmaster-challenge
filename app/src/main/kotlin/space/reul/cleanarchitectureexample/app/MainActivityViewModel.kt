package space.reul.cleanarchitectureexample.app

import android.app.Application
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import space.reul.cleanarchitectureexample.domain.model.EventList
import space.reul.cleanarchitectureexample.domain.usecase.ListEvents
import space.reul.imglytrial.app.ui.state.UiState
import javax.inject.Inject


/**
 * View model for the main activity.
 *
 * @property repository The repository to get the events from.
 * @property savedStateHandle The saved state handle to use for the view model.
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val application: Application,
    private val repository: ListEvents.Repository,
    private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    private val _eventsFlow: MutableStateFlow<UiState<EventList>> =
        MutableStateFlow(UiState.Loading)
    val eventsFlow: StateFlow<UiState<EventList>> = _eventsFlow

    private val _networkAvailableFlow = MutableStateFlow(isNetworkUnavailable())
    val networkAvailableFlow: StateFlow<Boolean> = _networkAvailableFlow

    private var loadTask: Job? = null

    private val networkRequest =
        NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).build()


    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: android.net.Network) {
            super.onAvailable(network)
            load()
            _networkAvailableFlow.value = true
        }

        override fun onLost(network: android.net.Network) {
            super.onLost(network)
            if (_eventsFlow.value is UiState.Loading) {
                _eventsFlow.value = UiState.Failure(Exception("Network unavailable"))
            }
            _networkAvailableFlow.value = false
        }
    }

    fun load() {
        if (loadTask?.isActive == true) return

        loadTask = viewModelScope.launch {
            try {
                val listEvents = newUseCase()
                val output = listEvents().also {
                    Log.d("MainActivityViewModel", "newUseCase output: $it")
                }
                _eventsFlow.value = UiState.Success(output)

            } catch (t: Throwable) {
                _eventsFlow.value = UiState.Failure(t as Exception)

            }
        }
    }

    fun onResume() {
        load()
        application.getSystemService(ConnectivityManager::class.java)
            .registerNetworkCallback(networkRequest, networkCallback)
    }

    fun onPause() {
        loadTask?.cancel()
        application.getSystemService(ConnectivityManager::class.java)
            .unregisterNetworkCallback(networkCallback)

    }

    private fun newUseCase() = ListEvents(Dispatchers.IO, repository)

    private fun isNetworkUnavailable(): Boolean {
        val connectivityManager =
            application.applicationContext.getSystemService(ConnectivityManager::class.java)

        val networkInfo =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return (networkInfo != null && (networkInfo.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && networkInfo.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_VALIDATED
        ) && (networkInfo.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || networkInfo.hasTransport(
            NetworkCapabilities.TRANSPORT_VPN
        ) || networkInfo.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkInfo.hasTransport(
            NetworkCapabilities.TRANSPORT_ETHERNET
        ))))
    }
}