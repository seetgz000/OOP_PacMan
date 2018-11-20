package OOP_PacMan.controller

import java.io.FileInputStream

import OOP_PacMan.Main
import scalafx.animation.PathTransition
import scalafx.event.ActionEvent
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.HBox
import scalafx.scene.shape.{LineTo, MoveTo, Path}
import scalafx.util.Duration
import scalafxml.core.macros.sfxml

@sfxml
class MainController(
                      private val transition1: HBox,
                      private val ghostV: ImageView,
                      private val pacmanV: ImageView
                    ) {

  def startGame(action: ActionEvent)={
    Main.playGame()
  }

  def showHighScore(actionEvent: ActionEvent) = {
    Main.showHighScore()
  }

  def startIntro(action:ActionEvent)={
    Main.showIntroduction()
  }

  // animation for pacman chasing ghosts
  val path = new Path(){
    elements=List(
      MoveTo (-250, 100),
      LineTo (600, 100)
    )
  }

  val ghost = new Image(new FileInputStream("src/main/resource/OOP_PacMan/image/4ghost-img.png"))
  ghostV.setImage(ghost)

  val pacman = new Image(new FileInputStream("src/main/resource/OOP_PacMan/image/pacmanGIF(fast).gif"))
  pacmanV.setImage(pacman)

  val transition = new PathTransition(Duration(2500), path, transition1) {
    cycleCount = PathTransition.Indefinite
    autoReverse = false
  }
  transition.play()

}
