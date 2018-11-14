package OOP_PacMan.controller

<<<<<<< HEAD
import java.io.File

import scalafx.scene.control.Label
=======
import java.io.FileInputStream

import OOP_PacMan.Main
import scalafx.event.ActionEvent
>>>>>>> origin/scene_switching
import scalafx.scene.image.{Image, ImageView}
import scalafxml.core.macros.sfxml

@sfxml
class IntroController(
                       private val close : ImageView,
                       private val directionalKeys: ImageView,
<<<<<<< HEAD
                       private val escKey: ImageView,
                     private val test:Label
                     ) {
  val closeBtnImg = new Image(new File("/image/close.jpg").toURI.toURL.toString)
  val directionalImg = new Image(new File("/image/keyboard.jpg").toURI.toURL.toString)
  val escapeImg = new Image(new File("/image/esc.jpg").toURI.toURL.toString)
=======
                       private val escKey: ImageView
                     ) {

  val closeBtnImg = new Image(new FileInputStream("src/main/resource/OOP_PacMan/image/close.png"))
  val directionalImg = new Image(new FileInputStream("src/main/resource/OOP_PacMan/image/keyboard.png"))
  val escapeImg = new Image(new FileInputStream("src/main/resource/OOP_PacMan/image/esc.png"))
>>>>>>> origin/scene_switching

  close.setImage(closeBtnImg)
  directionalKeys.setImage(directionalImg)
  escKey.setImage(escapeImg)

<<<<<<< HEAD
=======
  def closeIntro(action:ActionEvent)={
    Main.closeIntroduction
  }
>>>>>>> origin/scene_switching


}
