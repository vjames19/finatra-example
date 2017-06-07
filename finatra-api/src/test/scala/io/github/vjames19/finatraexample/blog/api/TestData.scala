package io.github.vjames19.finatraexample.blog.api

import java.util.UUID

/**
  * Created by victor.reventos on 6/7/17.
  */
trait TestData {

  def randomUUID(): String = UUID.randomUUID().toString

}
