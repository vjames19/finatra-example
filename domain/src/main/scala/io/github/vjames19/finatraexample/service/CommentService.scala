package io.github.vjames19.finatraexample.service

import io.github.vjames19.finatraexample.domain.Comment

import scala.concurrent.Future

/**
  * Created by victor.reventos on 6/5/17.
  */
trait CommentService {

  def create(comment: Comment): Future[Comment]

  def update(comment: Comment): Future[Comment]

  def delete(id: Long): Future[Void]

}
