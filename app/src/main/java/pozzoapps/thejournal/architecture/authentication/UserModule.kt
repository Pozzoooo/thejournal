package pozzoapps.thejournal.architecture.authentication

import dagger.Module
import dagger.Provides

@Module
class UserModule {

  @Provides
  fun provideLoggedInUser(): User {
    //todo need to safe it
    return User(username = "sample", password = "eferw5wr335£65")
  }
}
