package main.di.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import main.data.remote.profile.ProfileApi
import main.data.remote.profile.ProfileRepositoryImpl
import main.data.remote.profile.mapper.ProfileDtoMapper
import main.domain.profile.ProfileRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProfileRepository(profileApi: ProfileApi, profileDtoMapper: ProfileDtoMapper): ProfileRepository {
        return ProfileRepositoryImpl(
            api = profileApi, profileDtoMapper = profileDtoMapper
        )
    }
}