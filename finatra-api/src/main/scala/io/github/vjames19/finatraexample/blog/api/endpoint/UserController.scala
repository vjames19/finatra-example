package io.github.vjames19.finatraexample.blog.api.endpoint

import javax.inject.Inject

import com.twitter.finatra.http.Controller
import com.twitter.finatra.request.RouteParam
import com.twitter.finatra.validation.NotEmpty
import io.github.vjames19.finatraexample.blog.domain.User
import io.github.vjames19.finatraexample.blog.service.{PostService, UserService}

/**
  * Created by victor.reventos on 6/5/17.
  */
class UserController @Inject()(userService: UserService,
                               postService: PostService) extends Controller {

  post("/users") { request: CreateUserRequest =>
    userService.create(User(id = 0, username = request.username))
  }

  get("/users/:id") { request: GetUserRequest =>
    userService.get(request.id)
  }

  get("/users/:id/posts") { request: GetUserPostsRequest =>
    postService.getAllPostsForUser(request.id)
  }
}

case class GetUserRequest(@RouteParam id: Long)

case class GetUserPostsRequest(@RouteParam id: Long)

case class CreateUserRequest(@NotEmpty username: String)
