package OOP_PacMan.Component

import java.io.File

import scalafx.scene.image.{Image, ImageView}

class ground extends ImageView{
  val imageW = 23// set image Width
  val imageH = imageW // set image Height
  var groundFile = new File("src/main/resource/OOP_PacMan/image/ground.png").toURI.toURL.toString
  var ground = new Image(groundFile,imageW,imageH,true,false)
  val groundStyle: Iterable[String] = List("ground")

  fitWidth = imageW
  image = ground
  styleClass= groundStyle
}