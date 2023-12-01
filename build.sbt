import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "advent"

lazy val root = (project in file("."))
  .settings(
    name := "day01",
    libraryDependencies += scalaTest % Test
  )