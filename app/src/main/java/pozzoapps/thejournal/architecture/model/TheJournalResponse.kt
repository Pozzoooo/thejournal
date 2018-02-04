package pozzoapps.thejournal.architecture.model

/**
 * Generic higher level response from The Journal web services.
 */
open class TheJournalResponse<T> {
  var status: Boolean = false
  var response: T? = null
  lateinit var rendered: String
}
