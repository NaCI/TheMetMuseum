/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.naci.sample.themetmuseum.di

import com.naci.sample.themetmuseum.BuildConfig
import com.naci.sample.themetmuseum.api.MetMuseumService
import com.naci.sample.themetmuseum.api.UrlProvider
import com.naci.sample.themetmuseum.di.scope.AppScope
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@DisableInstallInCheck
object NetworkModule {

    @Provides
    @AppScope
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @AppScope
    fun provideUrlProvider() = UrlProvider()

    @Provides
    @AppScope
    fun provideRetrofit(okHttpClient: OkHttpClient, urlProvider: UrlProvider): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(urlProvider.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @AppScope
    fun provideMetMuseumService(retrofit: Retrofit): MetMuseumService {
        return retrofit.create(MetMuseumService::class.java)
    }
}
