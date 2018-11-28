package OOP_PacMan.controller

import scalafx.scene.control.{Alert, Label, TableColumn, TextField}
import scalafxml.core.macros.sfxml
import scalafx.stage.Stage
import User.Players
import scalafx.Includes._
import scalafx.event.ActionEvent
import OOP_PacMan.Main
import OOP_PacMan.component.Coin

@sfxml
class GameOverController(

  private val  name : TextField,
  private var score: Label

                           ) {



  score.text = Coin.score.get.toString()

  var dialogStage: Stage = new Stage
//  dialogStage.asInstanceOf[GameOverController]
  private var _players: Players = null
  var okClicked: Boolean = false

  def players = _players

  def players_=(x: Players) {
    _players = x
    name.text = _players.name.value
  }

  def handleOk(action: ActionEvent) {

    if (isInputValid()) {
      _players.name <== name.text;

      okClicked = true;
      dialogStage.close()
    }
  }

  def nullChecking(x: String) = x == null || x.length == 0

  def lengthChecking(x: String) = x.length >= 8

  def isInputValid(): Boolean = {
    var errorMessage = ""

    if (nullChecking(name.text.value))
      errorMessage += "No valid name!\n"

    if (lengthChecking(name.text.value))
      errorMessage += "Maximum 8 characters!\n"

    if (errorMessage.length() == 0) {
      return true;
    } else {
      // Show the error message.
      val alert = new Alert(Alert.AlertType.Error){
        initOwner(dialogStage)
        title = "Invalid Fields"
        headerText = "Username invalid"
        contentText = errorMessage
      }.showAndWait()

      return false;
    }

  }

}
