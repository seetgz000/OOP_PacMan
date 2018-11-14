package OOP_PacMan.controller

import OOP_PacMan.Main
import Database.UserDatabase
import User.User

import scalafx.scene.control.{Alert,TableColumn,TableView,TableCell}

import scalafxml.core.macros.sfxml
import scalafx.stage.{Modality, Stage}
import scalafx.event.ActionEvent
import scalafx.Includes._
import scalafx.scene.control.Alert.AlertType //contail all the implicits to change javafx classes to scalafx classes as necessary


@sfxml
class UserController (
                              val itemtableview: TableView[User],
                              val nameColumn: TableColumn[User, String],
                              val scoreColumn: TableColumn[User, Int]
                            ) {

  //get data from sql and show to table
  ShowUser()

  def ShowUser() = {
    //update the itemlist from database
    UserDatabase.GetUserData()

    //show to tableview
    itemtableview.items
      = UserDatabase.Userlist

    // initialize columns's cell values
    nameColumn.cellValueFactory = {
      _.value.name
    }
    scoreColumn.cellValueFactory = {
      _.value.score
    }
  }
}