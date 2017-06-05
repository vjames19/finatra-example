package io.github.vjames19.finatraexample.service

import io.github.vjames19.finatraexample.domain.Post

import scala.concurrent.Future

/**
  * Created by victor.reventos on 6/5/17.
  */
trait PostService {

  def getAllPostsForUser(userId: Long): Future[Seq[Post]]

  def get(id: Long): Future[Post]

  def create(post: Post): Future[Post]

  def update(post: Post): Future[Post]

  def delete(id: Long): Future[Void]

}
