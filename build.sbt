name := "finatra-example"

lazy val commonSettings = Seq(
  organization := "io.github.vjames19",
  version := "1.0",
  scalaVersion := "2.12.2"
)

lazy val noMigrate = Seq(
  flywayMigrate := {}
) ++ commonSettings

lazy val root = (project in file("."))
  .settings(noMigrate)
  .aggregate(domain, domainImplementation, finatraApi)

lazy val domain = (project in file("domain"))
  .settings(noMigrate)

lazy val domainImplementation = (project in file("domain-implementation"))
  .settings(commonSettings)
  .dependsOn(domain)


lazy val finatraApi = (project in file("finatra-api"))
  .settings(noMigrate)
  .dependsOn(domainImplementation)

run := (run in Compile in finatraApi).evaluated