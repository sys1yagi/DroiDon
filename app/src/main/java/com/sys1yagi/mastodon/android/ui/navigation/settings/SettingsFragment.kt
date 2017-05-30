package com.sys1yagi.mastodon.android.ui.navigation.settings

import android.content.Context
import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import com.sys1yagi.fragmentcreator.annotation.Args
import com.sys1yagi.fragmentcreator.annotation.FragmentCreator
import com.sys1yagi.mastodon.android.extensions.toast
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

@FragmentCreator
class SettingsFragment : PreferenceFragmentCompat(), SettingsContract.View {

    @Args
    lateinit var instanceName: String

    @Inject
    lateinit var presenter: SettingsContract.Presenter

    override fun onAttach(context: Context) {
        SettingsFragmentCreator.read(this)
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        presenter.initialize(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun showError(message: String) {
        context.toast(message)
    }
}
