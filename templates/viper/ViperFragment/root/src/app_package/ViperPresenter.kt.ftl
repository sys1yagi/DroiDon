package ${packageName}

import javax.inject.Inject
import android.support.v4.app.Fragment

class ${className}Presenter
@Inject constructor(
        val fragment: Fragment,
        val view: ${className}Contract.View,
        val interactor: ${className}Contract.Interactor,
        val router: ${className}Contract.Router
) : ${className}Contract.Presenter, ${className}Contract.InteractorOutput {

    val viewModel = ${className}ViewModel()

    override fun onResume() {
        interactor.startInteraction(this)
    }

    override fun onPause() {
        interactor.stopInteraction(this)
    }

    override fun onError(t: Throwable) {
        view.showError(t.message ?: "error")
    }
}
