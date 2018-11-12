package User
import Database.Mydatabase
import scalafx.beans.property.{StringProperty, ObjectProperty}
import scala.util.{ Try, Success, Failure }

class User(_name: String,_score: Int){
  var name = new StringProperty(_name)
  var score = ObjectProperty[Int](_score)

}










