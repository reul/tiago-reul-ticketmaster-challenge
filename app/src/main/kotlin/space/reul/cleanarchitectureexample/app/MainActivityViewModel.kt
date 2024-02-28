package space.reul.cleanarchitectureexample.app

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
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
    private val repository: ListEvents.Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val eventsStateFlow: MutableStateFlow<UiState<EventList>> = MutableStateFlow(UiState.Loading)
    val eventsFlow: StateFlow<UiState<EventList>> = eventsStateFlow
    private var loadTask: Job? = null

    fun onResume() {
        if (loadTask?.isActive == true) return

        loadTask = viewModelScope.launch {
            try {
                val listEvents = newUseCase()
                val output = listEvents().also {
                    Log.d("MainActivityViewModel", "newUseCase output: $it")
                }
                eventsStateFlow.value = UiState.Success(output)

            } catch (t: Throwable) {
                eventsStateFlow.value = UiState.Failure(t as Exception)

            }
        }
    }

    private fun newUseCase() = ListEvents(Dispatchers.IO, repository)
}
