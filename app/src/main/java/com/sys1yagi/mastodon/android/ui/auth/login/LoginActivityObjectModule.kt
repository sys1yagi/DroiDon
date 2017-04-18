package com.sys1yagi.mastodon.android.ui.auth.login

import android.app.Activity
import dagger.Module
import dagger.Provides

@Module
class LoginActivityObjectModule(val view: LoginContract.View) {
    @Provides
    fun providePresenter(interactor: LoginInteractor, router: LoginRouter): LoginContract.Presenter {
        return LoginPresenter(view as Activity, view, interactor, router)
    }
}
