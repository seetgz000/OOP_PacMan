package OOP_PacMan.ghost

import java.io.File

import OOP_PacMan.component.Ghost
import OOP_PacMan.Main.pacman
import OOP_PacMan.{Main, Movement}
import scalafx.scene.image.{Image, ImageView}

import scala.language.postfixOps
import scalafx.animation.{AnimationTimer, PauseTransition}
import scalafx.beans.property.DoubleProperty
import scalafx.scene.Node
import scalafx.scene.input.KeyCode
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.util.Duration

import scala.util.Random


object Ghosts extends Movement {

  /** set ghosts images */
  val purpleGhostImg = new Image(new File("src/main/resource/OOP_PacMan/image/purpleghost-down.png").toURI.toURL.toString)
  val blueGhostImg = new Image(new File("src/main/resource/OOP_PacMan/image/blueghost-down.png").toURI.toURL.toString)
  val coralGhostImg = new Image(new File("src/main/resource/OOP_PacMan/image/coralghost-down.png").toURI.toURL.toString)
  val redGhostImg = new Image(new File("src/main/resource/OOP_PacMan/image/redghost-down.png").toURI.toURL.toString)

  val purpleGhost = new Ghost(192, 120)
  purpleGhost.setImage(purpleGhostImg)

  val blueGhost = new Ghost(25, 520)
  blueGhost.setImage(blueGhostImg)

  val coralGhost = new Ghost(360, 90)
  coralGhost.setImage(coralGhostImg)

  val redGhost = new Ghost(360, 400) //y =500
  redGhost.setImage(redGhostImg)

  //set timers for each direction given
  var frameCount: Int = 0
  var frameCount2: Int = 0

  //variable to keep random number
  var randomNoPurple: Int = 0
  var randomNoBlue: Int = 0
  var randomNoCoral: Int = 0
  var randomNoRed: Int = 0


  //animate ghost movement
  val animationTimer = AnimationTimer(e => {
    //set range of random numbers from 1 to 4
    val start = 1
    val end = 4

    //used to generate random numbers at timed manner(about few frames in between)
    //or ghost will go on a spasm
    frameCount += 1
    if (frameCount >= 60) {
      val random = new Random()
      randomNoPurple = start + random.nextInt((end - start) + 1)
      randomNoBlue = start + random.nextInt((end - start) + 1)
      frameCount = 0
    }

    frameCount2 += 1
    if (frameCount2 >= 50) {
      val random = new Random()
      randomNoCoral = start + random.nextInt((end - start) + 1)
      randomNoRed = start + random.nextInt((end - start) + 1)
      frameCount2 = 0
    }

    movement(purpleGhost, randomNoPurple, 6)

    movement(blueGhost, randomNoBlue, 6)

    movement(coralGhost, randomNoCoral, 6)

    movement(redGhost, randomNoRed, 6)

    if(pacman.getBoundsInParent().intersects(purpleGhost.getBoundsInParent())||
       pacman.getBoundsInParent().intersects(blueGhost.getBoundsInParent())||
       pacman.getBoundsInParent().intersects(coralGhost.getBoundsInParent())||
       pacman.getBoundsInParent().intersects(redGhost.getBoundsInParent())){
          die()
        }
  })//end animation timer

  // return to position everytime game starts
  def preparingGhost() = {
    purpleGhost.translateX() = 0
    purpleGhost.translateY() = 0

    blueGhost.translateX() = 0
    blueGhost.translateY() = 0

    coralGhost.translateX() = 0
    coralGhost.translateY() = 0

    redGhost.translateX() = 0
    redGhost.translateY() = 0

    animationTimer.start()
  }

  def stopGhosts(): Unit ={
    animationTimer.stop()

  }

  //when pacman loses
  def die() {
    val timer = new PauseTransition(Duration(2000))

      animationTimer.stop()
      //death animation
      pacman.setImage(new Image(new File("src/main/resource/OOP_PacMan/image/pacmanDeath.gif")
        .toURI.toURL.toString))
      timer.onFinished = e => {
        Main.backToMain()
        pacman.setImage(new Image(new File("src/main/resource/OOP_PacMan/image/pacmanGIF(fast).gif")
          .toURI.toURL.toString))
      } //end die
      timer.play()
    }




  //**reserved**//

  //    duration = (e-startTime)/1e9

}

