package pozzoapps.thejournal.articles.details

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.databinding.Observable.OnPropertyChangedCallback
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.squareup.picasso.Picasso
import pozzoapps.thejournal.R
import pozzoapps.thejournal.architecture.base.BaseActivity
import pozzoapps.thejournal.architecture.injection.AppComponent
import pozzoapps.thejournal.articles.model.Article
import pozzoapps.thejournal.articles.model.Image.Companion.ImageType
import pozzoapps.thejournal.databinding.ActivityArticleDetailBinding

class ArticleDetailActivity : BaseActivity() {
  companion object {
    fun newIntent(context: Context, article: Article): Intent {
      val intent = Intent(context, ArticleDetailActivity::class.java)
      intent.putExtra(ArticleDetailFragment.ARTICLE, article)
      return intent
    }

    fun obtainViewModel(activity: FragmentActivity): ArticleDetailViewModel {
      return ViewModelProviders.of(activity).get(ArticleDetailViewModel::class.java)
    }
  }

  private lateinit var viewModel: ArticleDetailViewModel
  private lateinit var binding: ActivityArticleDetailBinding

  override fun onCreateComponent(appComponent: AppComponent) {
    DaggerArticleDetailComponent.builder()
        .appComponent(appComponent)
        .build()
        .inject(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_article_detail)
    viewModel = obtainViewModel(this)
    binding.viewModel = viewModel
    setupArticle()
    setupFragment()
  }

  private fun setupArticle() {
    viewModel.article.addOnPropertyChangedCallback(object : OnPropertyChangedCallback() {
      override fun onPropertyChanged(observable: Observable?, id: Int) {
        val article = viewModel.article.get()

        val image = article.images[ImageType.SUPER_WIDE_1.type]?.image
        if (image != null) {
          Picasso.with(this@ArticleDetailActivity).load(image).into(binding.articleImage)
        }
      }
    })
  }

  private fun setupFragment() {
    val article = intent.getParcelableExtra<Article>(ArticleDetailFragment.ARTICLE)
    val articleDetailFragment = ArticleDetailFragment.newInstance(article)
    addFragment(articleDetailFragment, R.id.articleDetailContainer)
  }
}
