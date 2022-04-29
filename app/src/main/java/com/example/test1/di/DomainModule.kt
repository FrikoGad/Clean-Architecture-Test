package com.example.test1.di

import com.example.test1.domain.repository.UserRepository
import com.example.test1.domain.usecase.GetUserNameUseCase
import com.example.test1.domain.usecase.SaveUserNameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetUserNameUseCase(userRepository: UserRepository) : GetUserNameUseCase {
        return GetUserNameUseCase(userRepository = userRepository)
    }

    @Provides
    fun provideSaveUserNameUseCase(userRepository: UserRepository) : SaveUserNameUseCase {
        return SaveUserNameUseCase(userRepository = userRepository)
    }
}