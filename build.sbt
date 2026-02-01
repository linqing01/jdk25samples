lazy val SCALA_VERSION = "3.3.7"

lazy val samples = (project in file("."))
    .settings(
	name := "jdk25",
        scalaVersion := SCALA_VERSION
    )

