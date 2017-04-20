package com.sys1yagi.mastodon.android.ui.navigation.home.notification

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides

@Module
class NotificationFragmentObjectModule(val view: NotificationContract.View) {
    @Provides
    fun providePresenter(interactor: NotificationInteractor, router: NotificationRouter): NotificationContract.Presenter {
        return NotificationPresenter(view as Fragment, view, interactor, router)
    }
}
