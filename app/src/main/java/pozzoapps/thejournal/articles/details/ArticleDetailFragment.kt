package pozzoapps.thejournal.articles.details

import android.arch.lifecycle.ViewModelProviders
import android.databinding.Observable
import android.databinding.Observable.OnPropertyChangedCallback
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pozzoapps.thejournal.architecture.base.BaseFragment
import pozzoapps.thejournal.architecture.injection.AppComponent
import pozzoapps.thejournal.articles.details.ArticleDetailActivity.Companion
import pozzoapps.thejournal.articles.model.Article
import pozzoapps.thejournal.databinding.FragmentArticleDetailBinding

class ArticleDetailFragment : BaseFragment() {
  companion object {
    const val ARTICLE = "article"

    fun newInstance(article: Article): ArticleDetailFragment {
      val arguments = Bundle()
      arguments.putParcelable(ARTICLE, article)

      val fragment = ArticleDetailFragment()
      fragment.arguments = arguments
      return fragment
    }
  }

  private lateinit var viewModel: ArticleDetailViewModel
  private lateinit var binding: FragmentArticleDetailBinding

  override fun onCreateComponent(appComponent: AppComponent) {
    DaggerArticleDetailComponent.builder()
        .appComponent(appComponent)
        .build()
        .inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
    viewModel = ArticleDetailActivity.obtainViewModel(activity)
    binding.viewModel = viewModel
    setupArticle()
    return binding.root
  }

  private fun setupArticle() {
    viewModel.article.addOnPropertyChangedCallback(object : OnPropertyChangedCallback() {
      override fun onPropertyChanged(observable: Observable?, id: Int) {
        val article = viewModel.article.get()
        binding.articleContent.loadData(article.content, "text/html", "utf-8")
      }
    })
  }

  override fun onResume() {
    super.onResume()
    viewModel.start(arguments.getParcelable(ARTICLE))
  }
}
