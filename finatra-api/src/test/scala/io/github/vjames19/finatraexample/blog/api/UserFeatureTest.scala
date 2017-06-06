package io.github.vjames19.finatraexample.blog.api

import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import io.github.vjames19.finatraexample.blog.domain.User


/**
  * Created by victor.reventos on 6/6/17.
  */
class UserFeatureTest extends JsonFeatureTest {
  override protected def server: EmbeddedHttpServer = new EmbeddedHttpServer(new Server)


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
}
