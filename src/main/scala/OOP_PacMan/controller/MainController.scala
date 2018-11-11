package OOP_PacMan.controller

import OOP_PacMan.Main
import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml

@sfxml
class MainController {

  def startGame(action: ActionEvent)={
    Main.playGame()
  }

  def startIntroduction(action:ActionEvent)={
    Main.showIntroduction()
  }
}
