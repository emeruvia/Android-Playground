package dev.emg.powerfulandroid.di.main

import dagger.Module

@Module
object MainModule {
//  @JvmStatic
//  @MainScope
//  @Provides
//  fun provideOpenApiMainService(retrofitBuilder: Retrofit.Builder): OpenApiMainService {
//    return retrofitBuilder
//      .build()
//      .create(OpenApiMainService::class.java)
//  }
//
//  @JvmStatic
//  @MainScope
//  @Provides
//  fun provideAccountRepository(
//    openApiMainService: OpenApiMainService,
//    accountPropertiesDao: AccountPropertiesDao,
//    sessionManager: SessionManager
//  ): AccountRepository {
//    return AccountRepository(openApiMainService, accountPropertiesDao, sessionManager)
//  }
//
//  @JvmStatic
//  @MainScope
//  @Provides
//  fun provideBlogPostDao(db: AppDatabase): BlogPostDao {
//    return db.getBlogPostDao()
//  }
//
//  @JvmStatic
//  @MainScope
//  @Provides
//  fun provideBlogRepository(
//    openApiMainService: OpenApiMainService,
//    blogPostDao: BlogPostDao,
//    sessionManager: SessionManager
//  ): BlogRepository {
//    return BlogRepository(openApiMainService, blogPostDao, sessionManager)
//  }
//
//  @JvmStatic
//  @MainScope
//  @Provides
//  fun provideCreateBlogRepository(
//    openApiMainService: OpenApiMainService,
//    blogPostDao: BlogPostDao,
//    sessionManager: SessionManager
//  ): CreateBlogRepository {
//    return CreateBlogRepository(openApiMainService, blogPostDao, sessionManager)
//  }
}