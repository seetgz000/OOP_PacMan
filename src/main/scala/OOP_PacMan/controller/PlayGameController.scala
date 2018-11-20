package OOP_PacMan.controller

import java.awt.Graphics

import javafx.scene.control.Label
import javax.swing.JFrame
import javafx.scene.layout.GridPane
import scalafx.scene.shape.Rectangle
import javafx.scene.paint.Color
import scalafxml.core.macros.sfxml

@sfxml
class PlayGameController extends JFrame {

  var row = 0
  var col = 0
  var grid = new GridPane()
  val rec = new Rectangle()
  var label = new Label()

  val maze = Array(Array(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,  1),
    Array(1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0,  1),
    Array(1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1,  1),
    Array(1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0,  1),
    Array(1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1,  1),
    Array(1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0,  1),
    Array(1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1,  1),
    Array(1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1,  1),
    Array(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,  1),
    Array(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,  1))

  override def paint(g : Graphics): Unit = {
    super.paint(g)
    g.translate(50, 50)
    var row = 0
    while ( {row < maze.length}) {
      var col = 0
      while ( {col < maze(row).length}) {
        maze(row)(col) match {
          case 1 =>
            rec.setFill(Color.BLUE)
            GridPane.setConstraints(rec,row,col)
          case 0 =>
            rec.setFill(Color.BLACK)
            GridPane.setConstraints(rec,row,col)
        }

        grid.getChildren().addAll(rec)

          col += 1;
          col - 1

      }

      {
        row += 1;
        row - 1
      }
    }
  }

}
