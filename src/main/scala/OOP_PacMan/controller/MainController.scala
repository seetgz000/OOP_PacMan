package OOP_PacMan.controller

import OOP_PacMan.Main
import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml

@sfxml
class MainController {

  def startGame(action: ActionEvent)={
    Main.playGame()
  }

<<<<<<< HEAD
  def startIntroduction(action:ActionEvent)={
    Main.showIntroduction()
  }

  def showHighScore(actionEvent: ActionEvent) = {
    Main.showHighScore()
  }
=======
  def startIntro(action:ActionEvent)={
    Main.showIntroduction()
  }
>>>>>>> origin/scene_switching
}
