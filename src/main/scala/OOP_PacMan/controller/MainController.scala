package OOP_PacMan.controller

import java.io.FileInputStream

import OOP_PacMan.Main
import scalafx.animation.PathTransition
import scalafx.event.ActionEvent
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{HBox, StackPane}
import scalafx.scene.shape.{LineTo, MoveTo, Path}
import scalafx.util.Duration
import scalafxml.core.macros.sfxml

@sfxml
class MainController(
                      private val transition1: HBox,
                      private val ghostV: ImageView,
                      private val pacmanV: ImageView,
                      private val close : ImageView,
                      private val directionalKeys: ImageView,
                      private val enterKey: ImageView,
                      private val spacebar: ImageView,
                      private val introRoot:StackPane
                    ) {

  val closeBtnImg = new Image(new FileInputStream("src/main/resource/OOP_PacMan/image/close.png"))
  val directionalImg = new Image(new FileInputStream("src/main/resource/OOP_PacMan/image/keyboard.png"))
  val enterImg = new Image(new FileInputStream("src/main/resource/OOP_PacMan/image/enter.png"))
  val spacebarImg = new Image(new FileInputStream("src/main/resource/OOP_PacMan/image/spacebar.png"))

  val ghost = new Image(new FileInputStream("src/main/resource/OOP_PacMan/image/4ghost-img.png"))
  val pacman = new Image(new FileInputStream("src/main/resource/OOP_PacMan/image/pacmanGIF(fast).gif"))

  //added functions for buttons
  def startGame(action: ActionEvent)={
    Main.playGame()
  }

  def showHighScore(actionEvent: ActionEvent) = {
    Main.showHighScore()
  }

  //for introduction pop up
  def startIntro(action:ActionEvent)={
    introRoot.toFront()//time to bring it out
  }
  def closeIntro(action:ActionEvent)={
    introRoot.toBack()//hide it again as its closed
  }

  def closeGame(actionEvent: ActionEvent) ={
    Main.closeGame()
  }

  // animation for pacman chasing ghosts
  val path = new Path(){
    elements=List(
      MoveTo (-250, 100),
      LineTo (600, 100)
    )
  }

  //set image for introduction pop up
  close.setImage(closeBtnImg)
  directionalKeys.setImage(directionalImg)
  enterKey.setImage(enterImg)
  spacebar.setImage(spacebarImg)

  //set image for animation at main menu
  ghostV.setImage(ghost)
  pacmanV.setImage(pacman)

  val transition = new PathTransition(Duration(2500), path, transition1) {
    cycleCount = PathTransition.Indefinite
    autoReverse = false
  }
  transition.play()

}
