package io.github.vjames19.finatraexample.blog.service.mapper

import io.github.vjames19.finatraexample.blog.domain.Post
import io.github.vjames19.finatraexample.blog.models.Tables.PostsRow

/**
  * Created by victor.reventos on 6/5/17.
  */
object PostMapper {

  def toDomain(post: PostsRow): Post = {
    Post(id = post.id, userId = post.id, content = post.content)
  }
}
