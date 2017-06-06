package io.github.vjames19.finatraexample.blog.service

import javax.inject.{Inject, Singleton}

import io.github.vjames19.finatraexample.blog.domain.Post
import io.github.vjames19.finatraexample.blog.models.Tables
import io.github.vjames19.finatraexample.blog.models.Tables.PostsRow
import io.github.vjames19.finatraexample.blog.service.di.DbExecutionContext
import io.github.vjames19.finatraexample.blog.service.mapper.PostMapper
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by victor.reventos on 6/5/17.
  */
@Singleton
class DbPostService @Inject()(db: Database,
                              @DbExecutionContext implicit val executionContext: ExecutionContext) extends PostService with DbHelper {


  override def getAllPostsForUser(userId: Long): Future[Seq[Post]] = {
    db.run {
      Tables.Posts.filter(_.userId === userId).result
    }.map(_.map(PostMapper.toDomain))
  }

  override def get(id: Long): Future[Option[Post]] = {
    db.run {
      Tables.Posts.filter(_.id === id).result.headOption
    }.map(_.map(PostMapper.toDomain))
  }

  override def create(post: Post): Future[Post] = {
    db.run {
      (Tables.Posts returning Tables.Posts.map(_.id)) += PostsRow(id = 0L, userId = post.userId, content = post.content)
    }.map(id => post.copy(id = id))
  }

  override def update(post: Post): Future[Option[Post]] = {
    db.run {
      Tables.Posts.filter(_.id === post.id).map(_.content).update(post.content)
    }.map(updateCodeToOptionalResult(post))
  }

  override def delete(id: Long): Future[Option[_]] = {
    db.run {
      Tables.Posts.filter(_.id === id).delete
    }.map(updateCodeToOptionalResult(id))
  }
}
