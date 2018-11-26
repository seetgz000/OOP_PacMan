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
    stage.setMaxWidth(423)
    stage.setMinWidth(423)

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
      var thisWall: Node = pacmanMap.wallList.head
      var moveable = Array(0)

//      print(thisWall.localToScene(thisWall.getBoundsInLocal()).getMaxX, thisWall.localToScene(thisWall.getBoundsInLocal()).getMaxY,
//        thisWall.localToScene(thisWall.getBoundsInLocal()).getMinX, thisWall.localToScene(thisWall.getBoundsInLocal()).getMinY)
//      println("Parent and Local", pacman.getBoundsInParent(), thisWall.localToScene(thisWall.getBoundsInLocal()))
//      println("Parent and Parent", pacman.getBoundsInParent(), thisWall.boundsInParent())
        stage.scene().onKeyPressed = k => k.code match {

        //            print(pacmanMap.wallList.take(row))

          case KeyCode.W
          =>
        for (row <- 1 until pacmanMap.wallList.size){
          thisWall = pacmanMap.wallList.take(row).last

          if (!pacman.localToParent(pacman.getBoundsInLocal()).intersects(thisWall.localToScene(thisWall.getBoundsInLocal()))){
            moveable = moveable ++ Array(0)
          }else {
            moveable = moveable ++ Array(1)
          }
          thisWall = pacmanMap.wallList.take(row).last
        }
        if (! moveable.contains(1)) {
          pacmanY() = pacmanY.value - 6
        }

        case KeyCode.A
        =>
          for (row <- 1 until pacmanMap.wallList.size){
            thisWall = pacmanMap.wallList.take(row).last

            if (!pacman.localToParent(pacman.getBoundsInLocal()).intersects(thisWall.localToScene(thisWall.getBoundsInLocal()))){
              moveable = moveable ++ Array(0)
            }else {
              moveable = moveable ++ Array(1)
            }
            thisWall = pacmanMap.wallList.take(row).last
          }
          if (! moveable.contains(1)) {
            pacmanX() = pacmanX.value - 6
          }
        case KeyCode.S
        =>
          for (row <- 1 until pacmanMap.wallList.size){
            thisWall = pacmanMap.wallList.take(row).last

            if (!pacman.localToParent(pacman.getBoundsInLocal()).intersects(thisWall.localToScene(thisWall.getBoundsInLocal()))){
              moveable = moveable ++ Array(0)
            }else {
              moveable = moveable ++ Array(1)
            }
            thisWall = pacmanMap.wallList.take(row).last
          }
          if (! moveable.contains(1)) {
            pacmanY() = pacmanY.value + 6
          }
        case KeyCode.D
        =>
          for (row <- 1 until pacmanMap.wallList.size){
            thisWall = pacmanMap.wallList.take(row).last

            if (!pacman.localToParent(pacman.getBoundsInLocal()).intersects(thisWall.localToScene(thisWall.getBoundsInLocal()))){
              moveable = moveable ++ Array(0)
            }else {
              moveable = moveable ++ Array(1)
            }
            thisWall = pacmanMap.wallList.take(row).last
          }
          if (! moveable.contains(1)) {
            pacmanX() = pacmanX.value + 6
          }
        case _ =>
      }

      children = List(
        pacman
      )
    }
    root.getChildren.add(group)
  }


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