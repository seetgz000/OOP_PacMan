package OOP_PacMan.controller

import OOP_PacMan.Main
import User.Players
import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml

@sfxml
class MainController {

  def startGame(action: ActionEvent)={
    Main.playGame()
  }

  def showHighScore(actionEvent: ActionEvent) = {
    Main.showHighScore()
  }

  def startIntro(action:ActionEvent)={
    Main.showIntroduction()
  }

}
