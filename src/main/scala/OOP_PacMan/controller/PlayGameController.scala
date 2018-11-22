package OOP_PacMan.controller

import java.io.{File, FileInputStream}

import OOP_PacMan.Main
import scalafx.event.ActionEvent
import scalafx.scene
import scalafx.scene.{Scene, SubScene}
import scalafx.scene.canvas.Canvas
import scalafx.scene.control.Label
import scalafx.scene.effect.DropShadow
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{BorderPane, HBox, Pane}
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, Text}
import scalafxml.core.macros.sfxml


@sfxml
class PlayGameController(
                          private var canvas: Canvas,
                          private var pane: Pane,
                          private var hbox: HBox,
                          private var num: Label,
                          private val escPause : ImageView
                        ) {
  var ground = new Image(new File("src/main/resource/OOP_PacMan/image/ground.png").toURI.toURL.toString)
  var wall = new Image(new File("src/main/resource/OOP_PacMan/image/wall.png").toURI.toURL.toString)
  var coin = new Image(new File("src/main/resource/OOP_PacMan/image/coin.png").toURI.toURL.toString)
  var wallV = new ImageView(wall)
  var groundV = new ImageView(ground)

//canvas.graphicsContext2D.drawImage(wall,0,0,30,30)

          var map1 = Array(
            Array(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1),
            Array(1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1),
            Array(1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1),
            Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1),
            Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1),
            Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1),
            Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1),
            Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1),
            Array(1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1),
            Array(1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1),
            Array(1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1),
            Array(1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1),
            Array(1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1),
            Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1),
            Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1),
            Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1),
            Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1),
            Array(1,2,1,0,0,0,0,1,2,1,0,0,0,0,1,2,1),
            Array(1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1),
            Array(1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1),
            Array(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1))

          var x = 0
          var y = 0

  var canwidth = canvas.width.toDouble/17
  println(canwidth)

  canvas.setWidth(410)


          // set image size
          val imageW = 23// set image Width
          val imageH = imageW // set image Height
          var nextline = 0

          for (row <- 0 until map1.length) { //loop map's row
            for (col <- 0 until map1(row).length) { //loop map's column
              var num = map1(row)(col)
              println(num)
              //print ground = 0
              if (num == 0){
                canvas.graphicsContext2D.drawImage(ground, x, y, imageW, imageH)
                x += (imageW) + 1
                nextline += 1
              }
              //print wall = 1
              if (num == 1){
                canvas.graphicsContext2D.drawImage(wall, x, y, imageW, imageH)
                x += (imageW) + 1
                nextline += 1
               }
              //print coin
              if (num == 2){
                canvas.graphicsContext2D.drawImage(coin, x, y, imageW, imageH)
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
