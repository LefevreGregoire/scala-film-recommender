name := "scala-recommender"

version := "0.1.0"

scalaVersion := "3.5.1"

libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test

scalacOptions ++= Seq(
  "-deprecation",
  "-feature"
)
