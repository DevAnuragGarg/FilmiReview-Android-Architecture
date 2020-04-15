package com.intact.moviesbox

import android.app.Application
import com.intact.moviesbox.di.component.DaggerAppComponent
import com.intact.moviesbox.util.ReleaseLogTree
import com.intact.moviesbox.util.createNotificationChannel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * application file having all the initializations of the libraries
 *
 * Note: 1) whenever we say new it will create a new object, same instance is not used each time we
 * call it, dagger will call new instance for each dependency
 *
 * 2) In below code we only need to initialize the module having the constructor, rest dagger
 * handles it
 *
 * 3) Scopes tell it belong to this instance and to this component. It is a special annotation in
 * Dagger 2. Scopes mechanism cares about keeping single instance of class as long as its scope
 * exists. In practice it means that instances scoped in @ApplicationScope lives as long as
 * Application object. @ActivityScope keeps references as long as Activity exists (for example we
 * can share single instance of any class between all fragments hosted in this Activity).
 *
 * @Singleton - application scope
 * @UserScope - scope for classes instances associated with picked user (in real app it could be
 * logged-in user)
 * @ActivityScope - scope for instances which live as long as the activity (presenters in our case)
 *
 * 4) Never ever use the @Singleton in your annotation for dagger. Whenever you call the build on
 * the dagger component it will always create a new instance even though you use the @Singleton
 *
 * 5) If compiler gets confused about the same two dependencies use the @Named annotation which in
 * itself is Qualifier, which can be replaced by self declared custom Qualifier
 *
 * Created by Anurag Garg on 18-03-2019.
 */
class MoviesBoxApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        // registering the notification channel
        createNotificationChannel(this)

        // setting up the dagger
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)

        // Setting up the timber
        if (BuildConfig.DEBUG) {

            // creating the debug mode
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format(
                        "C:%s:%s",
                        super.createStackElementTag(element),
                        element.lineNumber
                    )
                }
            })
        } else {
            Timber.plant(ReleaseLogTree())
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}