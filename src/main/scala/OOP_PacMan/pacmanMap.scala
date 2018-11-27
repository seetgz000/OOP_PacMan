package OOP_PacMan

import java.io.File

import OOP_PacMan.Component.{coin, wall}
import javafx.scene.Node
import scalafx.collections.ObservableBuffer
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.FlowPane

object pacmanMap {

  val imageW = 23// set image Width
  val imageH = imageW // set image Height
  var nextline = 0
  var x = 0
  var y = 0

//  var wallfile = new File("src/main/resource/OOP_PacMan/image/wall.png").toURI.toURL.toString
  var groundfile = new File("src/main/resource/OOP_PacMan/image/ground.png").toURI.toURL.toString
//  var coinfile = new File("src/main/resource/OOP_PacMan/image/coin.png").toURI.toURL.toString

  var ground = new Image(groundfile,imageW,imageH,true,false)
//  var wall = new Image(wallfile,imageW,imageH,true,false)
//  var coin = new Image(coinfile,imageW,imageH,true,false)

//  var wallPos: Iterable[Iterable[Double]] = None
  var wallList: Iterable[Node] = None

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


    //  println(length)
    for (row <- 0 until mapLayout.length) { //loop map's row
      for (col <- 0 until mapLayout(row).length) { //loop map's column
        val num = mapLayout(row)(col)
        /** 0 = print ground */
        if (num == 0){
          flow.getChildren().add(new ImageView(ground))
          lengthcount += 1

          lastWallIndex = flow.getChildren().size() - 1
//          wallPos = wallPos ++ Iterable(Iterable(0.0, 0.0))

          x += (imageW) + 1
          nextline += 1
        }
        // 1 = print wall
        if (num == 1){
          flow.getChildren().add(new wall)
          lengthcount += 1

          lastWallIndex = flow.getChildren().size() - 1
          wallList = wallList ++ Iterable(flow.getChildren.get(lastWallIndex))
//          wallPos = wallPos ++ Iterable(Iterable(flow.getChildrenUnmodifiable.get(lastWallIndex).getTranslateX, flow.getChildrenUnmodifiable.get(lastWallIndex).getTranslateY))
          x += (imageW) + 1
          nextline += 1
        }
        // 2 = print coin
        if (num == 2){
          flow.getChildren().add(new component)
          lengthcount += 1
          //        coinList = flow.children()
          x += (imageW) + 1
          nextline += 1
        }

        //go down line
        if (nextline == mapLayout(row).length){
          y += imageH+1
          x = 0
          nextline = 0 // reset
        }
      }
      // fill up spaces so that flowpane will not messed up
      if (lengthcount < length ) {
        for (extra <- 0 until (length - lengthcount)) {
          flow.getChildren().add(new ImageView(ground))
        }
      }
      lengthcount = 0
    }
//    println(wallList)
  }

}

class component extends coin {

}
