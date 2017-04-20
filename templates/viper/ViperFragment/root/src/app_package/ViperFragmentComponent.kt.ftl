package ${packageName}

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(${className}FragmentObjectModule::class))
interface ${className}FragmentComponent : AndroidInjector<${className}Fragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<${className}Fragment>() {

        abstract fun objectModule(objectModule: ${className}FragmentObjectModule)

        override fun seedInstance(instance: ${className}Fragment) {
            objectModule(${className}FragmentObjectModule(instance))
        }
    }
}
