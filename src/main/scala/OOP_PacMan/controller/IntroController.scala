package OOP_PacMan.controller

import java.io.FileInputStream

import OOP_PacMan.Main
import scalafx.event.ActionEvent
import scalafx.scene.image.{Image, ImageView}
import scalafxml.core.macros.sfxml

@sfxml
class IntroController(
                       private val close : ImageView,
                       private val directionalKeys: ImageView,
                       private val escKey: ImageView
                     ) {

  val closeBtnImg = new Image(new FileInputStream("src/main/resource/OOP_PacMan/image/close.png"))
  val directionalImg = new Image(new FileInputStream("src/main/resource/OOP_PacMan/image/keyboard.png"))
  val escapeImg = new Image(new FileInputStream("src/main/resource/OOP_PacMan/image/esc.png"))

  close.setImage(closeBtnImg)
  directionalKeys.setImage(directionalImg)
  escKey.setImage(escapeImg)


  def closeIntro(action:ActionEvent)={
    Main.backToMain()
  }

}
