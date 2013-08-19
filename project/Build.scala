import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "yata"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,

    "uk.co.panaxiom" %% "play-jongo" % "0.4"

  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
        resolvers += Resolver.url("My GitHub Play Repository", url("http://alexanderjarvis.github.com/releases/"))(Resolver.ivyStylePatterns),

        /* workaround for run tests */
        testOptions in Test ~= { args =>
            for {
                arg <- args
                val ta: Tests.Argument = arg.asInstanceOf[Tests.Argument]
                val newArg = if(ta.framework == Some(TestFrameworks.JUnit)) ta.copy(args = List.empty[String]) else ta
            } yield newArg
        }
  )

}
