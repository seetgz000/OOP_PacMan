package Database
import java.sql.Connection


object Mydatabase {

  val url = "jdbc:mysql://localhost:3306/oop?useTimezone=true&serverTimezone=UTC"
  val driver = "com.mysql.cj.jdbc.Driver"
  val username ="root"
  val password =""
  var connection:Connection = _
}
