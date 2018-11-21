package OOP_PacMan

import javafx.{scene => jfxs}
import scalafx.Includes._
import javafx.animation.Animation.Status
import scalafx.animation.Timeline
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.beans.property.DoubleProperty
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.KeyCode
import scalafx.scene.{Group, ImageCursor, Scene}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}

object Game extends JFXApp {
  val rootResource = getClass.getResourceAsStream("view/PlayGame3.fxml")
  val loader = new FXMLLoader(null, NoDependencyResolver)
  loader.load(rootResource)
  val roots = loader.getRoot[jfxs.layout.AnchorPane]

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
    fitWidth = 40
    preserveRatio = true
    x = 100
    y = 100
    translateX <== pacmanX
    translateY <== pacmanY
  }


  val pacmanComponent: Group = new Group {

    focusTraversable = true

    children = List(
      pacman

    )

    onKeyPressed = k => k.code match {

      case KeyCode.W =>

        pacmanY() = pacmanY.value - 6

      case KeyCode.A =>

        pacmanX() = pacmanX.value - 6

      case KeyCode.S =>

        pacmanY() = pacmanY.value + 6

      case KeyCode.D =>

        pacmanX() = pacmanX.value + 6

      case _ =>

    }

  }


  stage = new PrimaryStage() {
    scene = new Scene {
      root = roots
      cursor = new ImageCursor(pacmanCursor)
      content = pacmanComponent
    }
    title = "PacMan"
  }
}
