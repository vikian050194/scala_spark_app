name := "scala_spark_app"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.1.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % "test"

libraryDependencies += "org.apache.kafka" %% "kafka" % "2.3.1"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.4.2"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.2"
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.2"