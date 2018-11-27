package OOP_PacMan.ghost

import OOP_PacMan.Component.pacman
import OOP_PacMan.pacmanMap

import scalafx.Includes._
import scalafx.scene.Node
import scalafx.scene.input.KeyCode

//reminder to self: https://stackoverflow.com/questions/15013913/checking-collision-of-shapes-with-javafx refer this for collision

trait Movement {

  var thisWall: Node = pacmanMap.wallList.head
  var moveableUp = Array(0)
  var moveableDown = Array(0)
  var moveableLeft = Array(0)
  var moveableRight = Array(0)

  def movement(node: Node, directionNo: Int, speed: Int): Unit = {
    var nodeTester = new pacman
    directionNo match {

      //up
      case 1
      =>
        nodeTester.translateY() = node.getTranslateY - speed
        nodeTester.translateX() = node.getTranslateX
        for (row <- 1 until pacmanMap.wallList.size) {
          thisWall = pacmanMap.wallList.take(row).last

          if (!nodeTester.localToScene(nodeTester.getBoundsInLocal()).intersects(
            thisWall.localToScene(thisWall.getBoundsInLocal()))) {
            moveableUp = moveableUp ++ Array(0)
          } else {
            moveableUp = moveableUp ++ Array(1)
          }
          thisWall = pacmanMap.wallList.take(row).last
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
        for (row <- 1 until pacmanMap.wallList.size) {
          thisWall = pacmanMap.wallList.take(row).last

          if (!nodeTester.localToScene(nodeTester.getBoundsInLocal()).intersects(
            thisWall.localToScene(thisWall.getBoundsInLocal()))) {
            moveableLeft = moveableLeft ++ Array(0)
          } else {
            moveableLeft = moveableLeft ++ Array(1)
          }
          thisWall = pacmanMap.wallList.take(row).last
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
        for (row <- 1 until pacmanMap.wallList.size) {
          thisWall = pacmanMap.wallList.take(row).last

          if (!nodeTester.localToScene(node.getBoundsInLocal()).intersects(
            thisWall.localToScene(thisWall.getBoundsInLocal()))) {
            moveableDown = moveableDown ++ Array(0)
          } else {
            moveableDown = moveableDown ++ Array(1)
          }
          thisWall = pacmanMap.wallList.take(row).last
        }
        if (!moveableDown.contains(1)) {
          node.translateY() = node.getTranslateY + speed
          moveableDown = Array(0)
        } else {
          moveableDown = Array(0)
        }

      //right
      case 4
      =>
        nodeTester.translateX() = node.getTranslateX + speed
        nodeTester.translateY() = node.getTranslateY
        for (row <- 1 until pacmanMap.wallList.size) {
          thisWall = pacmanMap.wallList.take(row).last

          if (!nodeTester.localToScene(node.getBoundsInLocal()).intersects(
            thisWall.localToScene(thisWall.getBoundsInLocal()))) {
            moveableRight = moveableRight ++ Array(0)
          } else {
            moveableRight = moveableRight ++ Array(1)
          }
          thisWall = pacmanMap.wallList.take(row).last
        }
        if (!moveableRight.contains(1)) {
          node.translateX() = node.getTranslateX + speed
          moveableRight = Array(0)
        } else {
          moveableRight = Array(0)
        }

      case _ =>
    }
  }

  def movement(node: Node, keycode: KeyCode): Unit = {
    var nodeTester = new pacman

    keycode match {

      case KeyCode.W
      =>
        nodeTester.translateY() = node.getTranslateY - 6
        nodeTester.translateX() = node.getTranslateX
        for (row <- 1 until pacmanMap.wallList.size) {
          thisWall = pacmanMap.wallList.take(row).last

          if (!nodeTester.localToScene(nodeTester.getBoundsInLocal()).intersects(
            thisWall.localToScene(thisWall.getBoundsInLocal()))) {
            moveableUp = moveableUp ++ Array(0)
          } else {
            moveableUp = moveableUp ++ Array(1)
          }
          thisWall = pacmanMap.wallList.take(row).last
        }
        if (!moveableUp.contains(1)) {
          node.translateY() = node.translateY.value - 6
          moveableUp = Array(0)
        } else {
          moveableUp = Array(0)
        }

      case KeyCode.A
      =>
        nodeTester.translateX() = node.getTranslateX - 6
        nodeTester.translateY() = node.getTranslateY
        for (row <- 1 until pacmanMap.wallList.size) {
          thisWall = pacmanMap.wallList.take(row).last

          if (!nodeTester.localToScene(nodeTester.getBoundsInLocal()).intersects(
            thisWall.localToScene(thisWall.getBoundsInLocal()))) {
            moveableLeft = moveableLeft ++ Array(0)
          } else {
            moveableLeft = moveableLeft ++ Array(1)
          }
          thisWall = pacmanMap.wallList.take(row).last
        }
        if (!moveableLeft.contains(1)) {
          node.translateX() = node.getTranslateX - 6
          moveableLeft = Array(0)
        } else {
          moveableLeft = Array(0)
        }

      case KeyCode.S
      =>
        nodeTester.translateY() = node.getTranslateY + 6
        nodeTester.translateX() = node.getTranslateX
        for (row <- 1 until pacmanMap.wallList.size) {
          thisWall = pacmanMap.wallList.take(row).last

          if (!nodeTester.localToScene(node.getBoundsInLocal()).intersects(
            thisWall.localToScene(thisWall.getBoundsInLocal()))) {
            moveableDown = moveableDown ++ Array(0)
          } else {
            moveableDown = moveableDown ++ Array(1)
          }
          thisWall = pacmanMap.wallList.take(row).last
        }
        if (!moveableDown.contains(1)) {
          node.translateY() = node.getTranslateY + 6
          moveableDown = Array(0)
        } else {
          moveableDown = Array(0)
        }

      case KeyCode.D
      =>
        nodeTester.translateX() = node.getTranslateX + 6
        nodeTester.translateY() = node.getTranslateY
        for (row <- 1 until pacmanMap.wallList.size) {
          thisWall = pacmanMap.wallList.take(row).last

          if (!nodeTester.localToScene(node.getBoundsInLocal()).intersects(
            thisWall.localToScene(thisWall.getBoundsInLocal()))) {
            moveableRight = moveableRight ++ Array(0)
          } else {
            moveableRight = moveableRight ++ Array(1)
          }
          thisWall = pacmanMap.wallList.take(row).last
        }
        if (!moveableRight.contains(1)) {
          node.translateX() = node.getTranslateX + 6
          moveableRight = Array(0)
        } else {
          moveableRight = Array(0)
        }

      case _ =>
    }
  }
}
