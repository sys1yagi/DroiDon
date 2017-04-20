package com.sys1yagi.mastodon.android.ui.navigation.home.federatedtimeline

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides

@Module
class FederatedTimelineFragmentObjectModule(val view: FederatedTimelineContract.View) {
    @Provides
    fun providePresenter(interactor: FederatedTimelineInteractor, router: FederatedTimelineRouter): FederatedTimelineContract.Presenter {
        return FederatedTimelinePresenter(view as Fragment, view, interactor, router)
    }
}
