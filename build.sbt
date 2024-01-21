Global / onChangedBuildSource := IgnoreSourceChanges

name := "ingest-feed"

version := "0.1"

scalaVersion := "2.12.12"

val sparkVersion = "3.0.0"

mainClass := Some("com.develop24k.SQLContextExample")

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion
libraryDependencies += "com.typesafe" % "config" % "1.4.2"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF","services",xs @ _*) => MergeStrategy.filterDistinctLines // Added this
  case PathList("META-INF",xs @ _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

