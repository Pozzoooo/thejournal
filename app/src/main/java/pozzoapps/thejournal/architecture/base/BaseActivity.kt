package pozzoapps.thejournal.architecture.base

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import pozzoapps.thejournal.R
import pozzoapps.thejournal.architecture.App
import pozzoapps.thejournal.architecture.injection.AppComponent

/**
 * Provides basic abstraction of an activity, should improve development speed by implementing
 *  generic functionalities and development tools.
 * Should also tend to help maintaining implementation similar through the application.
 */
abstract class BaseActivity : FragmentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val app = App.get()
    onCreateComponent(app.component())
  }

  protected abstract fun onCreateComponent(appComponent: AppComponent)

  fun showLoading() {
    Toast.makeText(this, R.string.loading, Toast.LENGTH_SHORT).show()
  }

  fun hideLoading() {
    Toast.makeText(this, R.string.loaded, Toast.LENGTH_SHORT).show()
  }

  fun error(error: Throwable) {
    val message = getString(R.string.error) + " " + error.localizedMessage
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
  }

  fun addFragment(fragment: BaseFragment, id: Int) {
    supportFragmentManager.beginTransaction()
        .add(id, fragment)
        .commit()
  }

  fun replaceFragment(fragment: BaseFragment, id: Int) {
    supportFragmentManager.beginTransaction()
        .replace(id, fragment)
        .commit()
  }
}
