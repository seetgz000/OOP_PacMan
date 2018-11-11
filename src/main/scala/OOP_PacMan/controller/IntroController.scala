package OOP_PacMan.controller

import java.io.File

import scalafx.scene.control.Label
import scalafx.scene.image.{Image, ImageView}
import scalafxml.core.macros.sfxml

@sfxml
class IntroController(
                       private val close : ImageView,
                       private val directionalKeys: ImageView,
                       private val escKey: ImageView,
                     private val test:Label
                     ) {
  val closeBtnImg = new Image(new File("/image/close.jpg").toURI.toURL.toString)
  val directionalImg = new Image(new File("/image/keyboard.jpg").toURI.toURL.toString)
  val escapeImg = new Image(new File("/image/esc.jpg").toURI.toURL.toString)

  close.setImage(closeBtnImg)
  directionalKeys.setImage(directionalImg)
  escKey.setImage(escapeImg)



}
