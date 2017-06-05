package io.github.vjames19.finatraexample.blog.service

import io.github.vjames19.finatraexample.blog.domain.Comment

import scala.concurrent.Future

/**
  * Created by victor.reventos on 6/5/17.
  */
trait CommentService {

  def getCommentsForPost(postId: Long): Future[Seq[Comment]]

  def create(comment: Comment): Future[Comment]

  def update(comment: Comment): Future[Option[Comment]]

  def delete(id: Long): Future[Option[_]]
}
