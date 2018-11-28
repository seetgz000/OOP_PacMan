package OOP_PacMan

import java.io.File

import OOP_PacMan.component.{Coin, Wall, Ground}
import javafx.scene.Node
import scalafx.collections.ObservableBuffer
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.FlowPane

object PacmanMap {

  val imageW = 23// set image Width
  val imageH = imageW // set image Height
  var nextline = 0
  var x = 0
  var y = 0

//  var wallfile = new File ("src/main/resource/OOP_PacMan/image/wall.png").toURI.toURL.toString
//  var groundfile = new File("src/main/resource/OOP_PacMan/image/ground.png").toURI.toURL.toString
//  var coinfile = new File("src/main/resource/OOP_PacMan/image/coin.png").toURI.toURL.toString

//  var ground = new Image(groundfile,imageW,imageH,true,false)
//  var wall = new Image(wallfile,imageW,imageH,true,false)
//  var coin = new Image(coinfile,imageW,imageH,true,false)

  val wallList = new ObservableBuffer[Node]()
  var coinList = new ObservableBuffer[Node]()
  var groundList = new ObservableBuffer[Node]()

  def showMap(flow: FlowPane, mapLayout: Array[Array[Int]]): Unit = {



    var length = 0 // array length
    var lengthcount = 0
    /** search for longest length */
    for (row <- 0 until mapLayout.length) { //loop map's row
      for (col <- 0 until mapLayout(row).length) { //loop map's column
        if (length <= mapLayout(row).length) {
          length = mapLayout(row).length }}}

    flow.setMinWidth((imageW+1)*length)

    var lastWallIndex: Int = 0
    var lastCoinIndex: Int = 0
    var lastGroundIndex: Int = 0

    for (row <- 0 until mapLayout.length) { //loop map's row
      for (col <- 0 until mapLayout(row).length) { //loop map's column
        val num = mapLayout(row)(col)
        /** 0 = print ground */
        if (num == 0){
          flow.getChildren().add(new Ground)
          lengthcount += 1

          lastGroundIndex = flow.getChildren().size() - 1
          groundList += flow.getChildren.get(lastGroundIndex)
          x += (imageW) + 1
          nextline += 1
        }
        /** 1 = print wall */
        if (num == 1){
          flow.getChildren().add(new Wall)
          lengthcount += 1

          lastWallIndex = flow.getChildren().size() - 1
          wallList += flow.getChildren.get(lastWallIndex)
          x += (imageW) + 1
          nextline += 1
        }
        /** 2 = print coin */
        if (num == 2){
          flow.getChildren().add(new Coin)
          lengthcount += 1

          lastCoinIndex = flow.getChildren().size() - 1
          coinList += flow.getChildren.get(lastCoinIndex)
          x += (imageW) + 1
          nextline += 1
        }

        /** go down line */
        if (nextline == mapLayout(row).length){
          y += imageH+1
          x = 0
          nextline = 0 // reset
        }
      }
      /** fill up spaces so that flowpane will not messed up */
      if (lengthcount < length ) {
        for (extra <- 0 until (length - lengthcount)) {
          flow.getChildren().add(new Ground)
        }
      }
      lengthcount = 0
    }
  }

}

