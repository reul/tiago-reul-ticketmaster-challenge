package space.reul.imglytrial.app.ui.shared

import androidx.compose.runtime.MutableState

/**
 * Inverts the value of the state of a (Boolean) [MutableState.value].
 */
fun MutableState<Boolean>.toggle() {
    this.value = !this.value
}
