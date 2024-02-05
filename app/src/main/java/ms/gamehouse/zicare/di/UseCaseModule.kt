package ms.gamehouse.zicare.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ms.gamehouse.zicare.domain.usecase.UserUseCase
import ms.gamehouse.zicare.domain.usecase.UserUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindUserUseCase(
        userUseCaseImpl: UserUseCaseImpl
    ): UserUseCase
}