package pozzoapps.thejournal.articles.model

import android.annotation.SuppressLint
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Parcelable
import android.text.Html
import android.text.Spanned
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
class Article(val id: Int,
    val type: String,
    val title: String,
    val content: String,
    val images: HashMap<String, Image>) : Parcelable {
  companion object {
    enum class ArticleType(val type: String) {
      POST("post");
    }
  }

  fun contentPreview(): Spanned {
    return if (VERSION.SDK_INT >= VERSION_CODES.N) {
      Html.fromHtml(content.substring(0, 250), Html.FROM_HTML_MODE_COMPACT)
    } else {
      return Html.fromHtml(content.substring(0, 250))
    }
  }
}
