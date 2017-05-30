package com.sys1yagi.mastodon.android.ui.navigation.settings

import android.support.v4.app.Fragment
import com.sys1yagi.mastodon.android.data.database.OrmaDatabaseProvider
import dagger.Module
import dagger.Provides

@Module
class SettingsFragmentObjectModule(val instanceName: String, val view: SettingsContract.View) {
    @Provides
    fun providePresenter(interactor: SettingsInteractor, router: SettingsRouter, database: OrmaDatabaseProvider): SettingsContract.Presenter {
        return DebugSettingsPresenter(instanceName, view as Fragment, view, interactor, router, database)
    }
}
