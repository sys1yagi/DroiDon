package com.sys1yagi.mastodon.android.ui.auth.login

import com.sys1yagi.mastodon.android.data.database.Credential

class LoginViewModel {
    var instanceName: String = ""
    var credential: Credential? = null
}
