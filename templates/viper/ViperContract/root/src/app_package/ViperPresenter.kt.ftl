package ${packageName}

import javax.inject.Inject

class ${className}Presenter
@Inject constructor(
        val view: ${className}Contract.View,
        val interactor: ${className}Contract.Interactor
) : ${className}Contract.Presenter, ${className}Contract.InteractorOutput {

    val viewModel = ${className}ViewModel()

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onError(t: Throwable) {
        view.showError(t.message ?: "error")
    }
}
