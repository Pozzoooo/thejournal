package pozzoapps.thejournal.articles

import dagger.Module
import dagger.Provides
import pozzoapps.thejournal.architecture.model.Site
import retrofit2.Retrofit

@Module
class ArticleModule {

    @Provides
    fun provideArticleApi(retrofitBuilder: Retrofit.Builder, site: Site): ArticleApi {
        return retrofitBuilder.baseUrl(site.baseUrl).build().create(ArticleApi::class.java)
    }

    @Provides
    fun provideArticleClient(articleApi: ArticleApi): ArticleClient {
        return ArticleClient(articleApi)
    }

    @Provides
    fun provideArticleBusiness(articleClient: ArticleClient): ArticleBusiness {
        return ArticleBusiness(articleClient)
    }
}
