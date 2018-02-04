package pozzoapps.thejournal.articles.list

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.ListView
import pozzoapps.thejournal.articles.model.Article

object ArticleListBinding {

  @JvmStatic
  @BindingAdapter("app:items")
  fun setItems(recyclerView: RecyclerView, items: List<Article>) {
    val adapter = recyclerView.adapter as ArticleListAdapter?
    if (adapter != null) {
      adapter.replaceData(items)
    }
  }
}
