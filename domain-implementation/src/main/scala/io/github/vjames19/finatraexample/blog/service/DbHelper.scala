package io.github.vjames19.finatraexample.blog.service

/**
  * Created by victor.reventos on 6/5/17.
  */
trait DbHelper {

  def updateCodeToOptionalResult[T](result: => T)(code: Int): Option[T] = {
    if (code > 0) {
      Some(result)
    } else {
      None
    }
  }
}
