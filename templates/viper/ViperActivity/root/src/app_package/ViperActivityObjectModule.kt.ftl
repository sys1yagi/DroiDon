package ${packageName}

import android.app.Activity
import dagger.Module
import dagger.Provides

@Module
class ${className}ActivityObjectModule(val view: ${className}Contract.View) {
    @Provides
    fun providePresenter(interactor: ${className}Interactor, router: ${className}Router): ${className}Contract.Presenter {
        return ${className}Presenter(view as Activity, view, interactor, router)
    }
}
