package io.github.vjames19.finatraexample.blog.api

import com.twitter.finagle.http.Response
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.inject.server.FeatureTestMixin
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.{Matchers, WordSpec}

/**
  * Created by victor.reventos on 6/6/17.
  */
trait FeatureTest extends WordSpec with FeatureTestMixin with Matchers with ScalaFutures {

  // Patience for waiting for futures
  implicit val defaultPatience = PatienceConfig(timeout = Span(2, Seconds), interval = Span(5, Millis))

  lazy val objectMapper: FinatraObjectMapper = injector.instance[FinatraObjectMapper]

  def inject[T: Manifest]: T = injector.instance[T]

  implicit class RichResponse(s: Response) {
    def as[T: Manifest]: T = {
      objectMapper.parse[T](s.contentString)
    }
  }

  implicit class RichObject[T](t: T) {
    def toJson: String = objectMapper.writePrettyString(t)
  }

}
