package OOP_PacMan

import java.io.File

import OOP_PacMan.component.{Coin, Ghost, Pacman}
import scalafx.scene.Node
import scalafx.Includes._
import scalafx.animation.{AnimationTimer, PauseTransition}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.KeyCode
import scalafx.scene.shape.Rectangle
import scalafx.util.Duration

trait Movement {

  var moveableUp = Array(0)
  var moveableDown = Array(0)
  var moveableLeft = Array(0)
  var moveableRight = Array(0)

  /** ghost movement */
  def movement(node: ImageView, directionNo: Int, speed: Int): Unit = {
    var nodeTester = new Ghost(node.x.value, node.y.value)

    var thisWall: Node = PacmanMap.wallList.head

    directionNo match {

      //up
      case 1
      =>
        nodeTester.translateY() = node.getTranslateY - speed
        nodeTester.translateX() = node.getTranslateX
        for (row <- 1 until PacmanMap.wallList.size) {
          thisWall = PacmanMap.wallList.take(row).last
          if (!nodeTester.localToScene(nodeTester.getBoundsInLocal()).intersects(
            thisWall.localToScene(thisWall.getBoundsInLocal()))) {
            moveableUp = moveableUp ++ Array(0)
          } else {
            moveableUp = moveableUp ++ Array(1)
          }
          thisWall = PacmanMap.wallList.take(row).last
        }
        if (!moveableUp.contains(1)) {
          node.translateY() = node.getTranslateY - speed
          moveableUp = Array(0)
        } else {
          moveableUp = Array(0)
        }

      //left
      case 2
      =>
        nodeTester.translateX() = node.getTranslateX - speed
        nodeTester.translateY() = node.getTranslateY
        for (row <- 1 until PacmanMap.wallList.size) {
          thisWall = PacmanMap.wallList.take(row).last

          if (!nodeTester.localToScene(nodeTester.getBoundsInLocal()).intersects(
            thisWall.localToScene(thisWall.getBoundsInLocal()))) {
            moveableLeft = moveableLeft ++ Array(0)
          } else {
            moveableLeft = moveableLeft ++ Array(1)
          }
          thisWall = PacmanMap.wallList.take(row).last
        }
        if (!moveableLeft.contains(1)) {
          node.translateX() = node.getTranslateX - speed
          moveableLeft = Array(0)
        } else {
          moveableLeft = Array(0)
        }
      //down
      case 3
      =>
        nodeTester.translateY() = node.getTranslateY + speed
        nodeTester.translateX() = node.getTranslateX
        for (row <- 1 until PacmanMap.wallList.size) {
          thisWall = PacmanMap.wallList.take(row).last
          //println("Blocks " +thisWall.layoutX.value + " "+ thisWall.layoutY.value)

          if (!nodeTester.localToScene(node.getBoundsInLocal()).intersects(
            thisWall.localToScene(thisWall.getBoundsInLocal()))) {
            moveableDown = moveableDown ++ Array(0)
          } else {
            moveableDown = moveableDown ++ Array(1)
          }
          thisWall = PacmanMap.wallList.take(row).last
        }
        if (!moveableDown.contains(1)) {
            node.translateY() = node.getTranslateY + speed
            moveableDown = Array(0)
        } else {
          moveableDown = Array(0)
        }

        //println(node.getTranslateX + " "+ node.getTranslateY)

      //pritnln(node.getTransl)

      //right
      case 4
      =>
        nodeTester.translateX() = node.getTranslateX + speed
        nodeTester.translateY() = node.getTranslateY
        for (row <- 1 until PacmanMap.wallList.size) {
          thisWall = PacmanMap.wallList.take(row).last

          if (!nodeTester.localToScene(nodeTester.getBoundsInLocal()).intersects(
            thisWall.localToScene(thisWall.getBoundsInLocal()))) {
            moveableRight = moveableRight ++ Array(0)
          } else {
            moveableRight = moveableRight ++ Array(1)
          }
          thisWall = PacmanMap.wallList.take(row).last
        }
        if (!moveableRight.contains(1)) {
          node.translateX() = node.getTranslateX + speed
          moveableRight = Array(0)
        } else {
          moveableRight = Array(0)
        }

      case _ =>
    }
  } //end movement

  /** pacman movement */
  def movement(node: Node, keycode: KeyCode): Unit = {
    var nodeTester = new Pacman
    var thisWall: Node = PacmanMap.wallList.head

    keycode match {

      case KeyCode.W
      =>
        nodeTester.translateY() = node.getTranslateY - 6
        nodeTester.translateX() = node.getTranslateX
        for (row <- 1 until PacmanMap.wallList.size) {
          thisWall = PacmanMap.wallList.take(row).last

          if (!nodeTester.localToScene(nodeTester.getBoundsInLocal()).intersects(
            thisWall.localToScene(thisWall.getBoundsInLocal()))) {
            moveableUp = moveableUp ++ Array(0)
          } else {
            moveableUp = moveableUp ++ Array(1)
          }
          thisWall = PacmanMap.wallList.take(row).last
        }
        if (!moveableUp.contains(1)) {
          node.translateY() = node.translateY.value - 6
          Coin.checkCoinCollision(node)
          moveableUp = Array(0)
        } else {
          moveableUp = Array(0)
        }

      case KeyCode.A
      =>
        nodeTester.translateX() = node.getTranslateX - 6
        nodeTester.translateY() = node.getTranslateY
        for (row <- 1 until PacmanMap.wallList.size) {
          thisWall = PacmanMap.wallList.take(row).last

          if (!nodeTester.localToScene(nodeTester.getBoundsInLocal()).intersects(
            thisWall.localToScene(thisWall.getBoundsInLocal()))) {
            moveableLeft = moveableLeft ++ Array(0)
          } else {
            moveableLeft = moveableLeft ++ Array(1)
          }
          thisWall = PacmanMap.wallList.take(row).last
        }
        if (!moveableLeft.contains(1)) {
          node.translateX() = node.getTranslateX - 6
          Coin.checkCoinCollision(node)
          moveableLeft = Array(0)
        } else {
          moveableLeft = Array(0)
        }

      case KeyCode.S
      =>
        nodeTester.translateY() = node.getTranslateY + 6
        nodeTester.translateX() = node.getTranslateX
        for (row <- 1 until PacmanMap.wallList.size) {
          thisWall = PacmanMap.wallList.take(row).last

          if (!nodeTester.localToScene(node.getBoundsInLocal()).intersects(
            thisWall.localToScene(thisWall.getBoundsInLocal()))) {
            moveableDown = moveableDown ++ Array(0)
          } else {
            moveableDown = moveableDown ++ Array(1)
          }
          thisWall = PacmanMap.wallList.take(row).last
        }
        if (!moveableDown.contains(1)) {
          node.translateY() = node.getTranslateY + 6
          Coin.checkCoinCollision(node)
          moveableDown = Array(0)
        } else {
          moveableDown = Array(0)
        }

      case KeyCode.D
      =>
        nodeTester.translateX() = node.getTranslateX + 6
        nodeTester.translateY() = node.getTranslateY
        for (row <- 1 until PacmanMap.wallList.size) {
          thisWall = PacmanMap.wallList.take(row).last

          if (!nodeTester.localToScene(node.getBoundsInLocal()).intersects(
            thisWall.localToScene(thisWall.getBoundsInLocal()))) {
            moveableRight = moveableRight ++ Array(0)
          } else {
            moveableRight = moveableRight ++ Array(1)
          }
          thisWall = PacmanMap.wallList.take(row).last
        }
        if (!moveableRight.contains(1)) {
          node.translateX() = node.getTranslateX + 6
          Coin.checkCoinCollision(node)
          moveableRight = Array(0)
        } else {
          moveableRight = Array(0)
        }

      case _ =>
    }
  } //end movement

  //when pacman loses
//  def die(animation:AnimationTimer,n){
//    val timer = new PauseTransition(Duration(2000))
//    animation.stop()
//    //death animation
//    Main.pacman.setImage(new Image(new File("src/main/resource/OOP_PacMan/image/pacmanDeath.gif").toURI.toURL.toString))
//    timer.onFinished = e => {
//      Main.backToMain()
//      Main.pacman.setImage(new Image(new File("src/main/resource/OOP_PacMan/image/pacmanGIF(fast).gif").toURI.toURL.toString))
//    } //end die
//    timer.play()
//  }

}

//  //teaching ghost to avoid obstacles
//  def collisionDetection(node:ImageView): Unit = {
//
//    var thisWall: Node = PacmanMap.wallList.head
//
//    var boundRectUp = new Rectangle {
//      width = 3
//      height = 20
//      x = node.x.value
//      y = node.y.value
//      translateX = node.getTranslateX + 7
//      translateY = node.getTranslateY -10
//    }
//
//    var boundRectDown = new Rectangle {
//      width = 3
//      height = 20
//      x = node.x.value
//      y = node.y.value
//      translateX = node.getTranslateX + 7
//      translateY = node.getTranslateY +8
//    }
//
//    var boundRectLeft = new Rectangle {
//      width = 30
//      height = 3
//      x = node.x.value
//      y = node.y.value
//      translateX = node.getTranslateX -20
//      translateY = node.getTranslateY +8
//    }
//
//    var boundRectRight = new Rectangle {
//      width = 30
//      height = 3
//      x = node.x.value
//      y = node.x.value
//      translateX = node.getTranslateX +7
//      translateY = node.getTranslateY +8
//    }
//
//    for (row <- 1 until PacmanMap.wallList.size) {
//      thisWall = PacmanMap.wallList.take(row).last
//
//      if (!boundRectDown.getBoundsInParent().intersects(
//        thisWall.localToScene(thisWall.getBoundsInLocal()))) {
//        movement(node, 3, 2)
//      }
//      else if (!boundRectRight.localToScene(boundRectRight.getBoundsInParent()).intersects(
//        thisWall.localToScene(thisWall.getBoundsInLocal()))) {
//        movement(node, 4, 2)
//      }
//      else if (!boundRectLeft.localToScene(boundRectLeft.getBoundsInParent()).intersects(
//        thisWall.localToScene(thisWall.getBoundsInLocal()))) {
//        movement(node, 2, 2)
//
//      }
//      else if (!boundRectUp.localToScene(boundRectUp.getBoundsInParent()).intersects(
//        thisWall.localToScene(thisWall.getBoundsInLocal()))) {
//        movement(node, 1, 2)
//      }







