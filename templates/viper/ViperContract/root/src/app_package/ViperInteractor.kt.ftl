package ${packageName}

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ${className}Interactor
@Inject
constructor(
        // add dependencies
)
    : ${className}Contract.Interactor {

    var out: ${className}Contract.InteractorOutput? = null
    var disposable = Disposables.empty()

    override fun startInteraction(out: ${className}Contract.InteractorOutput) {
        this.out = out
    }

    override fun stoplInteraction(out: ${className}Contract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }
}
