package main.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import main.common.utils.ContextProvider
import main.common.utils.DefaultContextProvider
import main.common.utils.ScopeProvider

@Module
@InstallIn(ViewModelComponent::class)
object CoroutineModule {

    @Provides
    fun provideContextProvider(): ContextProvider = DefaultContextProvider()

    @Provides
    fun provideScopeProvider(contextProvider: ContextProvider): ScopeProvider {
        return ScopeProvider(contextProvider)
    }
}