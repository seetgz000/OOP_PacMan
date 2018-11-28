package OOP_PacMan.controller

import OOP_PacMan.Main
import User.Players
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.TableView.TableViewSelectionModel
import scalafx.scene.control.{Alert, TableColumn, TableView}
import scalafxml.core.macros.sfxml



@sfxml
class UserController (
                       val itemtableview: TableView[Players],
                       val nameColumn: TableColumn[Players, String],
                       val scoreColumn: TableColumn[Players, Int]
                            ) {


  var defaultSelectionModel: TableViewSelectionModel[Players] = itemtableview.getSelectionModel

  //get data from sql and show to table
  itemtableview.items = Main.Userlist
  nameColumn.cellValueFactory = {
    _.value.name
  }
  scoreColumn.cellValueFactory = {
    _.value.score
  }

  def backtoMain = {
    Main.backToMain()
  }

   def handleDeletePerson(action : ActionEvent) = {
    val selectedIndex = itemtableview.selectionModel().selectedIndex.value
    val selectedItem = itemtableview.selectionModel().selectedItem.value
    if (selectedIndex >= 0) {
      itemtableview.items().remove(selectedIndex);
      selectedItem.delete()
    } else {
      // Nothing selected.
      val alert = new Alert(AlertType.Warning){
        initOwner(Main.stage)
        title       = "No Selection"
        headerText  = "No Player Selected"
        contentText = "Please select a player in the scoreboard"
      }.showAndWait()
    }
  }
}