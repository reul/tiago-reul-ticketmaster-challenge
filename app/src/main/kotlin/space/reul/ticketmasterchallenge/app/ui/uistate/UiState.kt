package space.reul.imglytrial.app.ui.state

/**
 * Represents a state that should be reflected by the UI.
 *
 * @param T the type of the data in a success state.
 */
sealed class UiState<out T> {
    /**
     * Loading state.
     */
    data object Loading : UiState<Nothing>()

    /**
     * Success state.
     * @property data Data to be presented by the UI.
     */
    data class Success<T>(val data: T) : UiState<T>()

    /**
     * Failure state.
     *
     * @property exception The exception that was thrown, to be handled by the UI.
     */
    data class Failure(val exception: Exception) : UiState<Nothing>()
}