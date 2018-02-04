package pozzoapps.thejournal.articles.list

import dagger.Component
import pozzoapps.thejournal.architecture.injection.ActivityScope
import pozzoapps.thejournal.architecture.injection.AppComponent
import pozzoapps.thejournal.articles.ArticleClient
import pozzoapps.thejournal.articles.ArticleModule

@ActivityScope
@Component(dependencies = [(AppComponent::class)],
    modules = [(ArticleListModule::class), (ArticleModule::class)])
interface ArticleListComponent {
  fun inject(fragment: ArticleListFragment)
  fun inject(viewModel: ArticleListViewModel)
}
