package ms.gamehouse.zicare.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import ms.gamehouse.zicare.data.source.remote.RemoteDataSource
import ms.gamehouse.zicare.data.source.remote.RemoteDataSourceImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    @ViewModelScoped
    abstract fun bindDataSource(
        dataSource: RemoteDataSourceImpl
    ): RemoteDataSource
}