package OOP_PacMan.controller

import scalafx.scene.control.{TextField, TableColumn, Label, Alert}
import scalafxml.core.macros.sfxml
import scalafx.stage.Stage
import User.Players
import scalafx.Includes._
import scalafx.event.ActionEvent
import OOP_PacMan.Main

@sfxml
class GameOverController(

  private val  name : TextField

                           ) {

  var dialogStage: Stage = null
  private var _players: Players = null
  var okClicked = false

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
