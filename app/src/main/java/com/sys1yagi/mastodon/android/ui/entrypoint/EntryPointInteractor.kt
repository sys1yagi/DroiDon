package com.sys1yagi.mastodon.android.ui.entrypoint

import com.sys1yagi.mastodon.android.data.database.OrmaDatabase
import com.sys1yagi.mastodon.android.data.database.OrmaDatabaseProvider
import io.reactivex.disposables.Disposables
import javax.inject.Inject

class EntryPointInteractor
@Inject
constructor(
        databaseProvider: OrmaDatabaseProvider
)
    : EntryPointContract.Interactor {

    val database: OrmaDatabase = databaseProvider.database
    var out: EntryPointContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: EntryPointContract.InteractorOutput) {
        this.out = out
    }

    override fun stoplInteraction(out: EntryPointContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }

    override fun checkInstanceName() {
        database.selectFromCredential().firstOrNull()?.let{
          out?.onInstanceNameFound()
        } ?: out?.onInstanceNameNotRegistered()
    }

    override fun checkRegistration() {

    }

    override fun checkAccessToken() {

    }
}
