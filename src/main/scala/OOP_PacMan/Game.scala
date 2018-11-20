package OOP_PacMan

import javafx.{scene => jfxs}
import scalafx.Includes._
import scalafx.animation.PathTransition
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.beans.property.DoubleProperty
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.shape.{LineTo, MoveTo, Path}
import scalafx.scene.{Group, ImageCursor, Scene}
import scalafx.util.Duration
import scalafxml.core.{FXMLLoader, NoDependencyResolver}

object Game extends JFXApp {
  val rootResource = getClass.getResourceAsStream("view/Game.fxml")
  val loader = new FXMLLoader(null, NoDependencyResolver)
  loader.load(rootResource)
  val roots = loader.getRoot[jfxs.layout.BorderPane]

  /** Change Cursor Icon */
  val pacmanCursor = new Image(getClass.getResource("image/pacmanGIF(fast).gif").toString)

  /** Coordinate of Pacman */
  val pacmanX = new DoubleProperty
  val pacmanY = new DoubleProperty

  /** Pacman Movement Coordinate */
  var pacmanMoveX: Double = _
  var pacmanMoveY: Double = _

  /** Initial Pacman translation property */
  var initPacmanTranslateX: Double = _
  var initPacmanTranslateY: Double = _

  /** Initialize Pacman */
  val pacmanImg = new Image(getClass.getResource("image/pacman-open.png").toString)
  val pacman = new ImageView(pacmanImg) {
    fitWidth = 70
    preserveRatio = true
    x = 100
    y = 100
    translateX <== initPacmanTranslateX
    translateY <== initPacmanTranslateY
  }

  val pongComponents: Group = new Group {

    focusTraversable = true

    children = List(

      ball,

      topWall,

      leftWall,

      rightWall,

      bottomWall,

      leftPaddle,

      rightPaddle,

      startButton

    )

    onKeyPressed = k => k.code match {

      case KeyCode.Space if pongAnimation.status() == Status.STOPPED =>

        rightPaddleY() = rightPaddleY.value - 6

      case KeyCode.L if !rightPaddle.boundsInParent().intersects(topWall.boundsInLocal()) =>

        rightPaddleY() = rightPaddleY.value - 6

      case KeyCode.Comma if !rightPaddle.boundsInParent().intersects(bottomWall.boundsInLocal()) =>

        rightPaddleY() = rightPaddleY.value + 6

      case KeyCode.A if !leftPaddle.boundsInParent().intersects(topWall.boundsInLocal()) =>

        leftPaddleY() = leftPaddleY.value - 6

      case KeyCode.Z if !leftPaddle.boundsInParent().intersects(bottomWall.boundsInLocal()) =>

        leftPaddleY() = leftPaddleY.value + 6

      case _ =>

    }

  }


  stage = new PrimaryStage() {
    scene = new Scene {
      root = roots
      cursor = new ImageCursor(pacmanCursor)
    }
    title = "PacMan"
  }
}
