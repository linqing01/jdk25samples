lazy val SCALA_VERSION = "3.3.7"

lazy val samples = (project in file("."))
    .settings(
        scalaVersion := SCALA_VERSION
    )

lazy val threads = (project in file("threads"))
    .settings(
        name := "threads",
        scalaVersion := SCALA_VERSION
    )


lazy val jdk25 = (project in file("jdk25"))
    .settings(
        name := "jdk25",
        scalaVersion := SCALA_VERSION
    )
