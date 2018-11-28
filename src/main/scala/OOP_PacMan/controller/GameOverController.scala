package OOP_PacMan.controller

import scalafx.scene.control.{Alert, Label, TableColumn, TextField}
import scalafxml.core.macros.sfxml
import scalafx.stage.Stage
import User.Players
import scalafx.Includes._
import scalafx.event.ActionEvent
import OOP_PacMan.Main
import OOP_PacMan.component.Coin
import OOP_PacMan.component.Coin.score
import scalafx.beans.binding.Bindings
import scalafx.scene.text.Text

@sfxml
class GameOverController(

  private val  name : TextField,
  private val scorel : Text

                           ) {

  var dialogStage: Stage = new Stage
  private var _players: Players = null
  var okClicked: Boolean = false

  def players = _players

  def players_=(x: Players) {
    _players = x
    name.text = _players.name.value
  }

  scorel.textProperty().bind(Bindings.createStringBinding(() =>(" " + score.get()),score))


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
