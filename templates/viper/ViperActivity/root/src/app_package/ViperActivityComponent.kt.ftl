package ${packageName}

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(${className}ActivityObjectModule::class))
interface ${className}ActivityComponent : AndroidInjector<${className}Activity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<${className}Activity>() {

        abstract fun objectModule(objectModule: ${className}ActivityObjectModule)

        override fun seedInstance(instance: ${className}Activity) {
            objectModule(${className}ActivityObjectModule(instance))
        }
    }
}
