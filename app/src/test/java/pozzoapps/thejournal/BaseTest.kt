package pozzoapps.thejournal

import org.mockito.Mockito
import pozzoapps.thejournal.architecture.App
import pozzoapps.thejournal.architecture.injection.AppComponent
import pozzoapps.thejournal.architecture.injection.AppModule
import pozzoapps.thejournal.architecture.injection.DaggerAppComponent

open class BaseTest {
   fun getAppComponent(): AppComponent {
       val app = Mockito.mock(App::class.java)

       return DaggerAppComponent.builder()
               .appModule(AppModule(app))
               .build()
   }
}
