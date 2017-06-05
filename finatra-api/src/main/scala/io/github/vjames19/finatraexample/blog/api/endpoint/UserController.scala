package io.github.vjames19.finatraexample.blog.api.endpoint

import javax.inject.Inject

import com.twitter.finatra.http.Controller
import com.twitter.finatra.request.RouteParam
import com.twitter.finatra.validation.NotEmpty
import io.github.vjames19.finatraexample.domain.User
import io.github.vjames19.finatraexample.service.UserService

/**
  * Created by victor.reventos on 6/5/17.
  */
class UserController @Inject()(userService: UserService) extends Controller {

  get("/users/:id") { request: GetUserRequest =>
    userService.get(request.id)
  }

  post("/users") { request: CreateUserRequest =>
    userService.create(User(id = 0, username = request.username))
  }
}

case class GetUserRequest(@RouteParam id: Long)

case class CreateUserRequest(@NotEmpty username: String)
