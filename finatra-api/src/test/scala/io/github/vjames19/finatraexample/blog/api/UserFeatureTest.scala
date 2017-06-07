package io.github.vjames19.finatraexample.blog.api

import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import io.github.vjames19.finatraexample.blog.domain.User
import io.github.vjames19.finatraexample.blog.service.UserService


/**
  * Created by victor.reventos on 6/6/17.
  */
class UserFeatureTest extends FeatureTest with TestData {
  override protected def server: EmbeddedHttpServer = new EmbeddedHttpServer(new Server)

  private lazy val userService = inject[UserService]

  "Getting a user" when {
    "the user doesn't exist" should {
      "return not found" in {
        server.httpGet(
          path = s"/users/${Long.MinValue}",
          andExpect = Status.NotFound
        )
      }
    }

    "the user exists" should {
      "return the request user" in {
        val user = server.httpGet(
          path = s"/users/1",
          andExpect = Status.Ok
        ).as[User]

        user.id shouldEqual 1L
        user.username should not be empty
      }
    }
  }

  "Creating a user" when {
    "the username already exists" should {
      "return bad request" in {
        val user = User(id = 0, username = randomUUID())
        userService.create(user).futureValue

        server.httpPost(
          path = "/users",
          postBody = user.toJson,
          andExpect = Status.BadRequest
        )
      }
    }

    "the username doesn't exist" should {
      "create the user and then be able to retrieve it" in {
        val user = User(id = 0, username = randomUUID())

        val createdUser = server.httpPost(
          path = "/users",
          postBody = user.toJson,
          andExpect = Status.Ok
        ).as[User]


        createdUser.id should be > 0L
        createdUser.username shouldEqual user.username

        val returnedUser = server.httpGet(
          path = s"/users/${createdUser.id}",
          andExpect = Status.Ok
        ).as[User]

        returnedUser.id shouldEqual createdUser.id
        returnedUser.username shouldEqual createdUser.username
      }
    }
  }
}
