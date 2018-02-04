package pozzoapps.thejournal.articles

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import pozzoapps.thejournal.articles.ArticleRequest.Companion.ArticleRequestType.PUBLICATION
import pozzoapps.thejournal.articles.model.Article
import pozzoapps.thejournal.articles.model.Article.Companion.ArticleType

class ArticleBusinessTest {
  @Mock
  private lateinit var articleClient: ArticleClient

  private lateinit var articleList: MutableList<Article>
  private lateinit var articleBusiness: ArticleBusiness
  private lateinit var articleRequest: ArticleRequest

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    articleRequest = ArticleRequest(PUBLICATION, "any")

    articleList = ArrayList()
    articleList.add(Article(1, ArticleType.POST.type, "title", "content", HashMap()))
    articleList.add(Article(2, "notPost", "title2", "content2", HashMap()))

    Mockito.doReturn(articleList).`when`(articleClient).getArticleList(articleRequest)

    articleBusiness = ArticleBusiness(articleClient)
  }

  @Test
  fun articleListShouldBeReturned() {
    val resultList = articleBusiness.getArticleList(articleRequest)

    assertNotNull(resultList)
    assertFalse("result should not be empty", resultList.isEmpty())
  }

  @Test
  fun onlyPostShouldBeReturned() {
    val resultList = articleBusiness.getArticleList(articleRequest)

    resultList.forEach {
      assertEquals(ArticleType.POST.type, it.type)
    }
  }
}
