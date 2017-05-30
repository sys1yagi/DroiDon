package com.sys1yagi.mastodon.android.ui.navigation.settings

import android.widget.Toast
import com.sys1yagi.mastodon.android.data.database.OrmaDatabase
import com.sys1yagi.mastodon.android.data.database.OrmaDatabaseProvider
import com.sys1yagi.mastodon.android.extensions.async
import com.sys1yagi.mastodon.android.extensions.ui
import io.reactivex.disposables.Disposables
import kotlinx.coroutines.experimental.Job
import javax.inject.Inject

class SettingsInteractor
@Inject
constructor(
        databaseProvider: OrmaDatabaseProvider
)
    : SettingsContract.Interactor {

    val database: OrmaDatabase = databaseProvider.database
    var out: SettingsContract.InteractorOutput? = null
    var job: Job? = null

    override fun startInteraction(out: SettingsContract.InteractorOutput) {
        this.out = out
    }

    override fun stopInteraction(out: SettingsContract.InteractorOutput) {
        job?.cancel()
        this.out = null
    }

    override fun logout() {
        job = async {
            try {
                database.transactionSync {
                    database.deleteAll()
                }
                ui {
                    out?.onLogoutSuccess()
                }
            } catch (e: Exception) {
                ui {
                    out?.onLogoutFailed()
                }
            }
        }
    }
}
