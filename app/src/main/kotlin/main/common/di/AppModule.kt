package main.common.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import main.common.utils.ResourcesHandler
import main.common.utils.ResourcesHandlerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideResourcesHandler(@ApplicationContext context: Context): ResourcesHandler = ResourcesHandlerImpl(context)
}
