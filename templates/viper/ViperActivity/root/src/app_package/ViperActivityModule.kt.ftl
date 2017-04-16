package ${packageName}

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(${className}ActivityComponent::class))
abstract class ${className}ActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(${className}Activity::class)
    abstract fun bind${className}ActivityInjectorFactory(builder: ${className}ActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}
