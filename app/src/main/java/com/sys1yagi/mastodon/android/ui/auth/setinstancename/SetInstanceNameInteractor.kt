package com.sys1yagi.mastodon.android.ui.auth.setinstancename

import com.sys1yagi.mastodon.android.data.database.Credential
import com.sys1yagi.mastodon.android.data.database.OrmaDatabaseProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SetInstanceNameInteractor
@Inject
constructor(
        databaseProvider: OrmaDatabaseProvider
)
    : SetInstanceNameContract.Interactor {

    val database = databaseProvider.database
    var out: SetInstanceNameContract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: SetInstanceNameContract.InteractorOutput) {
        this.out = out
    }

    override fun stoplInteraction(out: SetInstanceNameContract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }

    override fun saveInstanceName(instanceName: String) {
        disposable = database.transactionAsCompletable {
            database.selectFromCredential().instanceNameEq(instanceName).count().takeIf { it < 1 }?.let {
                val credential = Credential()
                credential.instanceName = instanceName
                database.insertIntoCredential(credential)
            }
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    out?.onInstanceNameSaved()
                }
    }
}
