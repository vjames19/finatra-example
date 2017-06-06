package io.github.vjames19.finatraexample.blog.api

import com.twitter.finagle.http.Response
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.inject.server.FeatureTestMixin
import org.scalatest.{Matchers, WordSpec}

/**
  * Created by victor.reventos on 6/6/17.
  */
trait JsonFeatureTest extends WordSpec with FeatureTestMixin with Matchers {

  lazy val objectMapper: FinatraObjectMapper = injector.instance[FinatraObjectMapper]

  implicit class RichResponse(s: Response) {
    def as[T: Manifest]: T = {
      objectMapper.parse[T](s.contentString)
    }
  }

}
