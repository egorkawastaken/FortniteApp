package main.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import main.common.utils.DefaultContextProvider
import main.common.utils.ScopeProvider

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @Provides
    fun provideScopeProvider(): ScopeProvider {
        return ScopeProvider(DefaultContextProvider())
    }
}