package pozzoapps.thejournal.articles.details

import dagger.Component
import pozzoapps.thejournal.architecture.injection.ActivityScope
import pozzoapps.thejournal.architecture.injection.AppComponent
import pozzoapps.thejournal.articles.ArticleModule

@ActivityScope
@Component(dependencies = [(AppComponent::class)],
    modules = [(ArticleDetailModule::class), (ArticleModule::class)])
interface ArticleDetailComponent {
  fun inject(activity: ArticleDetailActivity)
  fun inject(fragment: ArticleDetailFragment)
  fun inject(viewModel: ArticleDetailViewModel)
}
