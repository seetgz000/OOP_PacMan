package Database
import User.User

import java.sql.{Connection,DriverManager}
import scalafx.collections.ObservableBuffer

object UserDatabase {

  var Userlist: ObservableBuffer[User] = new ObservableBuffer[User]()

  def UpdateUserlist() = {
    Userlist.clear()

    Class.forName(Mydatabase.driver)
    Mydatabase.connection = DriverManager.getConnection(Mydatabase.url, Mydatabase.username, Mydatabase.password)
    val statement = Mydatabase.connection.createStatement
    val result = statement.executeQuery("select * from user")

    while (result.next) {
      val name = result.getString("name")
      val score = result.getInt("score")

      var Players = new User(name, score)

      Userlist += Players
    }
    Mydatabase.connection.close()
  }

  def GetUserData() = {
    Userlist.clear()

    Class.forName(Mydatabase.driver)
    Mydatabase.connection = DriverManager.getConnection(Mydatabase.url, Mydatabase.username, Mydatabase.password)
    val statement = Mydatabase.connection.createStatement
    val result = statement.executeQuery("select * from user")

    while (result.next) {
      val name = result.getString("name")
      val score = result.getInt("score")

      var Players = new User(name, score)

      Userlist += Players
    }

    Mydatabase.connection.close()
  }

  def AddUser(name:String,score:Int) = {
    Class.forName(Mydatabase.driver)
    Mydatabase.connection = DriverManager.getConnection(Mydatabase.url, Mydatabase.username, Mydatabase.password)
    val statement = Mydatabase.connection.createStatement
    statement.executeUpdate(s"Insert into item user(${name},'${score}'})")

    Mydatabase.connection.close()
    UpdateUserlist()
  }
}
