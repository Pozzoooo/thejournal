package pozzoapps.thejournal.articles.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
class Image(val image: String): Parcelable {
  companion object {
    enum class ImageType(val type: String) {
      THUMBNAIL("thumbnail"),
      SUPER_WIDE_1("super_wide_1");
    }
  }
}
