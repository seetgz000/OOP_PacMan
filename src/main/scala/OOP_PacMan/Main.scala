package OOP_PacMan

import java.io.File

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Group, Node, Parent, Scene}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafx.Includes._
import javafx.{scene => jfxs}
import scalafx.scene.text.Font
import scalafx.stage.{Modality, Stage}
import Database.Database
import OOP_PacMan.controller.{GameOverController, PlayGameController}
import OOP_PacMan.component.{Coin, Pacman}
import OOP_PacMan.ghost.Ghosts
import OOP_PacMan.ghost.Ghosts.animationTimer
import User.Players
import scalafx.animation.PauseTransition
import scalafx.collections.ObservableBuffer
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.KeyCode
import scalafx.scene.layout.AnchorPane
import scalafx.util.Duration

object Main extends JFXApp with Movement{

  Database.setupDB()
  val rootResource = getClass.getResourceAsStream("view/MainMenu.fxml")
  val loader = new FXMLLoader(null, NoDependencyResolver)
  loader.load(rootResource)
  val roots = loader.getRoot[jfxs.layout.Pane]
  stage = new PrimaryStage {
    title = "Pac Man"
    maxWidth = 400
    minWidth = 400
    maxHeight = 700
    minHeight = 700
    scene = new Scene {
      root = roots
      Font.loadFont(getClass.getResourceAsStream("font/Fleftex_M.ttf"), 16)
      stylesheets add getClass.getResource("style/Style.css").toExternalForm
    }
  }

  val Userlist = new ObservableBuffer[Players]()

  Userlist ++= Players.getAllUsers

  /** close game */
  def closeGame() = {
    stage.close()
  }

  //play game page
  def playGame(): Unit = {
    val resource = getClass.getResourceAsStream("view/PlayGame3.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots2 = loader.getRoot[jfxs.layout.AnchorPane]
    stage.scene().setRoot(roots2)
//    stage.setMaxWidth(600)
    stage.setMinWidth(423)

    Ghost.preparingGhost

    showGameCanvas(roots2)
  }


  def showGameCanvas(root: AnchorPane): Unit = {

    val group = new Group() {

//      var thisWall: Node = PacmanMap.wallList.head
//      var moveableUp = Array(0)
//      var moveableDown = Array(0)
//      var moveableLeft = Array(0)
//      var moveableRight = Array(0)
//      var pacmanTester = new Pacman
      var pacman = new Pacman

      stage.scene().onKeyPressed = k =>{

        movement(pacman,k.getCode())
      }

      Ghosts.preparingGhost()

      children = List(
        pacman,
        Ghost.purpleGhost,
        Ghost.blueGhost,
        Ghost.coralGhost,
        Ghost.redGhost
      )
//
//      if(pacman.localToScene(pacman.getBoundsInLocal()).intersects(Ghosts.purpleGhost.localToScene(
//        Ghosts.purpleGhost.getBoundsInLocal()))){
//        println("startTime")
//        val timer = new PauseTransition(Duration(2000))
//        animationTimer.stop()
//        //death animation
//        pacman.setImage(new Image(new File("src/main/resource/OOP_PacMan/image/pacmanDeath.gif")
//          .toURI.toURL.toString))
//
//        timer.onFinished = e => {
//          Main.backToMain()
//          pacman.setImage(new Image(new File("src/main/resource/OOP_PacMan/image/pacmanGIF(fast).gif")
//            .toURI.toURL.toString))
//        } //end die
//        timer.play()
//      }
    }
    root.getChildren.add(group)

  }//end showgamecanvas


  //show high score page
  def showHighScore(): Unit = {
    val resource = getClass.getResourceAsStream("view/HighScores.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots2 = loader.getRoot[jfxs.layout.AnchorPane]
    stage.scene().setRoot(roots2)
  }

  def backToMain(): Unit = {
    stage.scene().setRoot(roots)
  }

  def showAddNew(players: Players): Boolean = {
    val resource = getClass.getResourceAsStream("view/GameOver.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots2 = loader.getRoot[jfxs.Parent]
    val control = loader.getController[GameOverController#Controller]

    val dialog = new Stage() {
      initModality(Modality.APPLICATION_MODAL)
      initOwner(stage)
      scene = new Scene {
        root = roots2
        stylesheets add getClass.getResource("style/Style.css").toExternalForm
      }
    }
    control.dialogStage = dialog
    control.players = players
    dialog.showAndWait()
    control.okClicked
  }
}