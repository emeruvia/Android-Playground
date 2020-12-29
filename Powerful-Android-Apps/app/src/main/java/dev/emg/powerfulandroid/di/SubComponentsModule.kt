package dev.emg.powerfulandroid.di

import dagger.Module
import dev.emg.powerfulandroid.di.auth.AuthComponent
import dev.emg.powerfulandroid.di.main.MainComponent

@Module(subcomponents = [AuthComponent::class, MainComponent::class])
class SubComponentsModule