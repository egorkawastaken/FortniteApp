package main.di.interactor

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import main.domain.ProfileInteractorImpl
import main.domain.profile.ProfileInteractor

@Module
@InstallIn(SingletonComponent::class)
abstract class InteractorModule {

    @Binds
    abstract fun bindProfileInteractor(impl: ProfileInteractorImpl): ProfileInteractor
}