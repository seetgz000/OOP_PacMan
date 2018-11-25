package OOP_PacMan

import java.io.File

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Group, Parent, Scene}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafx.Includes._
import javafx.{scene => jfxs}
import scalafx.scene.text.Font
import scalafx.stage.{Modality, Stage}
import Database.Database
import OOP_PacMan.controller.{GameOverController, PlayGameController}
import OOP_PacMan.ghost.Ghost
import User.Players
import scalafx.beans.property.DoubleProperty
import scalafx.collections.ObservableBuffer
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.KeyCode
import scalafx.scene.layout.AnchorPane

object Main extends JFXApp {

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

  //close game
  def closeGame()={
    stage.close()
  }

  //play game page
  def playGame(): Unit = {
    val resource = getClass.getResourceAsStream("view/PlayGame3.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots2 = loader.getRoot[jfxs.layout.AnchorPane]
    stage.scene().setRoot(roots2)
    stage.setMaxWidth(423)
    stage.setMinWidth(423)

    Ghost.preparingGhost

    showGameCanvas(roots2)
  }

  def showGameCanvas(root: AnchorPane): Unit = {

    val group = new Group() {
      /** Translation of Pacman */
      val pacmanX = new DoubleProperty
      val pacmanY = new DoubleProperty

      /** Initialize Pacman */
      val pacmanImg = new Image(new File("src/main/resource/OOP_PacMan/image/pacmanGIF(fast).gif").toURI.toURL.toString)
      val pacmanW = 20
      val pacmanH = 20


      val pacman = new ImageView(pacmanImg) {
        fitWidth = pacmanW
        preserveRatio = true
        x = 25
        y = 90
        translateX <== pacmanX
        translateY <== pacmanY
      }
      val resource = getClass.getResourceAsStream("view/PlayGame3.fxml")
      val loader = new FXMLLoader(null, NoDependencyResolver)
      loader.load(resource);
      val playGameCtrl = loader.getController[PlayGameController#Controller]
      val ground = playGameCtrl.ground
      val wall = playGameCtrl.wall
      val coin = playGameCtrl.coin
      val map = playGameCtrl.map1

      var escCondition:Boolean =true

      stage.scene().onKeyPressed = k => k.code match {

        case KeyCode.W
//          if !pacman.boundsInParent().intersects(playGameCtrl.groundList.boundsInLocal())
          if !(pacmanY() <= 0)
        =>
          //        canvas.translateY = canvas.translateY.value - 6
          pacmanY() = pacmanY.value - 6
//          print(pacmanX.value,pacmanY.value)
        case KeyCode.A
          //        canvas.translateX= canvas.translateX.value - 6
          if !(pacmanX() <= 0)
            =>
          pacmanX() = pacmanX.value - 6
//          print(pacmanX.value,pacmanY.value)
        case KeyCode.S
          if !(pacmanY() >= 432)
        =>
          //        canvas.translateY = canvas.translateY.value + 6
          pacmanY() = pacmanY.value + 6
//          print(pacmanX.value,pacmanY.value)
        case KeyCode.D
          if !(pacmanX() >= 336)
        =>
          //        canvas.translateX = canvas.translateX.value + 6
          pacmanX() = pacmanX.value + 6
//          print(pacmanX.value,pacmanY.value)

        case KeyCode.Escape
        =>
          if(escCondition) {
            Ghost.animationTimer.stop()
              escCondition = false
          }
          else {
            Ghost.animationTimer.start()
            escCondition = true
          }

        case _ =>

      }

      children = List(
        pacman,
        Ghost.purpleGhost,
        Ghost.blueGhost,
        Ghost.coralGhost,
        Ghost.redGhost
      )
    }

    root.getChildren.add(group)
  }


  //show high score page
  def showHighScore():Unit ={
    val resource = getClass.getResourceAsStream("view/HighScores.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots2 = loader.getRoot[jfxs.layout.AnchorPane]
    stage.scene().setRoot(roots2)
  }

  def backToMain():Unit ={
    stage.scene().setRoot(roots)
  }

  def showAddNew(players: Players):Boolean = {
      val resource = getClass.getResourceAsStream("view/GameOver.fxml")
      val loader = new FXMLLoader(null, NoDependencyResolver)
      loader.load(resource);
      val roots2  = loader.getRoot[jfxs.Parent]
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