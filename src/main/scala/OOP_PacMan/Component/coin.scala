package OOP_PacMan.Component

import java.io.File

import scalafx.scene.image.{Image, ImageView}

class coin extends ImageView{
  val imageW = 23// set image Width
  val imageH = imageW // set image Height
  var coinfile = new File("src/main/resource/OOP_PacMan/image/coin.png").toURI.toURL.toString
  var coin = new Image(coinfile,imageW,imageH,true,false)
  val coinStyle: Iterable[String] = List("coin")

  fitWidth = imageW
  image = coin
  styleClass= coinStyle
}