package OOP_PacMan

import java.io.File

import javafx.scene.ImageCursor
import scalafx.animation.PathTransition
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.effect.DropShadow
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.media.{Media, MediaPlayer, MediaView}
import scalafx.scene.paint.Color
import scalafx.scene.shape.{LineTo, MoveTo, Path, Rectangle}
import scalafx.scene.text.{Font, Text}
import scalafx.scene.{Cursor, Group, Scene}
import scalafx.util.Duration
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafx.Includes._
import javafx.{scene => jfxs}
import scalafx.scene.canvas.Canvas
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.StackPane

import scala.io.Source

object testCanvas extends JFXApp {
  val rootResource = getClass.getResourceAsStream("view/Game.fxml")
  val loader = new FXMLLoader(null, NoDependencyResolver)
  loader.load(rootResource)
  val roots = loader.getRoot[jfxs.layout.BorderPane]
  val pacmanCursor = new Image(getClass.getResource("image/pacmanGIF(fast).gif").toString)
  stage = new PrimaryStage () {
    scene = new Scene(500,500) {
      root = roots
      cursor = new ImageCursor(pacmanCursor)
    }
    title = "PacMan"
  }



  def showGameCanvas(): Unit = {

    val roots = new Group() {

      val canvas = new Canvas(600, 400)
      val gc = canvas.graphicsContext2D


      canvas.onMouseMoved = (e:MouseEvent) => {
        //      gc.clearRect(0, 0, canvas.width.value, canvas.height.value)
        gc.fillOval(e.x, e.y, 10, 10)
      }

      val pacman = new Image(getClass.getResource("image/pacman-open.png").toString)
      val pacmanV = new ImageView(pacman) {
        fitWidth = 70
        preserveRatio = true
        x = 100
        y = 100
      }
      val path = new Path() {
        elements = List(
          MoveTo(80, 70),
          LineTo(80, 90),
          LineTo(110, 90),
          LineTo(80, 70)
        )
      }

      val transition = new PathTransition(Duration(500), path, pacmanV){
        cycleCount = PathTransition.Indefinite
        autoReverse = false
      }
      transition.play()

      children = List(
        pacmanV,
        canvas
      )
    }
    this.roots.setCenter(roots)

  }
  showGameCanvas()

}