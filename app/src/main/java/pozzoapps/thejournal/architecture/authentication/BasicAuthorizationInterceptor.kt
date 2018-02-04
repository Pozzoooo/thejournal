package pozzoapps.thejournal.architecture.authentication

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Response

/**
 * OkHttp interceptor to handle Basic authenticator.
 */
class BasicAuthorizationInterceptor(val loggedInUser: User) : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val userCredentials: String = loggedInUser.credentials()
    val basicAuthorization = "Basic " + Base64.encodeToString(
        userCredentials.toByteArray(), Base64.NO_WRAP or Base64.URL_SAFE)

    val original = chain.request()

    val requestBuilder = original.newBuilder().header("Authorization", basicAuthorization)
    requestBuilder.method(original.method(), original.body())

    val request = requestBuilder.build()
    return chain.proceed(request)
  }
}
