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
import OOP_PacMan.ghost.GhostAnimation
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

  /**retrieve database names and score*/
  val Userlist = new ObservableBuffer[Players]()

  Userlist ++= Players.getAllUsers

  /**new pacman*/
  var pacman = new Pacman


  /** close game */
  def closeGame() = {
    stage.close()
  }

  /** play game page*/
  def playGame(): Unit = {
    val resource = getClass.getResourceAsStream("view/PlayGame3.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots2 = loader.getRoot[jfxs.layout.AnchorPane]
    stage.scene().setRoot(roots2)
//    stage.setMaxWidth(600)
    stage.setMinWidth(423)

    showGameCanvas(roots2)
    GhostAnimation.preparingGhost()

  }


  //var thisWall: Node = PacmanMap.wallList.head


  def showGameCanvas(root: AnchorPane): Unit = {
    pacman.translateX() = 0
    pacman.translateY() = 0

    //return ghosts to original position and start animation
    GhostAnimation.preparingGhost()

    val group = new Group() {

      stage.scene().onKeyPressed = k =>{
        if(!GhostAnimation.died){
          movement(pacman,k.getCode(),6)
        }
      }

      children = List(
        pacman,
        GhostAnimation.purpleGhost,
//        GhostAnimation.blueGhost,
        GhostAnimation.coralGhost,
//        GhostAnimation.redGhost
      )
    }//end group
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
    control.players = players
    control.dialogStage = dialog
    dialog.showAndWait()
    control.okClicked
  }
}