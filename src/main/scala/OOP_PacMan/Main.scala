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
import OOP_PacMan.controller.{PlayGameController, PlayerPageController}
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

    showGameCanvas(roots2)

  }

  def showGameCanvas(root: AnchorPane): Unit = {

    val group = new Group() {
      /** Coordinate of Pacman */
      val pacmanX = new DoubleProperty
      val pacmanY = new DoubleProperty

      /** Initialize Pacman */
      val pacmanImg = new Image(new File("src/main/resource/OOP_PacMan/image/pacmanGIF(fast).gif").toURI.toURL.toString)
      val pacmanW = 40
      val pacmanH = 40


      val pacman = new ImageView(pacmanImg) {
        fitWidth = 40
        preserveRatio = true
        x = 100
        y = 100
        translateX <== pacmanX
        translateY <== pacmanY
      }
      stage.scene().onKeyPressed = k => k.code match {

        case KeyCode.W =>

          //        canvas.translateY = canvas.translateY.value - 6
          pacmanY() = pacmanY.value - 6
          print(pacmanX, pacmanY)

        case KeyCode.A =>
          //        canvas.translateX= canvas.translateX.value - 6
          pacmanX() = pacmanX.value - 6
          print(pacmanX, pacmanY)

        case KeyCode.S =>
          //        canvas.translateY = canvas.translateY.value + 6
          pacmanY() = pacmanY.value + 6
          print(pacmanX, pacmanY)

        case KeyCode.D =>
          //        canvas.translateX = canvas.translateX.value + 6
          pacmanX() = pacmanX.value + 6
          print(pacmanX, pacmanY)

        case _ =>

      }

      children = List(
        pacman
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
      val resource = getClass.getResourceAsStream("view/PlayerPage.fxml")
      val loader = new FXMLLoader(null, NoDependencyResolver)
      loader.load(resource);
      val roots2  = loader.getRoot[jfxs.Parent]
      val control = loader.getController[PlayerPageController#Controller]

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