package pozzoapps.thejournal.articles

import pozzoapps.thejournal.articles.model.Article
import pozzoapps.thejournal.articles.model.Article.Companion.ArticleType

/**
 * @see Article
 */
class ArticleBusiness(private val articleClient: ArticleClient) {

  fun getArticleList(articleRequest: ArticleRequest): List<Article> {
    val articleList = articleClient.getArticleList(articleRequest)
    return filterPostTypes(articleList)
  }

  private fun filterPostTypes(articleList: List<Article>): List<Article> =
      articleList.filter { it.type == ArticleType.POST.type }
}
