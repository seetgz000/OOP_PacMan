package OOP_PacMan.component

import scalafx.scene.image.{Image, ImageView}

class Ghost(xLayout:Double,yLayout:Double) extends ImageView{

  fitWidth = 20
  preserveRatio = true
  x = xLayout
  y = yLayout

}
