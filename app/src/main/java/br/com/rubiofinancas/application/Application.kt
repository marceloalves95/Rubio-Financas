package br.com.rubiofinancas.application

import android.app.Application
import br.com.rubiofinancas.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by RubioAlves on 29/04/2021
 */
class Application:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(appModules)
        }
    }
}