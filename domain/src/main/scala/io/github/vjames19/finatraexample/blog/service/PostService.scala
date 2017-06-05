package io.github.vjames19.finatraexample.blog.service

import io.github.vjames19.finatraexample.blog.domain.Post

import scala.concurrent.Future

/**
  * Created by victor.reventos on 6/5/17.
  */
trait PostService {

  def getAllPostsForUser(userId: Long): Future[Seq[Post]]

  def get(id: Long): Future[Option[Post]]

  def create(post: Post): Future[Post]

  def update(post: Post): Future[Option[Post]]

  def delete(id: Long): Future[Option[_]]

}
