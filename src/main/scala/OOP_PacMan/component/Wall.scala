package OOP_PacMan.component

import java.io.File

import scalafx.scene.image.{Image, ImageView}

class Wall extends ImageView{
  val imageW = 23// set image Width
  val imageH = imageW // set image Height
  var wallFile = new File("src/main/resource/OOP_PacMan/image/wall.png").toURI.toURL.toString
  var wall = new Image(wallFile,imageW,imageH,true,false)
  val wallStyle: Iterable[String] = List("wall")

  fitWidth = imageW
  image = wall
  styleClass= wallStyle
}
