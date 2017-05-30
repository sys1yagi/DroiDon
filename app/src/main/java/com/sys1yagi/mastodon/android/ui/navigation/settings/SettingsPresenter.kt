package com.sys1yagi.mastodon.android.ui.navigation.settings

import android.support.v4.app.Fragment
import android.support.v7.preference.PreferenceFragmentCompat
import com.sys1yagi.mastodon.android.R
import com.sys1yagi.mastodon.android.ui.entrypoint.EntryPointActivity
import javax.inject.Inject

open class SettingsPresenter
@Inject constructor(
        val instanceName: String,
        val fragment: Fragment,
        val view: SettingsContract.View,
        val interactor: SettingsContract.Interactor,
        val router: SettingsContract.Router
) : SettingsContract.Presenter, SettingsContract.InteractorOutput {

    val viewModel = SettingsViewModel()

    override fun onResume() {
        interactor.startInteraction(this)
    }

    override fun onPause() {
        interactor.stopInteraction(this)
    }

    override fun onError(t: Throwable) {
        view.showError(t.message ?: "error")
    }

    override fun initialize(fragment: PreferenceFragmentCompat) {
        val context = fragment.context
        fragment.addPreferencesFromResource(R.xml.preference)

        val openYourProfile = fragment.findPreference(context.getString(R.string.preferences_open_your_profile_key))
        openYourProfile.setOnPreferenceClickListener {
            // TODO
            // router.openAccountProfile()
            true
        }
        val logout = fragment.findPreference(context.getString(R.string.preferences_logout_key))
        logout.setOnPreferenceClickListener {
            interactor.logout()
            true
        }
    }

    override fun onLogoutSuccess() {
        view.showError("ログアウトしました")
        fragment.activity.finish()
        fragment.context.startActivity(EntryPointActivity.createIntent(fragment.context))
    }

    override fun onLogoutFailed() {
        view.showError("ログアウトに失敗しました")
    }
}
