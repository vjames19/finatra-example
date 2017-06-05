package io.github.vjames19.finatraexample.blog.api

import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import io.github.vjames19.finatraexample.blog.api.endpoint.{PostController, UserController}

/**
  * Created by victor.reventos on 6/5/17.
  */
class Server extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit = {
      router
        .filter[CommonFilters]
        .add[UserController]
        .add[PostController]
  }
}
