package OOP_PacMan.Component

import java.io.File

import scalafx.beans.property.DoubleProperty
import scalafx.scene.image.{Image, ImageView}

class pacman extends ImageView {
  /** Translation of Pacman */
  val pacmanX = new DoubleProperty
  val pacmanY = new DoubleProperty

  /** Initialize properties of Pacman */
  val pacmanImg = new Image(new File("src/main/resource/OOP_PacMan/image/pacmanGIF(fast).gif").toURI.toURL.toString)
  val pacmanW = 22
  val pacmanH = 22
  val pacmanStyle: Iterable[String] = List("pacman")

  /** Initialize Pacman */


    image = pacmanImg
    styleClass= pacmanStyle
    fitWidth = pacmanW
    preserveRatio = true
    x = 25
    y = 90
    translateX <== pacmanX
    translateY <== pacmanY
}
