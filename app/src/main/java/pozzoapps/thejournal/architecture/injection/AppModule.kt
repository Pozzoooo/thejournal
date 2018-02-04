package pozzoapps.thejournal.architecture.injection

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val mApplication: Application) {

  @Provides
  @Singleton
  internal fun providesApplication(): Application {
    return mApplication
  }
}
