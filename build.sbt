name := "Scala-Algorithms"

lazy val root = Project("Scala-Algorithms", file(".")).aggregate(adventCode)

lazy val adventCode = Project("advent-code", file("advent-code"))
        .settings(version := "1.0.0",
                     scalaVersion := "2.11.7",
                     libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "2.2.4" % "test"),
                     scalacOptions ++= Seq("-deprecation", "-feature"))
