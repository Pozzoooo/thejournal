package pozzoapps.thejournal.architecture.authentication

import dagger.Module
import dagger.Provides

@Module(includes = [(UserModule::class)])
class LoginModule {

  @Provides
  fun provideBasicAuthorizationInterceptor(loggedInUser: User): BasicAuthorizationInterceptor {
    return BasicAuthorizationInterceptor(loggedInUser)
  }
}
