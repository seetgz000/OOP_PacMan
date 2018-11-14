name := "OOP_PacMan"

version := "0.1"

scalaVersion := "2.12.7"

// https://mvnrepository.com/artifact/org.scalafx/scalafx
libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.181-R13"

// https://mvnrepository.com/artifact/org.scalafx/scalafxml-core-sfx8
libraryDependencies += "org.scalafx" %% "scalafxml-core-sfx8" % "0.4"

// https://mvnrepository.com/artifact/mysql/mysql-connector-java
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.13"

resourceDirectory in Compile := baseDirectory.value / "src/main/resource"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)
