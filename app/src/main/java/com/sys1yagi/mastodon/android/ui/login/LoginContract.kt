package com.sys1yagi.mastodon.android.ui.login

import android.content.Context
import com.sys1yagi.mastodon.android.data.database.Credential

interface LoginContract {

    interface View {
        fun showError(message: String)
    }

    interface Presenter {
        fun onResume() // base
        fun onPause()  // base
    }

    interface Interactor {
        fun startInteraction(out: InteractorOutput) // base
        fun stoplInteraction(out: InteractorOutput) // base
        fun login(context: Context, credential: Credential)
    }

    interface InteractorOutput {
        fun onError(t: Throwable)
    }

    interface Router {

    }
}
