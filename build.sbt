name := "finatra-example"

version := "1.0"

scalaVersion := "2.12.2"

lazy val commonSettings = Seq(
  organization := "io.github.vjames19",
  version := "1.0",
  scalaVersion := "2.12.2"
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .aggregate(domain, finatraApi)

lazy val domain = (project in file("domain"))
  .settings(commonSettings)

lazy val finatraApi = (project in file("finatra-api"))
  .settings(commonSettings)
  .dependsOn(domain)