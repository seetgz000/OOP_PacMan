package OOP_PacMan.controller

import java.io.File

import OOP_PacMan.{Main, PacmanMap}
import OOP_PacMan.component.Coin.score
import OOP_PacMan.ghost.GhostAnimation
import User.Players
import scalafx.beans.binding.Bindings
import scalafx.event.ActionEvent
import scalafx.scene.control.Label
import scalafx.scene.layout._
import scalafx.scene.media.{Media, MediaPlayer}
import scalafx.scene.text.Text
import scalafxml.core.macros.sfxml


@sfxml
class PlayGameController(
                          private var flow: FlowPane,
                          private var anchorPane: AnchorPane,
                          private var pane: Pane,
                          private var hbox: HBox,
                          private var pauseRoot: StackPane,
                          private var scoreText: Text,
                          private var highScoreLabel: Label
                        ) {

  if (Players.HighestScore != None) {
    highScoreLabel.text = Players.HighestScore.get.toString
  } else {
    highScoreLabel.text = 0.toString()
  }

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
//  playBg


  def quitGame(action:ActionEvent)={
//    stopBg
    GhostAnimation.animationTimer.stop()
    this.flow.children.removeAll()
    score.value = 0
    Main.backToMain()
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
