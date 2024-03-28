package com.github.swent.echo.fakes

import com.github.swent.echo.authentication.AuthenticationResult
import com.github.swent.echo.authentication.AuthenticationService
import java.util.concurrent.CompletableFuture
import kotlinx.coroutines.future.await

/** A fake implementation of the authentication service. */
class FakeAuthenticationService : AuthenticationService {
    private lateinit var signedInResult: CompletableFuture<AuthenticationResult>

    /** The user ID to return when [getCurrentUserID] is called. */
    var userID: String? = null

    /**
     * Completes the current signing operation with the given result. Make sure to call this method
     * after calling [signIn], [signUp], or [signOut].
     *
     * @param result The result of the signing operation.
     */
    fun completeSigningOperation(result: AuthenticationResult) {
        signedInResult.complete(result)
    }

    override suspend fun signIn(email: String, password: String): AuthenticationResult {
        signedInResult = CompletableFuture()
        return signedInResult.await()
    }

    override suspend fun signUp(email: String, password: String): AuthenticationResult {
        signedInResult = CompletableFuture()
        return signedInResult.await()
    }

    override suspend fun signOut(): AuthenticationResult {
        signedInResult = CompletableFuture()
        return signedInResult.await()
    }

    override fun getCurrentUserID(): String? {
        return userID
    }
}
