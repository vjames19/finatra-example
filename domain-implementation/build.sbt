val slickVersion = "3.2.0"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % slickVersion,
  "com.typesafe.slick" %% "slick-codegen" % slickVersion,
  "org.postgresql" % "postgresql" % "42.1.1",
  "com.google.inject" % "guice" % "4.0"
)

slick <<= slickCodeGenTask

sourceGenerators in Compile <+= slickCodeGenTask

// code generation task
lazy val slick = TaskKey[Seq[File]]("gen-tables")
lazy val slickCodeGenTask = (baseDirectory, sourceManaged, dependencyClasspath in Compile, runner in Compile, streams) map { (baseDir, dir, cp, r, s) =>
  val outputDir = (dir / "slick").getPath // place generated files in sbt's managed sources folder
//  val currentDir = baseDir.getPath
//  val sqlFiles = Seq("drop_tables.sql", "create.sql", "populate_tables.sql")
//  val initScript = sqlFiles.map(n => s"runscript from '$currentDir/src/main/sql/$n'").mkString("\\;")
//  val url = s"jdbc:h2:mem:test\\;INIT=$initScript" // connection info for a pre-populated throw-away, in-memory db for this demo, which is freshly initialized on every run

  val url = "jdbc:postgresql://localhost/blog?user=postgres&password=admin"
  val jdbcDriver = "org.postgresql.Driver"
  val slickDriver = "slick.jdbc.PostgresProfile"
  val pkg = "io.github.vjames19.finatraexample.blog.models"
  toError(r.run("slick.codegen.SourceCodeGenerator", cp.files, Array(slickDriver, jdbcDriver, url, outputDir, pkg), s.log))
  val fname = s"$outputDir/io/github/vjames19/finatraexample/blog/models/Tables.scala"
  Seq(file(fname))
}