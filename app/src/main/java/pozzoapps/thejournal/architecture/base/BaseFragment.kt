package pozzoapps.thejournal.architecture.base

import android.os.Bundle
import android.support.v4.app.Fragment
import pozzoapps.thejournal.architecture.App
import pozzoapps.thejournal.architecture.injection.AppComponent

/**
 * Provides basic abstraction of a fragment, should improve development speed by implementing
 *  generic functionalities and development tools.
 * Should also tend to help maintaining implementation similar through the application.
 */
abstract class BaseFragment: Fragment() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val app = App.get()
    onCreateComponent(app.component())
  }

  protected abstract fun onCreateComponent(appComponent: AppComponent)
}
