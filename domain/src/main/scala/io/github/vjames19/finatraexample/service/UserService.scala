package io.github.vjames19.finatraexample.service

import io.github.vjames19.finatraexample.domain.User

import scala.concurrent.Future

/**
  * Created by victor.reventos on 6/5/17.
  */
trait UserService {

  def get(id: Long): Future[User]

  def create(user: User): Future[User]
}
