package OOP_PacMan.component

import java.io.File

import scalafx.beans.property.DoubleProperty
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.media.{Media, MediaPlayer}

class Pacman extends ImageView {
  /** Translation of Pacman */
  val pacmanX = new DoubleProperty
  val pacmanY = new DoubleProperty

  /** Initialize properties of Pacman */
  val pacmanImg = new Image(new File("src/main/resource/OOP_PacMan/image/pacmanGIF(fast).gif").toURI.toURL.toString)
  val pacmanW = 20
  val pacmanH = 20
  val pacmanStyle: Iterable[String] = List("pacman")

  /** Initialize Pacman */

    image = pacmanImg
    styleClass= pacmanStyle
    fitWidth = pacmanW
    preserveRatio = true
    x = 25
    y = 90

  def playMovingSound = {
    val move =new Media (new File("src/main/resource/OOP_PacMan/audio/move.wav").toURI.toURL.toString)
    new MediaPlayer(move).play
    //    player.play()
  }
}
