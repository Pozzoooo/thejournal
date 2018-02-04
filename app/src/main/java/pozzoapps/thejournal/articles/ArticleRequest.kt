package pozzoapps.thejournal.articles

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
class ArticleRequest(val articleRequestType: ArticleRequestType, val filter: String) : Parcelable {
  companion object {
    enum class ArticleRequestType {
      PUBLICATION,
      TAG
    }
  }
}
