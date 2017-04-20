package ${packageName}

import io.reactivex.disposables.Disposables
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

    override fun stopInteraction(out: ${className}Contract.InteractorOutput) {
        disposable.dispose()
        this.out = null
    }
}
