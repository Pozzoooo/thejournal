package pozzoapps.thejournal.articles.details

import android.databinding.ObservableField
import pozzoapps.thejournal.architecture.base.BaseViewModel
import pozzoapps.thejournal.architecture.injection.AppComponent
import pozzoapps.thejournal.articles.model.Article

class ArticleDetailViewModel : BaseViewModel() {
  val article = ObservableField<Article>()

  override fun onCreateComponent(appComponent: AppComponent) {
    DaggerArticleDetailComponent.builder()
        .appComponent(appComponent)
        .build()
        .inject(this)
  }

  fun start(article: Article) {
    super.start()
    this.article.set(article)
  }
}
