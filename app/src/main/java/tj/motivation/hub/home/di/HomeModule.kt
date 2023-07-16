//package tj.motivation.hub.home.di
//
//import android.app.Application
//import androidx.room.Room
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import tj.motivation.hub.home.HomeUtils
//import tj.motivation.hub.home.data.local.QuotesDB
//import tj.motivation.hub.home.data.remote.PhotoApi
//import tj.motivation.hub.home.data.remote.QuotesApi
//import tj.motivation.hub.home.data.repository.HomeRepositoryImpl
//import tj.motivation.hub.home.domain.repository.HomeRepository
//import tj.motivation.hub.home.domain.use_case.GetQuoteAndPhotoUseCase
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//class HomeModule {
//
//    @Provides
//    @Singleton
//    fun provideGetQuoteAndPhotoUseCase(homeRepository: HomeRepository): GetQuoteAndPhotoUseCase {
//        return GetQuoteAndPhotoUseCase(homeRepository)
//    }
//
//    @Provides
//    @Singleton
//    fun provideHomeRepository(
//        db: QuotesDB,
//        quotesApi: QuotesApi,
//        photoApi: PhotoApi
//    ): HomeRepository {
//        return HomeRepositoryImpl(db.getQuotesDao(), quotesApi, photoApi)
//    }
//
//    @Provides
//    @Singleton
//    fun provideQuotesDB(app: Application): QuotesDB {
//        return Room.databaseBuilder(
//            app, QuotesDB::class.java, "quotes_db"
//        ).build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideQuotesApi(): QuotesApi {
//        return Retrofit.Builder().baseUrl(HomeUtils.ZEN_BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create()).build()
//            .create(QuotesApi::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun providePhotoApi(): PhotoApi {
//        return Retrofit.Builder().baseUrl(HomeUtils.UNSPLASH_BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create()).build()
//            .create(PhotoApi::class.java)
//    }
//}