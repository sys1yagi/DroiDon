package com.sys1yagi.mastodon.android.ui.navigation.settings

import android.support.v7.preference.PreferenceFragmentCompat
import com.sys1yagi.mastodon.android.R

open class SettingsInitializerImpl : SettingsInitializer {
    override fun initialize(fragment: PreferenceFragmentCompat) {
        val context = fragment.context
        fragment.addPreferencesFromResource(R.xml.preference)

        val openYourProfile = fragment.findPreference(context.getString(R.string.preferences_open_your_profile_key))
        openYourProfile.setOnPreferenceClickListener {
            // TODO
            true
        }
    }
}
