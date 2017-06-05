package io.github.vjames19.finatraexample.blog.api.endpoint

import javax.inject.Inject

import com.twitter.finatra.http.Controller
import com.twitter.finatra.request.RouteParam
import io.github.vjames19.finatraexample.domain.{Comment, Post}
import io.github.vjames19.finatraexample.service.{CommentService, PostService}

/**
  * Created by victor.reventos on 6/5/17.
  */
class PostController @Inject()(postService: PostService,
                               commentService: CommentService) extends Controller {

  get("/posts/:id") { request: GetPostRequest =>
    postService.get(request.id)
  }

  post("/posts/:postId/comments") { request: CreateCommentRequest =>
    commentService.create(Comment(id = 0L, postId = request.postId, userId = request.userId, text = request.text))
  }

  post("/posts") { request: CreatePostRequest =>
    postService.create(Post(id = 0L, content = request.content))
  }

}

case class GetPostRequest(@RouteParam id: Long)

case class CreatePostRequest(content: String)

case class CreateCommentRequest(@RouteParam postId: Long, userId: Long, text: String)


