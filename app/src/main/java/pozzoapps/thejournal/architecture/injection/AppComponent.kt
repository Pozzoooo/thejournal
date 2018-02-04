package pozzoapps.thejournal.architecture.injection

import android.app.Application
import com.google.gson.Gson
import pozzoapps.thejournal.architecture.model.Site
import dagger.Component
import okhttp3.OkHttpClient
import pozzoapps.thejournal.architecture.authentication.User
import pozzoapps.thejournal.architecture.authentication.UserModule
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Root component.
 */
@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class), (UserModule::class), (SiteModule::class)])
interface AppComponent {
  //App
  fun app(): Application

  //Network
  fun gson(): Gson

  fun okHttpClient(): OkHttpClient
  fun retrofitBuilder(): Retrofit.Builder

  //Session
  fun loggedInUser(): User

  //Site
  fun site(): Site
}
