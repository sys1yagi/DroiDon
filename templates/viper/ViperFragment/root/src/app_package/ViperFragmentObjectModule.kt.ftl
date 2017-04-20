package ${packageName}

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides

@Module
class ${className}FragmentObjectModule(val view: ${className}Contract.View) {
    @Provides
    fun providePresenter(interactor: ${className}Interactor, router: ${className}Router): ${className}Contract.Presenter {
        return ${className}Presenter(view as Fragment, view, interactor, router)
    }
}
