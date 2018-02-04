package pozzoapps.thejournal.architecture.injection

import pozzoapps.thejournal.architecture.model.Site
import dagger.Module
import dagger.Provides

@Module
class SiteModule {

  @Provides
  fun provideSite(): Site {
    //todo should be setup after login
    val site = Site(baseUrl = "http://api.thejournal.ie/v3/sample/")
    return site
  }
}
