package OOP_PacMan.controller

import OOP_PacMan.Main
import Database.Database
import User.Players
import scalafx.scene.control.{Alert, TableCell, TableColumn, TableView}
import scalafxml.core.macros.sfxml
import scalafx.stage.{Modality, Stage}
import scalafx.event.ActionEvent
import scalafx.Includes._
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.TableView.TableViewSelectionModel
import scalikejdbc.DB



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

  def handleNewPlayer(action: ActionEvent) = {
    val player = new Players("Please Enter Name", 0)
    val okClicked = Main.showAddNew(player);
    if (okClicked) {
      Main.Userlist += player
      player.save()
    }
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