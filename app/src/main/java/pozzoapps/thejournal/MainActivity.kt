package pozzoapps.thejournal

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.navigation
import pozzoapps.thejournal.architecture.base.BaseActivity
import pozzoapps.thejournal.architecture.injection.AppComponent
import pozzoapps.thejournal.articles.ArticleRequest
import pozzoapps.thejournal.articles.ArticleRequest.Companion.ArticleRequestType.PUBLICATION
import pozzoapps.thejournal.articles.ArticleRequest.Companion.ArticleRequestType.TAG
import pozzoapps.thejournal.articles.list.ArticleListFragment

class MainActivity : BaseActivity() {
  override fun onCreateComponent(appComponent: AppComponent) {
  }

  private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
    when (item.itemId) {
      R.id.homeTab -> {
        openFeed(ArticleRequest(PUBLICATION, "thejournal"))
        return@OnNavigationItemSelectedListener true
      }
      R.id.businessTab -> {
        openFeed(ArticleRequest(PUBLICATION, "businessetc"))
        return@OnNavigationItemSelectedListener true
      }
      R.id.techTab -> {
        openFeed(ArticleRequest(TAG, "google"))
        return@OnNavigationItemSelectedListener true
      }
    }
    false
  }

  private fun openFeed(articleRequest: ArticleRequest) {
    replaceFragment(ArticleListFragment.newInstance(articleRequest), R.id.tabContent)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    navigation.selectedItemId = R.id.homeTab
  }
}
