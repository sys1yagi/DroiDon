package ${packageName}

import dagger.Module
import dagger.Provides

@Module
class ${className}ActivityObjectModule(val view: ${className}Contract.View) {
    @Provides
    fun providePresenter(interactor: ${className}Interactor): ${className}Contract.Presenter {
        return ${className}Presenter(view, interactor)
    }
}
