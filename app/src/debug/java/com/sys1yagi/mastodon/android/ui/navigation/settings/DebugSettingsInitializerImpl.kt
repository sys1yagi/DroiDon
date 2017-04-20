package com.sys1yagi.mastodon.android.ui.navigation.settings

import android.support.v7.preference.PreferenceFragmentCompat
import com.sys1yagi.mastodon.android.R

class DebugSettingsInitializerImpl : SettingsInitializerImpl() {
    override fun initialize(fragment: PreferenceFragmentCompat) {
        super.initialize(fragment)
        val context = fragment.context
        fragment.addPreferencesFromResource(R.xml.preference_debug)

        // TODO
    }
}
