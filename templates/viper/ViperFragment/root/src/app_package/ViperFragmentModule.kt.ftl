package ${packageName}

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(${className}FragmentComponent::class))
interface ${className}FragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(${className}Fragment::class)
    abstract fun bindLocal${className}InjectorFactory(builder: ${className}FragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
}
