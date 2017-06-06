package io.github.vjames19.finatraexample.blog.api

import com.google.inject.Module
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import io.github.vjames19.finatraexample.blog.api.di.{JacksonModule, ServiceModule}
import io.github.vjames19.finatraexample.blog.api.endpoint.{PostController, UserController}
import io.github.vjames19.finatraexample.blog.api.exceptions.PSQLExceptionMapper

/**
  * Created by victor.reventos on 6/5/17.
  */
class Server extends HttpServer {

  override protected def modules: Seq[Module] = Seq(ServiceModule)


  override protected def jacksonModule: Module = JacksonModule

  override protected def defaultFinatraHttpPort: String = ":8080"

  override protected def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[CommonFilters]
      .add[PostController]
      .add[UserController]
      .exceptionMapper[PSQLExceptionMapper]
  }
}

object ServerMain extends Server