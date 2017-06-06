package io.github.vjames19.finatraexample.blog.models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Comments.schema ++ Posts.schema ++ Users.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Comments
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param userId Database column user_id SqlType(int8)
   *  @param postId Database column post_id SqlType(int8)
   *  @param content Database column content SqlType(varchar), Length(1000,true) */
  final case class CommentsRow(id: Long, userId: Long, postId: Long, content: String)
  /** GetResult implicit for fetching CommentsRow objects using plain SQL queries */
  implicit def GetResultCommentsRow(implicit e0: GR[Long], e1: GR[String]): GR[CommentsRow] = GR{
    prs => import prs._
    CommentsRow.tupled((<<[Long], <<[Long], <<[Long], <<[String]))
  }
  /** Table description of table comments. Objects of this class serve as prototypes for rows in queries. */
  class Comments(_tableTag: Tag) extends profile.api.Table[CommentsRow](_tableTag, "comments") {
    def * = (id, userId, postId, content) <> (CommentsRow.tupled, CommentsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(userId), Rep.Some(postId), Rep.Some(content)).shaped.<>({r=>import r._; _1.map(_=> CommentsRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id SqlType(int8) */
    val userId: Rep[Long] = column[Long]("user_id")
    /** Database column post_id SqlType(int8) */
    val postId: Rep[Long] = column[Long]("post_id")
    /** Database column content SqlType(varchar), Length(1000,true) */
    val content: Rep[String] = column[String]("content", O.Length(1000,varying=true))

    /** Foreign key referencing Posts (database name comments_post_id_fkey) */
    lazy val postsFk = foreignKey("comments_post_id_fkey", postId, Posts)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Users (database name comments_user_id_fkey) */
    lazy val usersFk = foreignKey("comments_user_id_fkey", userId, Users)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Comments */
  lazy val Comments = new TableQuery(tag => new Comments(tag))

  /** Entity class storing rows of table Posts
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param userId Database column user_id SqlType(int8)
   *  @param content Database column content SqlType(text) */
  final case class PostsRow(id: Long, userId: Long, content: String)
  /** GetResult implicit for fetching PostsRow objects using plain SQL queries */
  implicit def GetResultPostsRow(implicit e0: GR[Long], e1: GR[String]): GR[PostsRow] = GR{
    prs => import prs._
    PostsRow.tupled((<<[Long], <<[Long], <<[String]))
  }
  /** Table description of table posts. Objects of this class serve as prototypes for rows in queries. */
  class Posts(_tableTag: Tag) extends profile.api.Table[PostsRow](_tableTag, "posts") {
    def * = (id, userId, content) <> (PostsRow.tupled, PostsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(userId), Rep.Some(content)).shaped.<>({r=>import r._; _1.map(_=> PostsRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id SqlType(int8) */
    val userId: Rep[Long] = column[Long]("user_id")
    /** Database column content SqlType(text) */
    val content: Rep[String] = column[String]("content")

    /** Foreign key referencing Users (database name posts_user_id_fkey) */
    lazy val usersFk = foreignKey("posts_user_id_fkey", userId, Users)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Posts */
  lazy val Posts = new TableQuery(tag => new Posts(tag))

  /** Entity class storing rows of table Users
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param username Database column username SqlType(varchar), Length(255,true) */
  final case class UsersRow(id: Long, username: String)
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Long], e1: GR[String]): GR[UsersRow] = GR{
    prs => import prs._
    UsersRow.tupled((<<[Long], <<[String]))
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends profile.api.Table[UsersRow](_tableTag, "users") {
    def * = (id, username) <> (UsersRow.tupled, UsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(username)).shaped.<>({r=>import r._; _1.map(_=> UsersRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column username SqlType(varchar), Length(255,true) */
    val username: Rep[String] = column[String]("username", O.Length(255,varying=true))

    /** Uniqueness Index over (username) (database name users_username_key) */
    val index1 = index("users_username_key", username, unique=true)
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
}
