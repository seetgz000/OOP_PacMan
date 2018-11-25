package OOP_PacMan.controller

import java.io.{File, FileInputStream}

import OOP_PacMan.Main
import OOP_PacMan.ghost.Ghost
import OOP_PacMan.ghost.Ghost.purpleGhost
import javafx.collections.ObservableList
import scalafx.Includes._
import scalafx.beans.property.DoubleProperty
import scalafx.scene
import scalafx.scene.{Node, Scene, SubScene}
import scalafx.scene.canvas.Canvas
import scalafx.scene.control.Label
import scalafx.scene.effect.DropShadow
import scalafx.scene.image.{Image, ImageView}
import javafx.scene.input.KeyCode
import scalafx.event.ActionEvent
import scalafx.scene.layout._
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, Text}
import scalafxml.core.macros.sfxml


@sfxml
class PlayGameController(
                          private var flow: FlowPane,
                          private var anchorPane: AnchorPane,
                          private var pane: Pane,
                          private var hbox: HBox,
                          private val escPause : ImageView
                        )  {

  var map1 = Array(
    //                      1 1 1 1 1 1 1 1
    //    1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7
    Array(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1), // 1
    Array(1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1), // 2
    Array(1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1), // 3
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 4
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 5
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 6
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 7
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 8
    Array(1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1), // 9
    Array(1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1), // 10
    Array(1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1), // 11
    Array(1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1), // 12
    Array(1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1), // 13
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 14
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 15
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 16
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 17
    Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1), // 18
    Array(1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1), // 19
    Array(1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1), // 20
    Array(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)) // 21


  var length = 0 // array length
  /** search for longest length */
  for (row <- 0 until map1.length) { //loop map's row
    for (col <- 0 until map1(row).length) { //loop map's column
      if (length <= map1(row).length) {
        length = map1(row).length }}}

  val imageW = 23// set image Width
  val imageH = imageW // set image Height
  var nextline = 0
  var x = 0
  var y = 0

  var wallfile = new File("src/main/resource/OOP_PacMan/image/wall.png").toURI.toURL.toString
  var groundfile = new File("src/main/resource/OOP_PacMan/image/ground.png").toURI.toURL.toString
  var coinfile = new File("src/main/resource/OOP_PacMan/image/coin.png").toURI.toURL.toString

  var ground = new Image(groundfile,imageW,imageH,true,false)
  var wall = new Image(wallfile,imageW,imageH,true,false)
  var coin = new Image(coinfile,imageW,imageH,true,false)

  flow.vgap = 1
  flow.hgap = 1

  flow.setMinWidth((imageW+1)*length)

//  var groundList :Node
//  var wallList :Node
//  var coinList :Node

  val flowWidth = flow.getMaxWidth
  println(length)
  for (row <- 0 until map1.length) { //loop map's row
    for (col <- 0 until map1(row).length) { //loop map's column
      val num = map1(row)(col)
      // 0 = print ground
      if (num == 0){
        flow.getChildren().add(new ImageView(ground))
//        groundList = flow.children().applyCss()
          x += (imageW) + 1
        nextline += 1
      }
      // 1 = print wall
      if (num == 1){
        flow.getChildren().add(new ImageView(wall))
//        wallList = flow.children()
        x += (imageW) + 1
        nextline += 1
      }
      // 2 = print coin
      if (num == 2){
        flow.getChildren().add(new ImageView(coin))
//        coinList = flow.children()
        x += (imageW) + 1
        nextline += 1
      }

      //go down line
      if (nextline == map1(row).length){
        y += imageH+1
        x = 0
        nextline = 0 // reset
      }
    }
  }


  // pause game
  val escPauseImg = new Image(new FileInputStream("src/main/resource/OOP_PacMan/image/esc.png"))
  escPause.setImage(escPauseImg)

  def quitGame(action:ActionEvent)={
    Main.backToMain()
  }


}
