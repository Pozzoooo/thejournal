package pozzoapps.thejournal.architecture.base

import android.arch.lifecycle.ViewModel
import pozzoapps.thejournal.architecture.App
import pozzoapps.thejournal.architecture.injection.AppComponent

abstract class BaseViewModel : ViewModel() {

  protected open fun start() {
    onCreateComponent(App.get().component())
  }

  protected abstract fun onCreateComponent(appComponent: AppComponent)
}