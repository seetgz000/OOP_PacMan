package OOP_PacMan.ghost

import java.io.File

import scalafx.scene.image.{Image, ImageView}

import scala.language.postfixOps
import scalafx.Includes._
import scalafx.animation.AnimationTimer
import scalafx.beans.property.DoubleProperty

import scala.util.Random

object Ghost extends GhostMovement {

  val start = 1
  val end = 4

  /** Translation of Ghosts */
  val purpleGhostX = new DoubleProperty
  val purpleGhostY = new DoubleProperty

  val blueGhostX = new DoubleProperty
  val blueGhostY = new DoubleProperty

  val coralGhostX = new DoubleProperty
  val coralGhostY = new DoubleProperty

  val redGhostX = new DoubleProperty
  val redGhostY = new DoubleProperty

  /** Initialize Ghotsts */
  val purpleGhostImg = new Image(new File("src/main/resource/OOP_PacMan/image/purpleghost-down.png").toURI.toURL.toString)
  val blueGhostImg = new Image(new File("src/main/resource/OOP_PacMan/image/blueghost-down.png").toURI.toURL.toString)
  val coralGhostImg = new Image(new File("src/main/resource/OOP_PacMan/image/coralghost-down.png").toURI.toURL.toString)
  val redGhostImg = new Image(new File("src/main/resource/OOP_PacMan/image/redghost-down.png").toURI.toURL.toString)

  val ghostW = 25
  val ghostH = 25

  val purpleGhost = new ImageView(purpleGhostImg){
    fitWidth = ghostW
    preserveRatio = true
    x = 24
    y = 87
    translateX <== purpleGhostX
    translateY <== purpleGhostY
  }

  val blueGhost = new ImageView(blueGhostImg){
    fitWidth = ghostW
    preserveRatio = true
    x = 24
    y = 520
    translateX <== blueGhostX
    translateY <== blueGhostY
  }

  val coralGhost = new ImageView(coralGhostImg){
    fitWidth = ghostW
    preserveRatio = true
    x = 360
    y = 520
    translateX <== coralGhostX
    translateY <== coralGhostY
  }

  val redGhost = new ImageView(redGhostImg){
    fitWidth = ghostW
    preserveRatio = true
    x = 360
    y = 87
    translateX <== redGhostX
    translateY <== redGhostY
  }


  //show current time
  val startTime = System.nanoTime()
  var duration:Double = 0

  var frameCount:Int = 0

  //variable to keep random number
  var randomNo:Int = 0
  var randomNo2:Int = 0

  // return to position everytime game starts
  def preparingGhost={
    purpleGhostX() = 0
    purpleGhostY() = 0

    blueGhostX() = 0
    blueGhostY() = 0

    coralGhostX() = 0
    coralGhostY() = 0

    redGhostX() = 0
    redGhostY() = 0
    animationTimer.start()
  }

  //animate ghost movement
  val animationTimer=AnimationTimer(e=>{
    duration = (e-startTime)/1e9

    movement(timedRandomGenerator2(),blueGhostX,blueGhostY)
    
    movement(timedRandomGenerator(),purpleGhostX,purpleGhostY)

  })

  //      start+random.nextInt((end-start)+1)

  //used to generate random numbers at timed manner(about few secs in between)
  //or ghost will go on a spasm
  def timedRandomGenerator()={
    frameCount += 1
    if (frameCount >= 40) {
      val random = new scala.util.Random()
      randomNo = start+random.nextInt((end-start)+1)
      frameCount = 0
    }
    randomNo
  }

  def timedRandomGenerator2()={
    frameCount += 1
    if (frameCount >= 40) {
      val random = new scala.util.Random()
      randomNo2 = start+random.nextInt((end-start)+1)
      frameCount = 0
    }
    randomNo2
  }

}

