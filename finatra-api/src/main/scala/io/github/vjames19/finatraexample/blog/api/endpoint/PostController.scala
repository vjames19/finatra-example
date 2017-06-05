package io.github.vjames19.finatraexample.blog.api.endpoint

import javax.inject.Inject

import com.twitter.finatra.http.Controller
import com.twitter.finatra.request.RouteParam
import com.twitter.finatra.validation.NotEmpty
import io.github.vjames19.finatraexample.blog.domain.{Comment, Post}
import io.github.vjames19.finatraexample.blog.service.{CommentService, PostService}

/**
  * Created by victor.reventos on 6/5/17.
  */
class PostController @Inject()(postService: PostService,
                               commentService: CommentService) extends Controller {
  post("/posts") { request: CreatePostRequest =>
    postService.create(request.toDomain())
  }

  get("/posts/:id") { request: GetPostRequest =>
    postService.get(request.id)
  }

  post("/posts/:postId/comments") { request: CreateCommentRequest =>
    commentService.create(request.toDomain())
  }

  get("/posts/:postId/comments") { request: GetPostCommentsRequest =>
    commentService.getCommentsForPost(request.id)
  }

  put("/posts/:postId/comments/:commentId") { request: CreateCommentRequest =>
    commentService.update(request.toDomain())
  }

  delete("/posts/:postId/comments/:commentId") { request: DeleteCommentRequest =>
    commentService.delete(request.commentId)
  }
}

case class GetPostRequest(@RouteParam id: Long)

case class CreatePostRequest(userId: Long, @NotEmpty content: String) {
  def toDomain(): Post = {
    Post(id = 0L, userId = userId, content = content)
  }
}

case class GetPostCommentsRequest(@RouteParam id: Long)

case class DeleteCommentRequest(@RouteParam postId: Long, @RouteParam commentId: Long)

case class CreateCommentRequest(@RouteParam postId: Long, userId: Long, text: String) {

  def toDomain(): Comment = {
    Comment(id = 0L, postId = postId, userId = userId, text = text)
  }
}


