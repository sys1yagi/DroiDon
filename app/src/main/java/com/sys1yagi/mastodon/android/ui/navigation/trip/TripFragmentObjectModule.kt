package com.sys1yagi.mastodon.android.ui.navigation.trip

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides

@Module
class TripFragmentObjectModule(val view: TripContract.View) {
    @Provides
    fun providePresenter(interactor: TripInteractor, router: TripRouter): TripContract.Presenter {
        return TripPresenter(view as Fragment, view, interactor, router)
    }
}
