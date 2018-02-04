package pozzoapps.thejournal.articles

import pozzoapps.thejournal.architecture.model.TheJournalResponse
import pozzoapps.thejournal.articles.model.ArticleList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Api to access articles.
 */
interface ArticleApi {

  @GET("thejournal")
  fun main() : Call<TheJournalResponse<ArticleList>>

  @GET("{publication}")
  fun byPublication(@Path("publication") publication: String) : Call<TheJournalResponse<ArticleList>>

  @GET("tag/{slug}")
  fun byTag(@Path("slug") slug: String) : Call<TheJournalResponse<ArticleList>>
}
