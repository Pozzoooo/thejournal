package pozzoapps.thejournal.articles

import pozzoapps.thejournal.articles.ArticleRequest.Companion.ArticleRequestType
import pozzoapps.thejournal.articles.model.Article

/**
 * Network client to request Articles web services.
 */
open class ArticleClient(private val articleApi: ArticleApi) {

  open fun getArticleList(articleRequest: ArticleRequest): List<Article> {
    val call = if (ArticleRequestType.TAG == articleRequest.articleRequestType) {
      articleApi.byTag(articleRequest.filter)
    } else {
      articleApi.byPublication(articleRequest.filter)
    }

    val response = call.execute()
    return response.body()?.response?.articles ?: ArrayList()
  }
}
