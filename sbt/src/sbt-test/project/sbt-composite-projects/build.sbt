import sbt.internal.AddSettings
import sbt.CompositeProject

lazy val check = taskKey[Unit]("check")

// Based on sbt-file-projects test
lazy val cross = new CompositeProject
{
  val p1 = Project.apply("a", new File("a"))
  val p2 = Project.apply("b", new File("b"))
  def componentProjects: Seq[Project] = Seq(p1, p2)
}

val g = taskKey[Unit]("A task in the root project")
g := println("Hello.")


check := {
  val verP1 = (version in cross.p1).?.value
  assert (verP1 == Some("0.1.0-SNAPSHOT"))

  val verP2 = (version in cross.p2).?.value
  assert (verP2 == Some("0.1.0-SNAPSHOT"))
}
