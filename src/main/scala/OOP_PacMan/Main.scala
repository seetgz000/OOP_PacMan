package OOP_PacMan
import java.io.File

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Parent, Scene}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafx.Includes._
import javafx.{scene => jfxs}
import scalafx.scene.media.Media
import scalafx.scene.text.Font
import scalafx.stage.{Modality, Stage}
import Database.Database
import OOP_PacMan.controller.PlayerPageController
import User.Players
import scalafx.collections.ObservableBuffer

object Main extends JFXApp {

  Database.setupDB()
  val rootResource = getClass.getResourceAsStream("view/MainMenu.fxml")
  val loader = new FXMLLoader(null, NoDependencyResolver)
  loader.load(rootResource)
  val roots = loader.getRoot[jfxs.layout.BorderPane]
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

  //introduction page
  val resource = getClass.getResourceAsStream("view/Introduction.fxml")
  val introLoader = new FXMLLoader(null, NoDependencyResolver)
  introLoader.load(resource);
  val introRoot = introLoader.getRoot[jfxs.Parent]

  def showIntroduction():Unit ={
    roots.setCenter(introRoot)
  }

  def closeIntroduction()={
    roots.getChildren.remove(introRoot)
  }

  //play game page
  def playGame(): Unit = {
    val resource = getClass.getResourceAsStream("view/PlayGame.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots2 = loader.getRoot[jfxs.layout.AnchorPane]
    stage.scene().setRoot(roots2)
  }

  //show high score page
  def showHighScore():Unit ={
    val resource = getClass.getResourceAsStream("view/HighScore.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots2 = loader.getRoot[jfxs.layout.AnchorPane]
    stage.scene().setRoot(roots2)
  }

  def backToMain():Unit ={
    val resource = getClass.getResourceAsStream("view/MainMenu.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots2 = loader.getRoot[jfxs.layout.BorderPane]
    stage.scene().setRoot(roots2)
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