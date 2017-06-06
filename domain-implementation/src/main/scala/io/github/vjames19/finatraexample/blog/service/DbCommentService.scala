package io.github.vjames19.finatraexample.blog.service

import javax.inject.{Inject, Singleton}

import io.github.vjames19.finatraexample.blog.domain.Comment
import io.github.vjames19.finatraexample.blog.models.Tables
import io.github.vjames19.finatraexample.blog.models.Tables.CommentsRow
import io.github.vjames19.finatraexample.blog.service.di.DbExecutionContext
import io.github.vjames19.finatraexample.blog.service.mapper.CommentMapper
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by victor.reventos on 6/5/17.
  */
@Singleton
class DbCommentService @Inject()(db: Database,
                                 @DbExecutionContext implicit val executionContext: ExecutionContext) extends CommentService with DbHelper {

  override def getCommentsForPost(postId: Long): Future[Seq[Comment]] = {
    db.run {
      Tables.Comments.filter(_.postId === postId).result
    }.map(_.map(CommentMapper.toDomain))
  }

  override def create(comment: Comment): Future[Comment] = {
    db.run {
      (Tables.Comments returning Tables.Comments.map(_.id)) +=
        CommentsRow(id = 0L, userId = comment.userId, postId = comment.postId, content = comment.text)
    }.map(id => comment.copy(id = id))
  }

  override def update(comment: Comment): Future[Option[Comment]] = {
    db.run {
      Tables.Comments.filter(_.id === comment.id)
        .map(_.content)
        .update(comment.text)
    }.map(updateCodeToOptionalResult(comment))
  }

  override def delete(id: Long): Future[Option[_]] = {
    db.run {
      Tables.Comments.filter(_.id === id).delete
    }.map(updateCodeToOptionalResult(id))
  }
}
