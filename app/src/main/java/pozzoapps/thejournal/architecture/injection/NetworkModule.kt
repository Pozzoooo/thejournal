package pozzoapps.thejournal.architecture.injection

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.grapesnberries.curllogger.CurlLoggerInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import pozzoapps.thejournal.architecture.authentication.BasicAuthorizationInterceptor
import pozzoapps.thejournal.architecture.authentication.LoginModule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.MINUTES
import javax.inject.Singleton

@Module(includes = [(LoginModule::class), (AppModule::class)])
class NetworkModule {

  @Provides
  @Singleton
  fun provideGson(): Gson {
    val gsonBuilder = GsonBuilder()
    return gsonBuilder.create()
  }

  @Provides
  @Singleton
  fun provideCache(application: Application): Cache {
    val cacheSize = 10L * 1024L * 1024L
    return Cache(application.cacheDir, cacheSize)
  }

  @Provides
  @Singleton
  fun provideOkHttpClient(authorization: BasicAuthorizationInterceptor, cache: Cache): OkHttpClient {
    return OkHttpClient.Builder()
        .readTimeout(1, MINUTES)
        .connectTimeout(1, MINUTES)
        .writeTimeout(2, MINUTES)
        .cache(cache)
        .addInterceptor(authorization)
        .addInterceptor(CurlLoggerInterceptor())
        .build()
  }

  @Provides
  fun provideRetrofitBuilder(gson: Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
  }
}
