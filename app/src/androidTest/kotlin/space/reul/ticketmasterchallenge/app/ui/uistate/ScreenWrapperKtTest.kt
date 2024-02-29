package space.reul.ticketmasterchallenge.app.ui.uistate

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import space.reul.imglytrial.app.ui.state.UiState
import space.reul.ticketmasterchallenge.app.ui.theme.TicketmasterChallengeTheme

class ScreenWrapperKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun verifyNoConnectionMessageIsDisplayedWhenDisconnected() {
        composeTestRule.setContent {
            TicketmasterChallengeTheme {


                ScreenWrapper(uiState = UiState.Failure(Exception("No connection")),
                    showDisconnectedMessage = true,
                    contents = { _, _ -> })

            }
        }
        composeTestRule.onNodeWithText("Network unavailable").assertIsDisplayed()

    }

    @Test
    fun verifyNoConnectionMessageIsHiddenWhenConnected() {
        composeTestRule.setContent {
            TicketmasterChallengeTheme {
                ScreenWrapper(uiState = UiState.Failure(Exception("No connection")),
                    showDisconnectedMessage = false,
                    contents = { _, _ -> })

            }
        }
        composeTestRule.onNodeWithText("Network unavailable").isNotDisplayed()
    }

}