package com.sys1yagi.mastodon.android.ui.navigation.settings

import android.content.Context
import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import com.sys1yagi.fragmentcreator.annotation.FragmentCreator
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

@FragmentCreator
class SettingsFragment : PreferenceFragmentCompat() {

    @Inject
    lateinit var initializer: SettingsInitializer

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        initializer.initialize(this)
    }
}
