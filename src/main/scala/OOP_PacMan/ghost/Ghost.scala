package OOP_PacMan.ghost

import java.io.File

import scalafx.scene.image.{Image, ImageView}

import scala.language.postfixOps
import scalafx.Includes._
import scalafx.animation.AnimationTimer
import scalafx.beans.property.DoubleProperty

import scala.util.Random

object Ghost extends GhostMovement {

  /** Translation of Ghosts */
  val purpleGhostX = new DoubleProperty
  val purpleGhostY = new DoubleProperty

  val blueGhostX = new DoubleProperty
  val blueGhostY = new DoubleProperty

  val coralGhostX = new DoubleProperty
  val coralGhostY = new DoubleProperty

  val redGhostX = new DoubleProperty
  val redGhostY = new DoubleProperty

  /** Initialize Ghosts */
  val purpleGhostImg = new Image(new File("src/main/resource/OOP_PacMan/image/purpleghost-down.png").toURI.toURL.toString)
  val blueGhostImg = new Image(new File("src/main/resource/OOP_PacMan/image/blueghost-down.png").toURI.toURL.toString)
  val coralGhostImg = new Image(new File("src/main/resource/OOP_PacMan/image/coralghost-down.png").toURI.toURL.toString)
  val redGhostImg = new Image(new File("src/main/resource/OOP_PacMan/image/redghost-down.png").toURI.toURL.toString)

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
    y = 87
//    x = 24
//    y = 520
    translateX <== blueGhostX
    translateY <== blueGhostY
  }

  val coralGhost = new ImageView(coralGhostImg){
    fitWidth = ghostW
    preserveRatio = true
    x = 24
    y = 87
//    x = 360
//    y = 520
    translateX <== coralGhostX
    translateY <== coralGhostY
  }

  val redGhost = new ImageView(redGhostImg){
    fitWidth = ghostW
    preserveRatio = true
    x = 24
    y = 87
//    x = 360
//    y = 87
    translateX <== redGhostX
    translateY <== redGhostY
  }

  //show current time
  val startTime = System.nanoTime()
  var duration:Double = 0

  //set timers for each direction given
  var frameCount:Int = 0
  var frameCount2:Int = 0

  //variable to keep random number
  var randomNoPurple:Int = 0
  var randomNoBlue:Int = 0
  var randomNoCoral:Int = 0
  var randomNoRed:Int = 0

  //set range of random numbers
  val start = 1
  val end = 4

  //animate ghost movement
  val animationTimer=AnimationTimer(e=>{
    duration = (e-startTime)/1e9

    //used to generate random numbers at timed manner(about 20 nanosecs in between)
    //or ghost will go on a spasm
    frameCount+=1
    if (frameCount >= 40) {
      val random = new Random()
      randomNoPurple = start+random.nextInt((end-start)+1)
      randomNoBlue = start+random.nextInt((end-start)+1)
      frameCount = 0
    }

    frameCount2+=1
    if (frameCount2 >= 80) {
      val random = new Random()
      randomNoCoral = start+random.nextInt((end-start)+1)
      randomNoRed = start+random.nextInt((end-start)+1)
      frameCount2 = 0
    }

    movement(randomNoPurple,blueGhostX,blueGhostY,5)

    movement(randomNoBlue,purpleGhostX,purpleGhostY,5)

    movement(randomNoCoral,coralGhostX,coralGhostY,5)

    movement(randomNoRed,redGhostX,redGhostY,5)

  })


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

  //reserved

  //    duration = (e-startTime)/1e9

//  def randomGenerator(timeSet:Int)= {
//    frameCount+=1
//    if (frameCount >= timeSet) {
//      val random = new Random()
//      randomNoPurple = start+random.nextInt((end-start)+1)
//      randomNoBlue = start+random.nextInt((end-start)+1)
//      randomNoCoral = start+random.nextInt((end-start)+1)
//      randomNoRed = start+random.nextInt((end-start)+1)
//      frameCount = 0
//    }
//  }

}

