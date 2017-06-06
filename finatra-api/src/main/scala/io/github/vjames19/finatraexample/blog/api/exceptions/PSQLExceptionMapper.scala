package io.github.vjames19.finatraexample.blog.api.exceptions

import javax.inject.Inject

import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.exceptions.ExceptionMapper
import com.twitter.finatra.http.response.ResponseBuilder
import com.twitter.inject.Logging
import org.postgresql.util.PSQLException

/**
  * Created by victor.reventos on 6/6/17.
  */
class PSQLExceptionMapper @Inject()(response: ResponseBuilder) extends ExceptionMapper[PSQLException] with Logging {

  // Error Codes: https://www.postgresql.org/docs/9.6/static/errcodes-appendix.html
  val integrityConstraintViolation: String = "23"

  override def toResponse(request: Request, throwable: PSQLException): Response = {
    val code = throwable.getServerErrorMessage.getSQLState

    if (code.startsWith(integrityConstraintViolation)) {
      response.badRequest(throwable.getServerErrorMessage.toString)
    } else {
      error("Something went wrong", throwable)
      response.internalServerError("Something went wrong")
    }
  }
}
