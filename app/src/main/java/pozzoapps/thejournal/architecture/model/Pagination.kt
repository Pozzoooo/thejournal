package pozzoapps.thejournal.architecture.model

class Pagination {
  var totalResults: Int = 0
  var currentPage: Int = 0
  lateinit var unpublished: List<String>
}
