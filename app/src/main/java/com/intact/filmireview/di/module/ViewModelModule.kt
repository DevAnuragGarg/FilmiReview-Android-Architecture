package com.intact.filmireview.di.module

import androidx.lifecycle.ViewModel
import com.intact.filmireview.di.scope.ViewModelKey
import com.intact.filmireview.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton


/**
 *
 * this module class provides the all the view model classes
 * creation of the view model classes is done here
 * Also, it will give the view model factory instance too.
 * We need customized view model factory, as we need to pass
 * parameters to the view models. If not, we could directly access
 * the View models using providers.
 * You can create separate view model factory for each view model,
 * but in place of this, we are creating a single view model factory
 * which is generic.
 *
 * In Dagger 2 exists multibindings and with it is possible to create a “map”
 * of objects with a specific key. In our use case will be like below:
 * the key: for example the class type of ViewModel MyViewModel::class
 * the value: the instance of ViewModel MyViewModel(repository, …)
 *
 * Dagger ( in compiling time) will create the map and we will provide them
 * to CustomViewModelFactory as argument, so when we will invoke the create() method
 * it will able to pick the right instance from the map:
 *
 * Created by Anurag Garg on 25/03/19.
 */

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel
}