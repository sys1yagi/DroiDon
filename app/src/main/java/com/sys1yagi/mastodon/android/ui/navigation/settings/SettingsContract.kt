package com.sys1yagi.mastodon.android.ui.navigation.settings

import android.support.v7.preference.PreferenceFragmentCompat
import com.sys1yagi.mastodon4j.api.entity.Account

interface SettingsContract {

    interface View {
        fun showError(message: String)
    }

    interface Presenter {
        fun onResume() // base
        fun onPause()  // base
        fun initialize(fragment: PreferenceFragmentCompat)
        fun onLogout()
    }

    interface Interactor {
        fun startInteraction(out: InteractorOutput) // base
        fun stopInteraction(out: InteractorOutput) // base
        fun logout()
    }

    interface InteractorOutput {
        fun onError(t: Throwable)
        fun onLogoutSuccess()
        fun onLogoutFailed()
    }

    interface Router {
        fun openAccountProfile(account: Account)
    }
}
