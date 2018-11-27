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
import OOP_PacMan.Component.pacman
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
//    stage.setMaxWidth(600)
    stage.setMinWidth(423)

    showGameCanvas(roots2)
  }

  def collisionHandling(movement: String, pacman: Node, thisWall: Node): Unit = {

    while (pacman.localToScene(pacman.getBoundsInLocal()).intersects(thisWall.localToScene(thisWall.getBoundsInLocal()))) movement match{
      case "UP"
      =>
        pacman.setTranslateY(pacman.getTranslateY + 6)
    }
    println(pacman.localToScene(pacman.getBoundsInLocal()),thisWall.localToScene(thisWall.getBoundsInLocal()))
  }

  def showGameCanvas(root: AnchorPane): Unit = {

    val group = new Group() {


      var thisWall: Node = pacmanMap.wallList.head
      var moveableUp = Array(0)
      var moveableDown = Array(0)
      var moveableLeft = Array(0)
      var moveableRight = Array(0)
      var pacman = new pacman
      var pacmanTester = new pacman

        stage.scene().onKeyPressed = k => k.code match {

          case KeyCode.W
          =>

            pacmanTester.pacmanY() = pacman.pacmanY.value - 6
            pacmanTester.pacmanX() = pacman.pacmanX.value
        for (row <- 1 until pacmanMap.wallList.size){
          thisWall = pacmanMap.wallList.take(row).last

          if (!pacmanTester.localToScene(pacmanTester.getBoundsInLocal()).intersects(
            thisWall.localToScene(thisWall.getBoundsInLocal()))){
            moveableUp = moveableUp ++ Array(0)
          }else {
            moveableUp = moveableUp ++ Array(1)
          }
          thisWall = pacmanMap.wallList.take(row).last
        }
        if (!moveableUp.contains(1)) {
          pacman.pacmanY() = pacman.pacmanY.value - 6
//          pacmanTester.pacmanY() = pacman.pacmanY() - 6
          moveableUp = Array(0)
        } else {
          moveableUp = Array(0)
        }

        case KeyCode.A
        =>
          pacmanTester.pacmanX() = pacman.pacmanX.value - 6
          pacmanTester.pacmanY() = pacman.pacmanY.value
          for (row <- 1 until pacmanMap.wallList.size){
            thisWall = pacmanMap.wallList.take(row).last

            if (!pacmanTester.localToScene(pacmanTester.getBoundsInLocal()).intersects(
              thisWall.localToScene(thisWall.getBoundsInLocal()))){
              moveableLeft = moveableLeft ++ Array(0)
            }else {
              moveableLeft = moveableLeft ++ Array(1)
            }
            thisWall = pacmanMap.wallList.take(row).last
          }
          if (!moveableLeft.contains(1)) {
            pacman.pacmanX() = pacman.pacmanX.value - 6
//            pacmanTester.pacmanX() = pacman.pacmanX() - 6
            moveableLeft = Array(0)
          } else {
            moveableLeft = Array(0)
          }
        case KeyCode.S
        =>
          pacmanTester.pacmanY() = pacman.pacmanY.value + 6
          pacmanTester.pacmanX() = pacman.pacmanX.value
          for (row <- 1 until pacmanMap.wallList.size){
            thisWall = pacmanMap.wallList.take(row).last

            if (!pacmanTester.localToScene(pacman.getBoundsInLocal()).intersects(thisWall.localToScene(thisWall.getBoundsInLocal()))){
              moveableDown = moveableDown ++ Array(0)
            }else {
              moveableDown = moveableDown ++ Array(1)
            }
            thisWall = pacmanMap.wallList.take(row).last
          }
          if (!moveableDown.contains(1)) {
            pacman.pacmanY() = pacman.pacmanY.value + 6
            moveableDown = Array(0)
          } else {
            moveableDown = Array(0)
          }

        case KeyCode.D
        =>
          pacmanTester.pacmanX() = pacman.pacmanX.value + 6
          pacmanTester.pacmanY() = pacman.pacmanY.value
          for (row <- 1 until pacmanMap.wallList.size){
            thisWall = pacmanMap.wallList.take(row).last

            if (!pacmanTester.localToScene(pacman.getBoundsInLocal()).intersects(thisWall.localToScene(thisWall.getBoundsInLocal()))){
              moveableRight = moveableRight ++ Array(0)
            }else {
              moveableRight = moveableRight ++ Array(1)
            }
            thisWall = pacmanMap.wallList.take(row).last
          }
          if (!moveableRight.contains(1)) {
            pacman.pacmanX() = pacman.pacmanX.value + 6
            moveableRight = Array(0)
          } else {
            moveableRight = Array(0)
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