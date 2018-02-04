package pozzoapps.thejournal.articles.model

import pozzoapps.thejournal.architecture.model.Pagination
import pozzoapps.thejournal.architecture.model.TheJournalResponse

class ArticleList {
  lateinit var articles: List<Article>
  lateinit var pagination: Pagination
}
