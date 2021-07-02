package com.naci.sample.themetmuseum.di

import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@Module
@DisableInstallInCheck
interface DataModule {

    // Since we have repository module, we don't need that binding anymore
/*    @AppScope
    @Binds
    fun bindMuseumRepository(museumRepository: MuseumRepositoryImp): MuseumRepository*/
}
