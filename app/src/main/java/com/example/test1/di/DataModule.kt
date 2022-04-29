package com.example.test1.di

import android.content.Context
import com.example.test1.data.repository.UserRepositoryImpl
import com.example.test1.data.storage.UserStorage
import com.example.test1.data.storage.sharedprefs.SharedPrefUserStorage
import com.example.test1.domain.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideUserStorage(context: Context): UserStorage {
        return SharedPrefUserStorage(context = context)
    }

    @Provides
    fun provideUserRepository(userStorage: UserStorage): UserRepository {
        return UserRepositoryImpl(userStorage = userStorage)
    }
}