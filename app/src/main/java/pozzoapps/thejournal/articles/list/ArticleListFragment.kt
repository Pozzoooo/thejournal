package pozzoapps.thejournal.articles.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.Observable
import android.databinding.Observable.OnPropertyChangedCallback
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_article_list.articleList
import pozzoapps.thejournal.architecture.base.BaseFragment
import pozzoapps.thejournal.architecture.injection.AppComponent
import pozzoapps.thejournal.articles.ArticleModule
import pozzoapps.thejournal.articles.ArticleRequest
import pozzoapps.thejournal.articles.details.ArticleDetailActivity
import pozzoapps.thejournal.articles.model.Article
import pozzoapps.thejournal.databinding.FragmentArticleListBinding

class ArticleListFragment : BaseFragment() {
  companion object {
    const val ARTICLE_REQUEST = "articleRequest"

    fun newInstance(articleRequest: ArticleRequest): ArticleListFragment {
      val fragment = ArticleListFragment()
      val args = Bundle()
      args.putParcelable(ARTICLE_REQUEST, articleRequest)
      fragment.arguments = args
      return fragment
    }
  }

  private lateinit var viewModel: ArticleListViewModel
  private lateinit var adapter: ArticleListAdapter
  private lateinit var binding: FragmentArticleListBinding

  override fun onCreateComponent(appComponent: AppComponent) {
    DaggerArticleListComponent.builder()
        .appComponent(appComponent)
        .articleModule(ArticleModule())
        .build()
        .inject(this)

    viewModel = ViewModelProviders.of(this).get(ArticleListViewModel::class.java)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentArticleListBinding.inflate(inflater, container, false)
    binding.viewModel = viewModel
    setupRefreshing()
    setupOpenArticleObserver()
    setupErrorMessage()
    return binding.root
  }

  private fun setupRefreshing() {
    binding.refreshLayout.isEnabled = false //todo proper refresh
    viewModel.isLoading.addOnPropertyChangedCallback(object : OnPropertyChangedCallback() {
      override fun onPropertyChanged(observable: Observable?, id: Int) {
        binding.refreshLayout.isRefreshing = viewModel.isLoading.get()
      }
    })
  }

  private fun setupOpenArticleObserver() {
    viewModel.openArticleEvent.observe(this, Observer<Article> { article ->
      if (article != null) {
        startActivity(ArticleDetailActivity.newIntent(activity, article))
      }
    })
  }

  private fun setupErrorMessage() {
    viewModel.errorMessage.addOnPropertyChangedCallback(object : OnPropertyChangedCallback() {
      override fun onPropertyChanged(observable: Observable?, id: Int) {
        val errorMessage = viewModel.errorMessage.get()
        val view = this@ArticleListFragment.view
        if (view != null) {
          Snackbar.make(view, errorMessage, Snackbar.LENGTH_LONG).show()
        }
      }
    })
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setupListAdapter()
    viewModel.start(arguments.getParcelable(ARTICLE_REQUEST))
  }

  private fun setupListAdapter() {
    adapter = ArticleListAdapter(ArrayList<Article>(0), viewModel)
    articleList.adapter = adapter
  }
}
