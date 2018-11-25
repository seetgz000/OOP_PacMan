package OOP_PacMan.ghost

import scala.language.postfixOps
import scalafx.Includes._
import scalafx.beans.property.DoubleProperty

//reminder to self: https://stackoverflow.com/questions/15013913/checking-collision-of-shapes-with-javafx refer this for collision

trait GhostMovement {

  val ghostW = 25
  val ghostH = 25

  def movement(directionNo:Int, coorX: DoubleProperty,coorY:DoubleProperty,speed:Double): Unit ={
    directionNo match {
      case 1 =>
        if (coorX() <= 327)
          coorX() = coorX.value + speed

      case 2 =>
        if (coorX() >= 5)
          coorX() = coorX.value - speed

      case 3 =>
        if (coorY() <= 427)
          coorY() = coorY.value + speed

      case 4 =>
        if (coorY() >= 5)
          coorY() = coorY.value - speed

      case _=>

    }
  }
}
