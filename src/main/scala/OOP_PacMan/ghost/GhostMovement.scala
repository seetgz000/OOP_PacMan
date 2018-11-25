package OOP_PacMan.ghost

import scala.language.postfixOps
import scalafx.Includes._
import scalafx.beans.property.DoubleProperty

//reminder to self: https://stackoverflow.com/questions/15013913/checking-collision-of-shapes-with-javafx refer this for collision
trait GhostMovement {

  //val timeline = new Timeline(){
    //        keyFrames = Seq(
    //          at (0.8 s) {coorX -> toCoordinateX}
    //        )
    //      }

  def movement(directionNo:Int, coorX: DoubleProperty,coorY:DoubleProperty): Unit ={
    directionNo match {
      case 1 =>
        if (coorX() <= 327)
          coorX() = coorX.value + 5

      case 2 =>
        if (coorX() >= 5)
          coorX() = coorX.value - 5

      case 3 =>
        if (coorY() <= 427)
          coorY() = coorY.value + 5

      case 4 =>
        if (coorY() >= 5)
          coorY() = coorY.value - 5

      case _=>

    }
  }
}
