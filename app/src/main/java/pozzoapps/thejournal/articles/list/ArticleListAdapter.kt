package pozzoapps.thejournal.articles.list

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import pozzoapps.thejournal.articles.list.ArticleListAdapter.Companion.ArticleViewHolder
import pozzoapps.thejournal.articles.model.Article
import pozzoapps.thejournal.articles.model.Image.Companion.ImageType
import pozzoapps.thejournal.databinding.ItemArticlePreviewBinding

class ArticleListAdapter(private var articleList: List<Article>, private val viewModel: ArticleListViewModel)
  : RecyclerView.Adapter<ArticleViewHolder>() {

  override fun getItemCount(): Int = articleList.size

  override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
    holder.setArticle(articleList[position])
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val biding = ItemArticlePreviewBinding.inflate(inflater, parent, false)
    biding.viewModel = viewModel
    return ArticleViewHolder(biding)
  }

  fun replaceData(articleList: List<Article>) {
    this.articleList = articleList
    notifyDataSetChanged()
  }

  companion object {
    class ArticleViewHolder(private val binding: ItemArticlePreviewBinding) : ViewHolder(binding.root) {

      fun setArticle(article: Article) {
        binding.article = article
        val thumbnail = article.images[ImageType.THUMBNAIL.type]?.image
        if (thumbnail != null) {
          Picasso.with(binding.root.context).load(thumbnail).into(binding.articleIcon)
        } else {
          binding.articleIcon.setImageDrawable(null)
        }
      }
    }
  }
}
