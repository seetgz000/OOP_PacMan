package OOP_PacMan.controller

import java.io.{File, FileInputStream}

import OOP_PacMan.component.{Coin, Wall}
import OOP_PacMan.Main
import OOP_PacMan.PacmanMap
import OOP_PacMan.ghost.GhostAnimation
import User.Players
import javafx.collections.ObservableList
import scalafx.Includes._
import scalafx.beans.property.{DoubleProperty, IntegerProperty, StringProperty}
import scalafx.scene
import scalafx.scene.{Node, Scene, SubScene}
import scalafx.scene.canvas.Canvas
import scalafx.scene.control.Label
import scalafx.scene.effect.DropShadow
import scalafx.scene.image.{Image, ImageView}
import javafx.scene.input.KeyCode
import scalafx.event.ActionEvent
import scalafx.scene.layout._
import scalafx.scene.media.{Media, MediaPlayer}
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, Text}
import scalafxml.core.macros.sfxml
import OOP_PacMan.component.Coin.score
import javafx.beans.property.SimpleIntegerProperty
import scalafx.beans.binding.Bindings


@sfxml
class PlayGameController(
                          private var flow: FlowPane,
                          private var anchorPane: AnchorPane,
                          private var pane: Pane,
                          private var hbox: HBox,
                          private var pauseRoot: StackPane
                        ) {

  val die =new Media (new File("src/main/resource/OOP_PacMan/audio/die.wav").toURI.toURL.toString)
  val AHH =new Media (new File("src/main/resource/OOP_PacMan/audio/AHH.wav").toURI.toURL.toString)
  val background =new Media (new File("src/main/resource/OOP_PacMan/audio/retrobackground.mp3").toURI.toURL.toString)


  var backgroundmusic =  new MediaPlayer(background)
  backgroundmusic.play

  var map1 = Array(
    //                      1 1 1 1 1 1 1 1
    //    1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7
    Array(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1), // 1
    Array(1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1), // 2
    Array(1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1), // 3
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 4
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 5
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 6
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 7
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 8
    Array(1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1), // 9
    Array(1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1), // 10
    Array(1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1), // 11
    Array(1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1), // 12
    Array(1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1), // 13
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 14
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 15
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 16
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 17
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 18
    Array(1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1), // 19
    Array(1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1), // 20
    Array(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)) // 21


  flow.vgap = 1
  flow.hgap = 1


  scoreText.textProperty().bind(Bindings.createStringBinding(() =>(" " + score.get()),score))

  /** To print out image according array */
  PacmanMap.showMap(flow, map1)



  def quitGame(action:ActionEvent)={
    backgroundmusic.stop
    Main.backToMain()
    GhostAnimation.animationTimer.stop()
  }

  //for pause pop up
  def openPause(action:ActionEvent)={
    GhostAnimation.animationTimer.stop()
    pauseRoot.toFront()
  }

  def closePause(action: ActionEvent)={
    GhostAnimation.animationTimer.start()
    pauseRoot.toBack()
  }

  pauseRoot.toBack()

}
