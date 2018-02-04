package pozzoapps.thejournal.architecture

import android.app.Application
import android.content.Context
import pozzoapps.thejournal.architecture.injection.AppComponent
import pozzoapps.thejournal.architecture.injection.AppModule
import pozzoapps.thejournal.architecture.injection.DaggerAppComponent

/**
 * App initialization.
 */
class App : Application() {
  companion object {
    private lateinit var instance: App

    fun get(): App {
      return instance
    }
  }

  private lateinit var appComponent: AppComponent

  override fun onCreate() {
    super.onCreate()

    instance = this
    initComponent()
  }

  fun component(): AppComponent {
    return appComponent
  }

  private val appModule: AppModule
    get() = AppModule(this)

  private fun initComponent() {
    appComponent = DaggerAppComponent.builder()
        .appModule(appModule)
        .build()
  }
}
