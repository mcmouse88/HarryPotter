package com.mcmouse88.harrypotter.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * для создания точки входа в приложение для Hilt не нужно переопределять метод onCreate, и
 * создавать там компонент DI, достаточно пометить класс [Application] аннотацией
 * [HiltAndroidApp], и Hilt сам сгенерирует необходимый код
 */
@HiltAndroidApp
class AppComponent : Application()