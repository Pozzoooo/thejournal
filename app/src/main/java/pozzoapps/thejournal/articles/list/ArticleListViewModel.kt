package pozzoapps.thejournal.articles.list

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableList
import pozzoapps.thejournal.architecture.base.BaseBackground
import pozzoapps.thejournal.architecture.base.BaseViewModel
import pozzoapps.thejournal.architecture.injection.AppComponent
import pozzoapps.thejournal.articles.ArticleBusiness
import pozzoapps.thejournal.articles.ArticleModule
import pozzoapps.thejournal.articles.ArticleRequest
import pozzoapps.thejournal.articles.model.Article
import javax.inject.Inject

class ArticleListViewModel : BaseViewModel() {
  val openArticleEvent = MutableLiveData<Article>()
  val isLoading = ObservableBoolean(false)
  val articleList: ObservableList<Article> = ObservableArrayList<Article>()
  val errorMessage = ObservableField<String>()

  private lateinit var articleRequest: ArticleRequest

  @Inject
  lateinit var articleBusiness: ArticleBusiness

  override fun onCreateComponent(appComponent: AppComponent) {
    DaggerArticleListComponent.builder()
        .appComponent(appComponent)
        .articleModule(ArticleModule())
        .build()
        .inject(this)
  }

  public fun start(articleRequest: ArticleRequest) {
    super.start()
    this.articleRequest = articleRequest
    loadArticleList()
  }

  private fun loadArticleList() {
    BaseBackground<List<Article>>(
        { -> articleBusiness.getArticleList(articleRequest)},
        { articleList -> setArticleList(articleList)},
        { error -> showError(error) },
        isLoading
    ).execute()
  }

  private fun setArticleList(articleList: List<Article>?) {
    if (articleList != null) {
      this.articleList.clear()
      this.articleList.addAll(articleList)
    }
  }

  private fun showError(error: Throwable) {
    errorMessage.set(error.message)
  }

  fun getErrorMessageObserver(): ObservableField<String> = errorMessage

  fun openArticle(article: Article) {
    openArticleEvent.value = article
  }
}
