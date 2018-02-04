package pozzoapps.thejournal.architecture.base

import android.databinding.ObservableBoolean
import android.os.AsyncTask

class BaseBackground<T>(private val backgroundRunner: () -> T, private val foregroundResult: (T?) -> Unit,
    private val errorCallback: (Throwable) -> Unit, private val loading: ObservableBoolean)
    : AsyncTask<Void, Void, T>() {

  private var isSuccess = false
  private lateinit var error: Throwable

  override fun onPreExecute() {
    loading.set(true)
  }

  override fun doInBackground(vararg params: Void?): T? {
    try {
      val result = backgroundRunner()
      isSuccess = true
      return result
    } catch (e: Throwable) {
      e.printStackTrace()
      error = e
      //todo add logger
      return null
    }
  }

  override fun onPostExecute(result: T?) {
    loading.set(false)
    if (isSuccess) {
      foregroundResult(result)
    } else {
      errorCallback(error)
    }
  }
}
