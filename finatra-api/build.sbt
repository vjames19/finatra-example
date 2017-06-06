lazy val versions = new {
  val finatra = "2.10.0"
  val scalatest = "3.0.0"
  val guice = "4.0"
  val mockito = "1.9.5"
}

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.1",
  "com.iheart" %% "ficus" % "1.4.0",
  "com.zaxxer" % "HikariCP" % "2.6.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3",

  "com.twitter" %% "finatra-http" % versions.finatra,

  "com.twitter" %% "finatra-http" % versions.finatra % "test",
  "com.twitter" %% "inject-server" % versions.finatra % "test",
  "com.twitter" %% "inject-app" % versions.finatra % "test",
  "com.twitter" %% "inject-core" % versions.finatra % "test",
  "com.twitter" %% "inject-modules" % versions.finatra % "test",
  "com.google.inject.extensions" % "guice-testlib" % versions.guice % "test",

  "com.twitter" %% "finatra-http" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-server" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-app" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-core" % versions.finatra % "test" classifier "tests",
  "com.twitter" %% "inject-modules" % versions.finatra % "test" classifier "tests",

  "org.mockito" % "mockito-core" % versions.mockito % "test",
  "org.scalatest" %% "scalatest" % versions.scalatest % "test"
)