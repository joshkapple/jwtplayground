name := "JWTPlayground"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.13.8"

organization := "com.joshkapple.jwt-playground"
mainClass in assembly := Some("Main")

libraryDependencies ++= Seq(
  "com.nimbusds" % "nimbus-jose-jwt" % "9.25.6"
)
