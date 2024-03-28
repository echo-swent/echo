package com.github.swent.echo.compose.authentication

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.github.swent.echo.viewmodels.authentication.AuthenticationState
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AuthenticationScreenTest {

    companion object {
        const val ACTION = "Action Button"
    }

    @get:Rule val composeTestRule = createComposeRule()

    private var authenticationCount = 0
    private var email = ""
    private var password = ""

    private fun onAuthenticate(email: String, password: String) {
        this.email = email
        this.password = password
        authenticationCount++
    }

    @Before
    fun setUp() {
        authenticationCount = 0
        email = ""
        password = ""
    }

    @Test
    fun shouldHaveLoginButtonAndInputFieldsWhenIsSignedOut() {
        composeTestRule.setContent {
            AuthenticationScreen(ACTION, AuthenticationState.SignedOut, this::onAuthenticate)
        }
        composeTestRule.onNodeWithText(ACTION).assertExists().assertHasClickAction()
        composeTestRule.onNodeWithTag("email-field").assertExists()
        composeTestRule.onNodeWithTag("password-field").assertExists()
        composeTestRule.onNodeWithTag("error-message").assertDoesNotExist()
    }

    @Test
    fun shouldCallOnAuthenticateWithCorrectParametersWhenActionButtonIsPressedInSignedOutState() {
        composeTestRule.setContent {
            AuthenticationScreen(ACTION, AuthenticationState.SignedOut, this::onAuthenticate)
        }
        composeTestRule.onNodeWithTag("email-field").performTextInput("test@test.test")
        composeTestRule.onNodeWithTag("password-field").performTextInput("password")
        assertEquals(0, authenticationCount)
        composeTestRule.onNodeWithTag("action-button").performClick()
        assertEquals(1, authenticationCount)
        assertEquals("test@test.test", email)
        assertEquals("password", password)
    }

    @Test
    fun shouldHaveSigningInTextWhenIsSigningIn() {
        composeTestRule.setContent {
            AuthenticationScreen(ACTION, AuthenticationState.SigningIn, this::onAuthenticate)
        }
        composeTestRule.onNodeWithText("Signing in...").assertExists()
        assertEquals(0, authenticationCount)
    }

    @Test
    fun shouldHaveSignedInTextWhenIsSignedIn() {
        composeTestRule.setContent {
            AuthenticationScreen(ACTION, AuthenticationState.SignedIn, this::onAuthenticate)
        }
        composeTestRule.onNodeWithText("Signed in").assertExists()
        assertEquals(0, authenticationCount)
    }

    @Test
    fun shouldHaveErrorTextWhenIsError() {
        val message = "Error message"
        composeTestRule.setContent {
            AuthenticationScreen(ACTION, AuthenticationState.Error(message), this::onAuthenticate)
        }
        composeTestRule.onNodeWithText(message).assertExists()
    }

    @Test
    fun shouldHaveLoginButtonAndInputFieldsWhenIsError() {
        val message = "Error message"
        composeTestRule.setContent {
            AuthenticationScreen(ACTION, AuthenticationState.Error(message), this::onAuthenticate)
        }
        composeTestRule.onNodeWithText(ACTION).assertExists().assertHasClickAction()
        composeTestRule.onNodeWithTag("email-field").assertExists()
        composeTestRule.onNodeWithTag("password-field").assertExists()
        composeTestRule.onNodeWithTag("error-message").assertExists()
    }

    @Test
    fun shouldCallOnAuthenticateWithCorrectParametersWhenActionButtonIsPressedInErrorState() {
        composeTestRule.setContent {
            AuthenticationScreen(ACTION, AuthenticationState.Error("Error"), this::onAuthenticate)
        }
        composeTestRule.onNodeWithTag("email-field").performTextInput("test@test.test")
        composeTestRule.onNodeWithTag("password-field").performTextInput("password")
        composeTestRule.onNodeWithTag("action-button").performClick()
        assertEquals(1, authenticationCount)
        assertEquals("test@test.test", email)
        assertEquals("password", password)
    }
}
