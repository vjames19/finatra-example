val slickVersion = "3.2.0"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % slickVersion,
  "com.typesafe.slick" %% "slick-codegen" % slickVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0",
  "org.postgresql" % "postgresql" % "42.1.1",
  "com.google.inject" % "guice" % "4.0"
)

// Automatic generation with each compile execution i.e sbt compile
//sourceGenerators in Compile <+= slickCodeGenTask

// code generation task
lazy val slick: TaskKey[Seq[File]] = taskKey[Seq[File]]("Generate code from the DB tables")

slick := {
  val outputDir = (baseDirectory.value / "src/main/scala").getPath // place generated files in sbt's managed sources folder
  //  val currentDir = baseDir.getPath
  //  val sqlFiles = Seq("drop_tables.sql", "create.sql", "populate_tables.sql")
  //  val initScript = sqlFiles.map(n => s"runscript from '$currentDir/src/main/sql/$n'").mkString("\\;")
  //  val url = s"jdbc:h2:mem:test\\;INIT=$initScript" // connection info for a pre-populated throw-away, in-memory db for this demo, which is freshly initialized on every run

  val url = "jdbc:postgresql://localhost/blog?user=postgres&password=postgres"
  val jdbcDriver = "org.postgresql.Driver"
  val slickDriver = "slick.jdbc.PostgresProfile"
  val pkg = "io.github.vjames19.finatraexample.blog.models"
  toError((runner in Compile).value.run("slick.codegen.SourceCodeGenerator", (dependencyClasspath in Compile).value.files, Array(slickDriver, jdbcDriver, url, outputDir, pkg), streams.value.log))
  val fname = s"$outputDir/io/github/vjames19/finatraexample/blog/models/Tables.scala"
  Seq(file(fname))
}


// flyway
flywayUrl := "jdbc:postgresql://localhost/blog"
flywayUser := "postgres"
// This should be added through an environment variable, doing it like this for simplicity.
flywayPassword := "postgres"

flywayBaselineOnMigrate := true
flywayLocations := Seq("classpath:db/migration")