package com.sys1yagi.mastodon.android.ui.navigation.home

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides

@Module
class HomeFragmentObjectModule(val view: HomeContract.View) {
    @Provides
    fun providePresenter(interactor: HomeInteractor, router: HomeRouter): HomeContract.Presenter {
        return HomePresenter(view as Fragment, view, interactor, router)
    }
}
