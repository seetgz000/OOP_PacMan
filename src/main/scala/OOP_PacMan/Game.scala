package OOP_PacMan

import javafx.{scene => jfxs}
import scalafx.Includes._
import scalafx.animation.PathTransition
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
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
w
  stage = new PrimaryStage () {
    scene = new Scene {
      root = roots
      cursor = new ImageCursor(pacmanCursor)
    }
    title = "PacMan"
  }

  def showPacman(): Unit = {

    val roots = new Group() {

      val pacman = new Image(getClass.getResource("image/pacman-open.png").toString)
      val pacmanV = new ImageView(pacman) {
        fitWidth = 70
        preserveRatio = true
        x = 100
        y = 100
      }

      children = List(
        pacmanV
      )

      onKeyPressed = (e:KeyEvent) => {
        if(e.code == KeyCode.Up) pacmanV.setTranslateY(pacmanV.getTranslateY - 5)
      }
    }
    this.roots.getChildren().add(roots)
  }
  showPacman()

}

