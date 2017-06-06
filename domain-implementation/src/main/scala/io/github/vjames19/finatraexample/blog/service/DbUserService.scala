package io.github.vjames19.finatraexample.blog.service

import javax.inject.{Inject, Singleton}

import io.github.vjames19.finatraexample.blog.domain.User
import io.github.vjames19.finatraexample.blog.models.Tables
import io.github.vjames19.finatraexample.blog.models.Tables.UsersRow
import io.github.vjames19.finatraexample.blog.service.di.DbExecutionContext
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by victor.reventos on 6/5/17.
  */
@Singleton
class DbUserService @Inject()(db: Database,
                              @DbExecutionContext implicit val executionContext: ExecutionContext) extends UserService {

  override def get(id: Long): Future[Option[User]] = {
    db.run {
      Tables.Users.filter(_.id === id).result.headOption
    }.map(_.map(r => User(r.id, r.username)))
  }

  override def create(user: User): Future[User] = {
    db.run {
      (Tables.Users returning Tables.Users.map(_.id)) += UsersRow(id = 0, username = user.username)
    }.map(id => user.copy(id = id))
  }
}
