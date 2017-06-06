package io.github.vjames19.finatraexample.blog.api.di

import java.util.concurrent.Executors
import javax.inject.Singleton

import com.google.inject.Provides
import com.twitter.inject.{Injector, TwitterModule}
import io.github.vjames19.finatraexample.blog.service._
import io.github.vjames19.finatraexample.blog.service.di.DbExecutionContext
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext

/**
  * Created by victor.reventos on 6/6/17.
  */
object ServiceModule extends TwitterModule {
  protected override def configure(): Unit = {
    super.configure()

    bind[CommentService].to[DbCommentService]
    bind[PostService].to[DbPostService]
    bind[UserService].to[DbUserService]
  }


  override def singletonShutdown(injector: Injector): Unit = {
    super.singletonShutdown(injector)

    injector.instance[Database].close()
  }

  @Provides
  @DbExecutionContext
  @Singleton
  def providesDbExecutionContext(): ExecutionContext = {
    ExecutionContext.fromExecutorService(Executors.newCachedThreadPool())
  }

  @Provides
  @Singleton
  def providesDatabase(): Database = {
    Database.forConfig("blogDb")
  }
}
