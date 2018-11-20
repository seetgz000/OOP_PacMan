package OOP_PacMan.Maze


import java.awt.{Color, Graphics}
import scalafx.scene.image.Image
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.scene.Scene

import javax.swing.JFrame


class Maze extends JFrame{

  val maze =
    Array(Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0),
      Array(1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0),
      Array(1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
      Array(1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1),
      Array(1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0),
      Array(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0),
      Array(1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0)
    )



  override def paint(g : Graphics): Unit ={
    super.paint(g)

    g.translate(50,50)



    var row = 0
    while ( {
      row < maze.length
    }) {
      var col = 0
      while ( {
        col < maze(0).length
      }) {
        var color  = Color.orange
        maze(row)(col) match {
          case 1 =>
            color = Color.red
          case 9 =>
            color = Color.black
          case _ =>
            color = Color.white
        }
        g.setColor(color)
        g.fillRect(30 * col, 30 * row, 30, 30)
        g.setColor(Color.BLACK)
        g.drawRect(30 * col, 30 * row, 30, 30)

        {
          col += 1; col - 1
        }
      }

      {
        row += 1; row - 1
      }
    }
  }



  }





