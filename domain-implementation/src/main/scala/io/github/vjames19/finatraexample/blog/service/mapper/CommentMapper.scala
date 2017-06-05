package io.github.vjames19.finatraexample.blog.service.mapper

import io.github.vjames19.finatraexample.blog.domain.Comment
import io.github.vjames19.finatraexample.blog.models.Tables.CommentsRow

/**
  * Created by victor.reventos on 6/5/17.
  */
object CommentMapper {

  def toDomain(commentsRow: CommentsRow): Comment = {
    Comment(id = commentsRow.id,
      userId = commentsRow.userId,
      postId = commentsRow.postId,
      text = commentsRow.content)
  }

}
