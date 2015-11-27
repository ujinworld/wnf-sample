name := "wnf-sample"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "org.apache.commons" % "commons-digester3" % "3.2",
  "commons-io" % "commons-io" % "2.4",
  "com.google.code.gson" % "gson" % "2.5",
  "org.apache.httpcomponents" % "httpclient" % "4.5.1",
  "org.apache.httpcomponents" % "httpasyncclient" % "4.1.1"
)     

play.Project.playScalaSettings
