package com.intact.moviesbox.di.component

import android.app.Application
import com.intact.moviesbox.MoviesBoxApp
import com.intact.moviesbox.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * This component is responsible for providing application scope instances
 * (eg. OkHttp, Database, SharedPrefs.). This Component is root of our dagger graph
 *
 * AndroidInjectionModule : We didn’t create this. It is an internal class in
 * Dagger 2.10. Provides our activities and fragments with given module.
 *
 * ActivityBuilder : We created this module. This is a given module to dagger.
 * We map all our activities here. And Dagger know our activities in compile time.
 */

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ActivityBuilderModule::class, AppModule::class,
        RemoteRequestModule::class, PicassoModule::class, ApplicationContextModule::class,
        ViewModelModule::class, DataModule::class, PresentationModule::class,
        ViewModelFactoryModule::class, LocalPersistenceModule::class, FragmentModule::class]
)
interface AppComponent {

    /**
     *  we want to bind some instance to Component. In this case we can create an interface with
     *  @Component.Builder annotation and add whatever method we want to add to builder. Here we
     *  are adding Application to my AppComponent.
     *
     *  we are annotating that this interface will be responsible for our generated builder which
     *  will have methods responsible for retrieving our application class and building whole
     *  component together. @BindsInstance means that this method will also inject our Application
     *  class to the component so we will be able to use it there.
     */

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MoviesBoxApp)
}