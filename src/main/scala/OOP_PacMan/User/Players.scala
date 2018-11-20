package User
import Database.Database
import scalafx.beans.property.{ObjectProperty, StringProperty}
import scalikejdbc._
import scala.util.{Failure, Success, Try}

class Players(nameS: String,scoreS: Int){

  var name = new StringProperty(nameS)
  var score = ObjectProperty[Int](scoreS)


  def isExist : Boolean =  {
    DB readOnly { implicit session =>
      sql"""
				select * from player where
				name = ${name.value} and score = ${score.value}
			""".map(rs => rs.string("name")).single.apply()
    } match {
      case Some(x) => true
      case None => false
    }

  }

  def delete() : Try[Int] = {
    if (isExist) {
      Try(DB autoCommit { implicit session =>
        sql"""
				delete from player where
					name = ${name.value} and score = ${score.value}
				""".update.apply()
      })
    } else
      throw new Exception("Player not Exists in Database")
  }

  def save() : Try[Int] = {
    if (!(isExist)) {
      Try(DB autoCommit { implicit session =>
        sql"""
					insert into player (name,score) values
						(${name.value}, ${score.value})
				""".update.apply()
      })
    } else {
      Try(DB autoCommit { implicit session =>
        sql"""
				update player
				set
				name = ${name.value} ,
				score   = ${score.value},

				 where name = ${name.value}
				""".update.apply()
      })
    }

  }
}

object Players extends Database {
  def apply(
             name: String,
             score: Int
           ): Players = {

    new Players(name, score) {
    }

  }

  def initializeTable() = {
    DB autoCommit { implicit session =>
      sql"""
			create table player (
			  id int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
			  name varchar(200),
			  score int
			)
			""".execute.apply()
    }
  }

  def dropTable() = {
      DB autoCommit { implicit session =>
        sql"""
          drop table players """.execute().apply
      }
    }


  def getAllUsers : List[Players] = {
    DB readOnly { implicit session =>
      sql"select * from player".map(rs => Players(rs.string("name"),
        rs.int("score") )).list.apply()
    }
  }
}











